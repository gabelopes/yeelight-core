package com.voltaic.yeelight.core.method.parameter.arity;

import com.voltaic.yeelight.core.Describable;

public interface Arity extends Describable {
  boolean validate(Object... parameters);

  static Arity none() {
    return new None();
  }

  static Arity any() {
    return new Any();
  }

  static Arity notEmpty() {
    return new NotEmpty();
  }

  static Arity fixed(int size) {
    return new Fixed(size);
  }

  static Arity ranged(int from, int to) {
    return new Ranged(from, to);
  }

  static Arity anyOf(int... sizes) {
    return new AnyOf(sizes);
  }
}
