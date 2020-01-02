package com.voltaic.yeelight.core.method.parameter.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ColorAction {
  INCREASE("increase"),
  DECREASE("decrease"),
  CIRCLE("circle");

  private String name;
}
