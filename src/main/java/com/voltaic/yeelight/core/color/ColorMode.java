package com.voltaic.yeelight.core.color;

import com.voltaic.yeelight.core.Enumeration;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.IntStream;

@Getter
@AllArgsConstructor
public enum ColorMode {
  RGB(1),
  TEMPERATURE(2),
  HSV(3);

  private int code;

  public static ColorMode forCode(int code) {
    return Enumeration.find(ColorMode.class, ColorMode::getCode, code);
  }

  public static ColorMode[] forCodes(int... codes) {
    return IntStream.of(codes)
      .mapToObj(ColorMode::forCode)
      .toArray(ColorMode[]::new);
  }
}
