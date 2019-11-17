package com.voltaic.yeelight.detector.extractor;

import com.voltaic.yeelight.lamp.color.*;
import com.voltaic.yeelight.ssdp.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ColorExtractor {
  private Response response;

  public Color extract() {
    final Mode mode = this.extractMode();

    if (mode == Mode.RGB) {
      return this.extractRGB();
    }

    if (mode == Mode.HSV) {
      return this.extractHSV();
    }

    return this.extractTemperature();
  }

  private Mode extractMode() {
    final String colorMode = this.getResponse().getHeader("color_mode");
    final int modeCode = Integer.parseInt(colorMode);

    return Mode.forCode(modeCode);
  }

  private RGB extractRGB() {
    final String rgb = this.getResponse().getHeader("rgb");
    final int rgbValue = Integer.parseInt(rgb);

    return RGB.parse(rgbValue);
  }

  private HSV extractHSV() {
    final String hue = this.getResponse().getHeader("hue");
    final String sat = this.getResponse().getHeader("sat");
    final String bright = this.getResponse().getHeader("bright");

    final int hueDegrees = Integer.parseInt(hue);
    final int saturation = Integer.parseInt(sat);
    final int brightness = Integer.parseInt(bright);

    return new HSV(hueDegrees, saturation, brightness);
  }

  private Temperature extractTemperature() {
    final String ct = this.getResponse().getHeader("ct");
    final int temperature = Integer.parseInt(ct);

    return new Temperature(temperature);
  }
}
