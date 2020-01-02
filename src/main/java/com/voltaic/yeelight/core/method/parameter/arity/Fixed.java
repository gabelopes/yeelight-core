package com.voltaic.yeelight.core.method.parameter.arity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Fixed implements Arity {
  private int size;

  @Override
  public boolean validate(Object... parameters) {
    return parameters.length == this.getSize();
  }

  @Override
  public String describe() {
    return Integer.toString(this.getSize());
  }
}
