package com.voltaic.yeelight.network;

import lombok.Getter;
import lombok.SneakyThrows;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

@Getter
public class Address {
  private InetAddress ip;
  private int port;

  public Address(InetAddress ip, int port) {
    this.ip = ip;
    this.port = port;
  }

  public Address(byte[] ip, int port) throws UnknownHostException {
    this(InetAddress.getByAddress(ip), port);
  }

  public Address(String ip, int port) throws UnknownHostException {
    this(InetAddress.getByName(ip), port);
  }

  public Address(int port) throws UnknownHostException {
    this(InetAddress.getLocalHost(), port);
  }

  public Address(InetAddress ip) {
    this(ip, 0);
  }

  public Address(byte[] ip) throws UnknownHostException {
    this(ip, 0);
  }

  public Address(String ip) throws UnknownHostException {
    this(ip, 0);
  }

  public Address() throws UnknownHostException {
    this(0);
  }

  public InetSocketAddress toInetSocketAddress() {
    return new InetSocketAddress(this.getIp(), this.getPort());
  }

  public InetAddress toInetAddress() {
    return this.getIp();
  }

  @SneakyThrows
  public static Address parse(String address) {
    final var parts = address.split(":");

    if (parts.length < 2) {
      throw new IllegalArgumentException(address);
    }

    final var ip = InetAddress.getByName(parts[0]);
    final var port = Integer.parseInt(parts[1]);

    return new Address(ip, port);
  }

  @Override
  public String toString() {
    return this.getIp().getHostAddress() + ":" + this.getPort();
  }
}
