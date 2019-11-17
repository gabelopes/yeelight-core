package com.voltaic.yeelight.method.promise;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter(AccessLevel.PROTECTED)
class DeferredPromise<R> implements Promise<R> {
  private Deferred<R> deferred;

  public Promise<R> done(Callback<R> callback) {
    return this.getDeferred().done(callback);
  }

  public Promise<R> fail(Callback<R> callback) {
    return this.getDeferred().fail(callback);
  }

  public Promise<R> always(Callback<R> callback) {
    return this.getDeferred().always(callback);
  }
}
