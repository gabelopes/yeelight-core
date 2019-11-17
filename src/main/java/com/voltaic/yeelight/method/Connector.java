package com.voltaic.yeelight.method;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.net.Socket;
import java.net.URI;
import java.nio.charset.Charset;

@Getter
@Setter(AccessLevel.PROTECTED)
class Connector {
  private URI address;
  private Socket socket;

  Connector(URI address) {
    this.address = address;
  }

  @SneakyThrows
  public void connect() {
    final URI address = this.getAddress();
    this.setSocket(new Socket(address.getHost(), address.getPort()));
  }

  @SneakyThrows
  public void disconnect() {
    this.getSocket().close();
  }

  @SneakyThrows
  public void send(String message) {
    IOUtils.write(message, this.getSocket().getOutputStream(), Charset.defaultCharset());
  }

  public <T> void send(T object) {
    this.send(object.toString());
  }

  @SneakyThrows
  public String receive() {
    return IOUtils.readLines(this.getSocket().getInputStream(), Charset.defaultCharset())
      .stream()
      .reduce((a, b) -> String.join(System.lineSeparator(), a, b))
      .orElse(null);
  }
}
