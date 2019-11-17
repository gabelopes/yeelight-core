package com.voltaic.yeelight.method;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter(AccessLevel.PROTECTED)
public class Timeout implements Runnable {
  private int timeout;
  private boolean expired;

  public Timeout(int timeout) {
    this.timeout = timeout;
    this.expired = false;
  }

  @Getter(AccessLevel.PROTECTED)
  private Thread thread;

  @SneakyThrows
  public void start() {
    this.setThread(new Thread(this));
    this.getThread().start();
  }

  @SneakyThrows
  public void stop() {
    this.getThread().interrupt();
    this.setExpired(false);
  }

  @Override
  @SneakyThrows
  public void run() {
    this.wait(this.getTimeout());
    this.setExpired(true);
  }
}
