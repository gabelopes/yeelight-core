package com.voltaic.yeelight.core.method.parameter.bound;

import lombok.Getter;

@Getter
public class IntegerEnumBound<T extends Enum<T>> extends RangeBound {
  public Class<T> enumeration;

  public IntegerEnumBound(Class<T> enumeration) {
    super(0, enumeration.getEnumConstants().length - 1);

    this.enumeration = enumeration;
  }
}
