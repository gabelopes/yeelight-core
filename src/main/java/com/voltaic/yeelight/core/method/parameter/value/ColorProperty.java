package com.voltaic.yeelight.core.method.parameter.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ColorProperty {
  BRIGHTNESS("bright"),
  TEMPERATURE("ct"),
  RGB("color");

  private String name;
}
