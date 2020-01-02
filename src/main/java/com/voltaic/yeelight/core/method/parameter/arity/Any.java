package com.voltaic.yeelight.core.method.parameter.arity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Any implements Arity {
  @Override
  public boolean validate(Object... parameters) {
    return true;
  }

  @Override
  public String describe() {
    return "0..N";
  }
}
