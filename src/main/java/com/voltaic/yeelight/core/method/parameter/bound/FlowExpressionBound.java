package com.voltaic.yeelight.core.method.parameter.bound;

import java.util.Optional;

public class FlowExpressionBound extends StringBound {
  @Override
  protected boolean acceptsString(String value) {
    return super.acceptsString(value);
  }

  @Override
  public Optional<String> describeBounds() {
    return Optional.of("FlowExpression");
  }
}
