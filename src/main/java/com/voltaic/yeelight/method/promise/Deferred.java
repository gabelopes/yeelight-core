package com.voltaic.yeelight.method.promise;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public class Deferred<R> implements Promise<R> {
  private DeferredPromise<R> promise;
  private Map<Status, List<Callback<R>>> callbacks;

  private Status status;
  private R result;

  public Deferred() {
    this.promise = new DeferredPromise<>(this);
    this.callbacks = new HashMap<>();
  }

  private List<Callback<R>> getCallbacks(Status status) {
    return this.getCallbacks().computeIfAbsent(status, s -> new LinkedList<>());
  }

  private boolean isReady() {
    return this.getStatus() != null;
  }

  private void add(Status status, Callback<R> callback) {
    if (!this.isReady()) {
      this.getCallbacks(status).add(callback);
    }

    if (this.getStatus() == status) {
      callback.call(this.getResult());
    }
  }

  private void clearCallbacks() {
    this.getCallbacks().clear();
  }

  private void triggerCallbacks() {
    this.getCallbacks(this.getStatus()).forEach(callback -> callback.call(this.getResult()));
    this.getCallbacks(Status.ALWAYS).forEach(callback -> callback.call(this.getResult()));

    this.clearCallbacks();
  }

  @Override
  public Promise<R> done(Callback<R> callback) {
    this.add(Status.SUCCESS, callback);
    return this.getPromise();
  }

  @Override
  public Promise<R> fail(Callback<R> callback) {
    this.add(Status.ERROR, callback);
    return this.getPromise();
  }

  @Override
  public Promise<R> always(Callback<R> callback) {
    this.add(Status.ALWAYS, callback);
    return this.getPromise();
  }

  public Promise<R> promise() {
    return this.getPromise();
  }

  public void resolve(R result) {
    this.setStatus(Status.SUCCESS);
    this.setResult(result);

    this.triggerCallbacks();
  }

  public void reject(R result) {
    this.setStatus(Status.ERROR);
    this.setResult(result);

    this.triggerCallbacks();
  }
}
