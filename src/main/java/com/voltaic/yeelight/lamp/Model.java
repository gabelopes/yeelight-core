package com.voltaic.yeelight.lamp;

import com.voltaic.yeelight.Enumerator;
import lombok.Getter;

import java.util.Objects;

@Getter
public enum Model {
  MONOCHROMATIC("mono"),
  COLORED("color"),
  STRIPE("stripe"),
  CEILING("ceiling"),
  BEDSIDE("bslamp");

  private String id;

  Model(String id) {
    this.id = id;
  }

  public static Model forId(String id) {
    return Enumerator.find(Model.class, model -> Objects.equals(model.getId(), id));
  }
}
