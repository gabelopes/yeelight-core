package com.voltaic.yeelight.lamp.color;

import lombok.Getter;

@Getter
public class RGB implements Color {
  private byte red;
  private byte green;
  private byte blue;

  public RGB(byte red, byte green, byte blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @Override
  public Mode getMode() {
    return Mode.RGB;
  }

  public int toInteger() {
    return (this.getRed() & 0xff) << 16 | (this.getGreen() & 0xff) << 8 | this.getBlue() & 0xff;
  }

  public static RGB parse(int rgb) {
    return new RGB((byte) ((rgb >> 16) & 0xff), (byte) ((rgb >> 8) & 0xff), (byte) (rgb & 0xff));
  }
}
