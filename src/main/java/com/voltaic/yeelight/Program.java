package com.voltaic.yeelight;

import com.voltaic.yeelight.detector.LampDetector;
import com.voltaic.yeelight.lamp.Lamp;
import com.voltaic.yeelight.lamp.Operation;
import com.voltaic.yeelight.method.Scheduler;
import lombok.SneakyThrows;

import java.util.Set;

public class Program {
  private static Scheduler SCHEDULER = new Scheduler();

  @SneakyThrows
  public static void main(String[] args) {
    SCHEDULER.start();

    final Set<Lamp> lamps = LampDetector.detect();

    for (Lamp lamp : lamps) {
      SCHEDULER.enqueue(lamp, Operation.SET_POWERED, "off");
    }

    SCHEDULER.stop();
  }
}
