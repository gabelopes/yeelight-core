package com.voltaic.yeelight.ssdp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public abstract class Message {
  private String httpVersion;
  private List<Header> headers;

  public Message(String httpVersion) {
    this(httpVersion, new LinkedList<>());
  }

  private Optional<Header> findHeader(String name) {
    return this.getHeaders()
      .stream()
      .filter(header -> Objects.equals(header.getName(), name))
      .findFirst();
  }

  public String getHeader(String name) {
    final Optional<Header> optionalHeader = this.findHeader(name);

    if (optionalHeader.isEmpty()) { return null; }

    return optionalHeader.get().getValue();
  }

  public void setHeader(String name, String value) {
    final Optional<Header> optionalHeader = this.findHeader(name);

    if (optionalHeader.isPresent()) {
      optionalHeader.get().setValue(value);
    } else {
      final Header header = new Header(name, value);
      this.getHeaders().add(header);
    }
  }
}
