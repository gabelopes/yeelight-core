package com.voltaic.yeelight.method;

import com.voltaic.yeelight.lamp.Lamp;
import com.voltaic.yeelight.lamp.Operation;
import com.voltaic.yeelight.method.promise.Promise;
import com.voltaic.yeelight.method.response.Response;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

@Setter(AccessLevel.PROTECTED)
public class Scheduler implements Runnable {
  private static int DEFAULT_POOL = 10;
  private static int DEFAULT_TIME_TO_LIVE = 2000;

  @Getter
  private int pool;

  @Getter
  private int timeout;

  @Getter
  private boolean running;

  @Getter(AccessLevel.PROTECTED)
  private Queue<Controller> queue;

  @Getter(AccessLevel.PROTECTED)
  private List<Controller> schedulings;

  public Scheduler() {
    this(DEFAULT_POOL);
  }

  public Scheduler(int pool) {
    this(pool, DEFAULT_TIME_TO_LIVE);
  }

  public Scheduler(int pool, int timeout) {
    this.pool = pool;
    this.timeout = timeout;
    this.running = false;
    this.queue = new LinkedList<>();
    this.schedulings = new LinkedList<>();
  }

  public Promise<Response> enqueue(Lamp lamp, String method, Object... parameters) {
    return this.enqueue(lamp, new Method(method, parameters));
  }

  public Promise<Response> enqueue(Lamp lamp, Operation operation, Object... parameters) {
    return this.enqueue(lamp, new Method(operation.getName(), parameters));
  }

  public Promise<Response> enqueue(Lamp lamp, Method method) {
    this.getLampController(lamp).enqueueMethod(method);

    return method.getPromise();
  }

  public void start() {
    this.setRunning(true);
    new Thread(this).start();
  }

  public void stop() {
    this.setRunning(false);
  }

  public void setPool(int pool) {
    if (pool < 1) { return; }

    this.pool = pool;
  }

  private Controller getLampController(Lamp lamp) {
    Controller controller = this.findLampController(lamp);

    if (controller == null) {
      controller = new Controller(lamp, this.getTimeout());
      this.getQueue().add(controller);
    }

    return controller;
  }

  private Controller findLampController(Lamp lamp) {
    final Stream<Controller> schedulingsStream = this.getSchedulings().stream();
    final Stream<Controller> queueStream = this.getQueue().stream();

    return Stream.concat(schedulingsStream, queueStream)
      .filter(controller -> controller.getLamp().equals(lamp))
      .findFirst()
      .orElse(null);
  }

  private boolean canSchedule() {
    return this.getPool() - this.getSchedulings().size() > 0
        && this.getQueue().size() > 0;
  }

  protected void removeExpired() {
    this.getSchedulings().removeIf(Controller::isExpired);
  }

  protected void schedule() {
    while (this.canSchedule()) {
      final Controller controller = this.getQueue().poll();

      this.getSchedulings().add(controller);

      new Thread(controller).start();
    }
  }

  @Override
  public void run() {
    while (this.isRunning()) {
      this.removeExpired();
      this.schedule();
    }
  }
}
