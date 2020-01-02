package com.voltaic.yeelight.core.method.parameter.bound;

import com.voltaic.yeelight.core.method.parameter.value.Power;

public class PowerBound extends EnumBound<Power, String> {
  public PowerBound() {
    super(Power.class, String.class, Power::getState);
  }
}
