package com.voltaic.yeelight.core;

import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Enumeration {
  public static <T extends Enum<T>> T find(Class<T> enumeration, Predicate<T> predicate) {
    return Stream.of(enumeration.getEnumConstants())
      .filter(predicate)
      .findFirst()
      .orElse(null);
  }

  public static <T extends Enum<T>, P> Set<T> findAll(Class<T> enumeration, Predicate<T> predicate, Set<P> properties) {
    return properties.stream()
      .map(property -> Enumeration.find(enumeration, predicate))
      .collect(Collectors.toUnmodifiableSet());
  }

  public static <T extends Enum<T>, P> T find(Class<T> enumeration, Function<T, P> accessor, P property) {
    return Stream.of(enumeration.getEnumConstants())
      .filter(enumerator -> Objects.equals(accessor.apply(enumerator), property))
      .findFirst()
      .orElse(null);
  }

  public static <T extends Enum<T>, P> Set<T> findAll(Class<T> enumeration, Function<T, P> accessor, Set<P> properties) {
    return properties.stream()
      .map(property -> Enumeration.find(enumeration, accessor, property))
      .collect(Collectors.toUnmodifiableSet());
  }
}
