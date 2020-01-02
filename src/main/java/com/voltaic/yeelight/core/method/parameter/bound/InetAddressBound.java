package com.voltaic.yeelight.core.method.parameter.bound;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

public class InetAddressBound extends StringBound {
  @Override
  protected boolean acceptsString(String value) {
    try {
      return InetAddress.getByName(value) != null;
    } catch (UnknownHostException e) {
      return false;
    }
  }

  @Override
  public Optional<String> describeBounds() {
    return Optional.of("IP");
  }
}
