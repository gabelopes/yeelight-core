package com.voltaic.yeelight.core.method.parameter.bound;

import com.voltaic.yeelight.core.method.parameter.value.Effect;

public class EffectBound extends EnumBound<Effect, String> {
  public EffectBound() {
    super(Effect.class, String.class, Effect::getName);
  }
}
