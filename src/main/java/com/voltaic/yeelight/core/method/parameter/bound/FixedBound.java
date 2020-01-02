package com.voltaic.yeelight.core.method.parameter.bound;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class FixedBound<T> implements Bound<T> {
  private T value;

  @Override
  public boolean accepts(Object value) {
    return Objects.equals(value, this.getValue());
  }

  @Override
  @SuppressWarnings("unchecked")
  public Class<T> getType() {
    return (Class<T>) this.getValue().getClass();
  }

  @Override
  public Optional<String> describeBounds() {
    return Optional.of("x = " + this.getValue());
  }
}
