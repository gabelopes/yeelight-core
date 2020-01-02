package com.voltaic.yeelight.core.lamp;

import com.voltaic.yeelight.core.Enumeration;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum Model {
  MONOCHROMATIC("mono"),
  COLORED("color"),
  STRIPE("stripe"),
  CEILING("ceiling"),
  BEDSIDE("bslamp");

  private String id;

  public static Model forId(String id) {
    return Enumeration.find(Model.class, Model::getId, id);
  }

  public static Set<Model> forIds(String... ids) {
    return Enumeration.findAll(Model.class, Model::getId, Set.of(ids));
  }
}
