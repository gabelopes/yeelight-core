package com.voltaic.yeelight.core.method.parameter.bound;

import com.voltaic.yeelight.core.method.parameter.value.SceneClass;

public class SceneClassBound extends EnumBound<SceneClass, String> {
  public SceneClassBound() {
    super(SceneClass.class, String.class, SceneClass::getName);
  }
}
