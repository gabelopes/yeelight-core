package com.voltaic.yeelight.core.method.parameter.bound;

import com.voltaic.yeelight.core.method.parameter.value.Property;

public class PropertyBound extends EnumBound<Property, String> {
  public PropertyBound() {
    super(Property.class, String.class, Property::getName);
  }
}
