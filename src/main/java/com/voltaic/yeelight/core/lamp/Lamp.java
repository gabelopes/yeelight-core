package com.voltaic.yeelight.core.lamp;

import com.voltaic.yeelight.core.color.Color;
import com.voltaic.yeelight.core.method.Method;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URI;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lamp {
  private int id;
  private URI location;
  private String name;
  private Model model;
  private boolean powered;
  private short brightness;
  private Color color;
  private Set<Method> methods;
  private String firmwareVersion;

  @Override
  public boolean equals(Object object) {
    if (this == object) { return true; }

    if (object == null || this.getClass() != object.getClass()) { return false; }

    return this.getId() == ((Lamp) object).id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
