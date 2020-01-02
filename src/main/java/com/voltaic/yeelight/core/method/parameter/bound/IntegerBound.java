package com.voltaic.yeelight.core.method.parameter.bound;

public class IntegerBound implements Bound<Integer> {
  protected boolean acceptsInteger(int value) {
    return true;
  }

  @Override
  public boolean accepts(Object value) {
    return (value instanceof Integer || int.class.isInstance(value))
        && this.acceptsInteger((int) value);
  }

  @Override
  public Class<Integer> getType() {
    return Integer.class;
  }
}
