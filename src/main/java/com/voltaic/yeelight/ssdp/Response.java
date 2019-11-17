package com.voltaic.yeelight.ssdp;

import lombok.*;

import java.net.DatagramPacket;
import java.util.Map;

@Getter
@Setter(AccessLevel.PRIVATE)
public class Response extends Message {
  private int status;

  public Response(String httpVersion, int status) {
    super(httpVersion);
    this.status = status;
  }

  public Response(String httpVersion, int status, Map<String, String> headers) {
    super(httpVersion, headers);
    this.status = status;
  }

  public static Response from(DatagramPacket responsePacket) {
    final String responseText = new String(responsePacket.getData());
    return new ResponseParser(responseText).parse();
  }
}
