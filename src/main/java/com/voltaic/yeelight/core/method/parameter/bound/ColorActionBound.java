package com.voltaic.yeelight.core.method.parameter.bound;

import com.voltaic.yeelight.core.method.parameter.value.ColorAction;

public class ColorActionBound extends EnumBound<ColorAction, String> {
  public ColorActionBound() {
    super(ColorAction.class, String.class, ColorAction::getName);
  }
}
