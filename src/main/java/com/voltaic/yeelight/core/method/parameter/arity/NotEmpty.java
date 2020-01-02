package com.voltaic.yeelight.core.method.parameter.arity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class NotEmpty implements Arity {
  @Override
  public boolean validate(Object... parameters) {
    return parameters.length >= 1;
  }

  @Override
  public String describe() {
    return "1..N";
  }
}
