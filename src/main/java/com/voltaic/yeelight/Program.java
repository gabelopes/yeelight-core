package com.voltaic.yeelight;

import com.jsoniter.output.JsonStream;
import com.voltaic.yeelight.detector.LampDetector;
import com.voltaic.yeelight.lamp.Lamp;

import java.util.Set;

public class Program {
  public static void main(String[] args) {
    final Set<Lamp> lamps = LampDetector.detect();
    final String lampsJson = JsonStream.serialize(lamps);

    System.out.println(lampsJson);
  }
}
