package com.voltaic.yeelight.core.method.parameter.bound;

import com.voltaic.yeelight.core.Describable;

import java.util.Optional;

public interface Bound<T> extends Describable {
  boolean accepts(Object value);
  Class<T> getType();

  default Optional<String> describeBounds() {
    return Optional.empty();
  }

  default String describe() {
    final var boundDescription = new StringBuilder(this.getType().getSimpleName());
    final var boundsDescription = this.describeBounds();

    if (boundsDescription.isPresent()) {
      boundDescription
        .append("[")
        .append(boundsDescription.get())
        .append("]");
    }

    return boundDescription.toString();
  }
}
