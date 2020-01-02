package com.voltaic.yeelight.core.method.parameter.bound;

import lombok.Getter;

import java.util.Optional;

@Getter
public class RangeBound extends IntegerBound {
  private int from;
  private int to;

  public RangeBound(int from, int to) {
    this.from = Math.min(from, to);
    this.to = Math.max(from, to);
  }

  @Override
  protected boolean acceptsInteger(int value) {
    return this.getFrom() <= value && value <= this.getTo();
  }

  @Override
  public Optional<String> describeBounds() {
    return Optional.of(this.getFrom() + " < x < " + this.getTo());
  }
}
