package com.voltaic.yeelight.core.method.parameter.bound;

import com.voltaic.yeelight.core.Enumeration;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Function;

@Getter
@AllArgsConstructor
public class EnumBound<T extends Enum<T>, A> implements Bound<T> {
  private Class<T> enumeration;
  private Class<A> accessorType;
  private Function<T, A> accessor;

  @Override
  public boolean accepts(Object value) {
    final var enumeration = this.getEnumeration();
    final var accessorType = this.getAccessorType();

    if (accessorType.isInstance(value)) {
      return Enumeration.find(enumeration, this.getAccessor(), accessorType.cast(value)) != null;
    }

    return enumeration.isInstance(value);
  }

  @Override
  public Class<T> getType() {
    return this.getEnumeration();
  }
}
