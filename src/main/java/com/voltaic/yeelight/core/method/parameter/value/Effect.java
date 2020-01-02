package com.voltaic.yeelight.core.method.parameter.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Effect {
  SUDDEN("sudden"),
  SMOOTH("smooth");

  private String name;
}
