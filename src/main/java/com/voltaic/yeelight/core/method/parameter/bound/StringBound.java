package com.voltaic.yeelight.core.method.parameter.bound;

public class StringBound implements Bound<String> {
  protected boolean acceptsString(String value) {
    return true;
  }

  @Override
  public boolean accepts(Object value) {
    return value instanceof String
        && this.acceptsString((String) value);
  }

  @Override
  public Class<String> getType() {
    return String.class;
  }
}
