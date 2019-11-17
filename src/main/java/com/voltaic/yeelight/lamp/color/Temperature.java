package com.voltaic.yeelight.lamp.color;

import lombok.Getter;

@Getter
public class Temperature implements Color {
  private int value;

  public Temperature(int value) {
    this.value = value;
  }

  @Override
  public Mode getMode() {
    return Mode.TEMPERATURE;
  }
}
