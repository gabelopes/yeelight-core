package com.voltaic.yeelight.core.method.parameter.bound;

import com.voltaic.yeelight.core.method.parameter.value.ColorProperty;

public class ColorPropertyBound extends EnumBound<ColorProperty, String> {
  public ColorPropertyBound() {
    super(ColorProperty.class, String.class, ColorProperty::getName);
  }
}
