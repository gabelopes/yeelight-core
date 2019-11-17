package com.voltaic.yeelight.ssdp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Getter
@Setter(AccessLevel.PRIVATE)
public class Request extends Message {
  private String method;

  public Request(String method, String httpVersion) {
    super(httpVersion);
    this.method = method;
  }

  public Request(String method, String httpVersion, Map<String, String> headers) {
    super(httpVersion, headers);
    this.method = method;
  }

  private String getRequest() {
    return this.getMethod() + " * HTTP/" + this.getHttpVersion();
  }

  private String inlineHeader(Entry<String, String> entry) {
    return entry.getKey() + ": " + entry.getValue();
  }

  private List<String> inlineHeaders() {
    return this.getHeaders().entrySet().stream()
      .map(this::inlineHeader)
      .collect(Collectors.toList());
  }

  public DatagramPacket toPacket() {
    return this.toPacket(System.lineSeparator());
  }

  public DatagramPacket toPacket(String lineSeparator) {
    final List<String> lines = new ArrayList<>();

    lines.add(this.getRequest());
    lines.addAll(this.inlineHeaders());

    final String message = String.join(lineSeparator, lines);
    final byte[] messageBuffer = message.getBytes(StandardCharsets.US_ASCII);

    return new DatagramPacket(messageBuffer, messageBuffer.length);
  }
}
