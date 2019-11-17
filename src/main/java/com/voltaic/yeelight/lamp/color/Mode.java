package com.voltaic.yeelight.lamp.color;

import com.voltaic.yeelight.Enumerator;
import lombok.Getter;

import java.util.Objects;

@Getter
public enum Mode {
  RGB(1),
  TEMPERATURE(2),
  HSV(3);

  private int code;

  Mode(int code) {
    this.code = code;
  }

  public static Mode forCode(int code) {
    return Enumerator.find(Mode.class, mode -> Objects.equals(mode.getCode(), code));
  }
}
