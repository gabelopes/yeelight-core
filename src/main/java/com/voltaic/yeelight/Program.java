package com.voltaic.yeelight;

import com.voltaic.yeelight.core.method.Method;

import java.util.stream.Stream;

public class Program {
  public static void main(String[] args) {
    final var setHSVDescription = Method.SET_HSV.describe();

    Stream.of(Method.values())
      .map(Method::describe)
      .forEach(System.out::println);
  }
}
