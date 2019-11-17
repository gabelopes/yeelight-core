package com.voltaic.yeelight.ssdp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public abstract class Message {
  private String httpVersion;
  private Map<String, String> headers;

  public Message(String httpVersion) {
    this(httpVersion, new LinkedHashMap<>());
  }

  public String getHeader(String name) {
    return this.getHeaders().get(name);
  }

  public void setHeader(String name, String value) {
    this.getHeaders().put(name, value);
  }
}
