package com.voltaic.yeelight.ssdp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Header {
  private static final String SEPARATOR = ": ";

  private String name;
  private String value;

  @Override
  public String toString() {
    return this.getName() + SEPARATOR + this.getValue();
  }
}
