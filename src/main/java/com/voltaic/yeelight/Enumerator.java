package com.voltaic.yeelight;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class Enumerator {
  public static <T extends Enum<T>> T find(Class<T> enumeration, Predicate<T> predicate) {
    return Stream.of(enumeration.getEnumConstants())
      .filter(predicate)
      .findFirst()
      .orElse(null);
  }
}
