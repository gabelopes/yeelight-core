package com.voltaic.yeelight.method.promise;

@FunctionalInterface
public interface Callback<R> {
  void call(R results);
}
