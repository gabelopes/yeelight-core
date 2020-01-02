package com.voltaic.yeelight.core.method.parameter.bound;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class LowerLimitBound extends IntegerBound {
  private int limit;

  @Override
  protected boolean acceptsInteger(int value) {
    return this.getLimit() <= value;
  }

  @Override
  public Optional<String> describeBounds() {
    return Optional.of("x > " + this.getLimit());
  }
}
