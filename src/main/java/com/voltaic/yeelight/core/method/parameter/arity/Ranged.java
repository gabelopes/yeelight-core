package com.voltaic.yeelight.core.method.parameter.arity;

import lombok.Getter;

@Getter
public class Ranged implements Arity {
  private int from;
  private int to;

  Ranged(int from, int to) {
    this.from = Math.min(from, to);
    this.to = Math.max(from, to);
  }

  @Override
  public boolean validate(Object... parameters) {
    final var size = parameters.length;
    return this.getFrom() <= size && size <= this.getTo();
  }

  @Override
  public String describe() {
    return this.getFrom() + ".." + this.getTo();
  }
}
