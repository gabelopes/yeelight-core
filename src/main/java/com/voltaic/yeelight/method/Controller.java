package com.voltaic.yeelight.method;

import com.voltaic.yeelight.lamp.Lamp;
import com.voltaic.yeelight.method.promise.Deferred;
import com.voltaic.yeelight.method.response.Response;
import com.voltaic.yeelight.method.response.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
class Controller implements Runnable {
  private Timeout timeout;

  private Lamp lamp;
  private Connector connector;
  private Queue<Method> queue;

  Controller(Lamp lamp, int timeout) {
    this.timeout = new Timeout(timeout);
    this.lamp = lamp;
    this.connector = new Connector(lamp.getLocation());
    this.queue = new LinkedList<>();
  }

  public boolean isExpired() {
    return this.getTimeout().isExpired();
  }

  public void enqueueMethod(Method method) {
    this.getQueue().offer(method);
    this.getTimeout().stop();
  }

  private void startTimeout() {
    this.getTimeout().start();
  }

  private Response sendRequest(Method method) {
    this.getConnector().send(method.getRepresentation());
    return this.receiveResponse();
  }

  private Response receiveResponse() {
    final String responseMessage = this.getConnector().receive();
    return Response.from(responseMessage);
  }

  private void executeMethod() {
    final Method method = this.getQueue().poll();
    final Deferred<Response> deferred = method.getDeferred();
    final Response response = this.sendRequest(method);

    response.setLamp(this.getLamp());
    response.setMethod(method);

    if (response.getStatus() == Status.OK) {
      deferred.resolve(response);
    } else {
      deferred.reject(response);
    }
  }

  private boolean hasAvailableMethods() {
    return this.getQueue().size() > 0;
  }

  @Override
  public void run() {
    this.getConnector().connect();

    while (!this.isExpired()) {
      if (this.hasAvailableMethods()) {
        this.executeMethod();
      } else {
        this.startTimeout();
      }
    }

    this.getConnector().disconnect();
  }
}
