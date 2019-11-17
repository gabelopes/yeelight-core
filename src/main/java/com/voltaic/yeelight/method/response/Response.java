package com.voltaic.yeelight.method.response;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.voltaic.yeelight.lamp.Lamp;
import com.voltaic.yeelight.method.Method;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
  private Lamp lamp;
  private Method method;

  private String id;
  private Status status;
  private Object[] results;

  public Response(String id, Status status, Object[] results) {
    this.id = id;
    this.status = status;
    this.results = results;
  }

  private static Status getStatus(Map<String, Any> responseMap) {
    return responseMap.containsKey("result") ? Status.OK : Status.ERROR;
  }

  private static Object[] getResults(Map<String, Any> responseMap) {
    final String key = responseMap.containsKey("result") ? "result" : "error";
    final Any results = responseMap.get(key);

    return results.as(Object[].class);
  }

  public static Response from(String responseMessage) {
    final Map<String, Any> responseMap = JsonIterator.deserialize(responseMessage).asMap();

    final String id = responseMap.get("id").toString();
    final Status status = Response.getStatus(responseMap);
    final Object[] results = Response.getResults(responseMap);

    return new Response(id, status, results);
  }
}
