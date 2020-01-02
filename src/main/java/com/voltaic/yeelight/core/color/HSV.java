package com.voltaic.yeelight.core.color;

import lombok.Getter;

@Getter
public class HSV implements Color {
  private int hue;
  private int saturation;
  private int value;

  public HSV(int hue, int saturation, int value) {
    this.hue = hue;
    this.saturation = saturation;
    this.value = value;
  }

  @Override
  public ColorMode getMode() {
    return ColorMode.HSV;
  }
}
