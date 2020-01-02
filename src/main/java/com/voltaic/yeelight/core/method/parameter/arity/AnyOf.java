package com.voltaic.yeelight.core.method.parameter.arity;

import lombok.Getter;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class AnyOf implements Arity {
  private Set<Integer> sizes;

  AnyOf(Set<Integer> sizes) {
    this.sizes = Collections.unmodifiableSet(sizes);
  }

  AnyOf(int... sizes) {
    this.sizes = IntStream
      .of(sizes)
      .boxed()
      .collect(Collectors.toUnmodifiableSet());
  }

  @Override
  public boolean validate(Object... parameters) {
    return this.getSizes().contains(parameters.length);
  }

  @Override
  public String describe() {
    return this.getSizes()
      .stream()
      .sorted()
      .map(Object::toString)
      .collect(Collectors.joining(", "));
  }
}
