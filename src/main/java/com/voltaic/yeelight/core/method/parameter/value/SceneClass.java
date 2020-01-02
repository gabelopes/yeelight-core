package com.voltaic.yeelight.core.method.parameter.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SceneClass {
  HSV("hsv"),
  TEMPERATURE("ct"),
  COLOR_FLOW("cf"),
  SLEEP_TIMER("auto_delay_off");

  private String name;
}
