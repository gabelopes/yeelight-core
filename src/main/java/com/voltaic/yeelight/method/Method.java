package com.voltaic.yeelight.method;

import com.jsoniter.annotation.JsonIgnore;
import com.jsoniter.output.JsonStream;
import com.voltaic.yeelight.method.promise.Deferred;
import com.voltaic.yeelight.method.promise.Promise;
import com.voltaic.yeelight.method.response.Response;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Method {
  private String id;
  private String method;
  private Object[] params;

  @JsonIgnore
  @Getter(AccessLevel.PROTECTED)
  private Deferred<Response> deferred;

  public Method(String method, Object[] params) {
    this.id = UUID.randomUUID().toString();
    this.method = method;
    this.params = params;
    this.deferred = new Deferred<>();
  }

  public Promise<Response> getPromise() {
    return this.getDeferred().promise();
  }

  public String getRepresentation() {
    return JsonStream.serialize(this) + "\r\n";
  }
}
