package com.voltaic.yeelight.core.method.parameter.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Power {
  ON("on"),
  OFF("off");

  private String state;
}
