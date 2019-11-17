package com.voltaic.yeelight.method.promise;

public interface Promise<R> {
  Promise<R> done(Callback<R> callback);
  Promise<R> fail(Callback<R> callback);
  Promise<R> always(Callback<R> callback);
}
