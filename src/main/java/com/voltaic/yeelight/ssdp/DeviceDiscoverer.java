package com.voltaic.yeelight.ssdp;

import com.voltaic.yeelight.network.Address;
import lombok.Getter;
import lombok.SneakyThrows;

import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DeviceDiscoverer {
  private static final String ALL_SEARCH_TARGETS = "ssdp:all";
  private static final String SEARCH_METHOD = "M-SEARCH";
  private static final String HTTP_VERSION = "1.1";
  private static final String DISCOVER_MESSAGE_TYPE = "\"ssdp:discover\"";

  private static final int DEFAULT_TIMEOUT = 1000 * 2;
  private static final int DEFAULT_BUFFER_SIZE = 1024;

  private Address address;
  private String searchTarget;
  private MulticastSocket socket;

  public DeviceDiscoverer(Address address, String searchTarget) {
    this.address = address;
    this.searchTarget = searchTarget;
  }

  public DeviceDiscoverer(Address address) {
    this(address, ALL_SEARCH_TARGETS);
  }

  public List<Response> discover() {
    return this.discover(DEFAULT_TIMEOUT);
  }

  public List<Response> discover(int timeout) {
    if (this.getSocket() == null || this.getSocket().isClosed()) {
      this.openSocket();
    }

    this.search(timeout);

    final List<Response> responses = this.receiveResponses();

    this.closeSocket();

    return responses;
  }

  @SneakyThrows
  private void openSocket() {
    this.socket = new MulticastSocket();
  }

  private void closeSocket() {
    this.getSocket().close();
  }

  private Request createSearchRequest() {
    final Request searchRequest = new Request(SEARCH_METHOD, HTTP_VERSION);

    searchRequest.setHeader("HOST", this.getAddress().toString());
    searchRequest.setHeader("MAN", DISCOVER_MESSAGE_TYPE);
    searchRequest.setHeader("ST", this.getSearchTarget());

    return searchRequest;
  }

  @SneakyThrows
  private void search(int timeout) {
    final Request searchRequest = this.createSearchRequest();
    final DatagramPacket searchPacket = searchRequest.toPacket("\r\n");

    searchPacket.setSocketAddress(this.getAddress().toInetSocketAddress());

    this.getSocket().setSoTimeout(timeout);
    this.getSocket().send(searchPacket);
  }

  private DatagramPacket receiveResponse() {
    return this.receiveResponse(DEFAULT_BUFFER_SIZE);
  }

  @SneakyThrows
  private DatagramPacket receiveResponse(int bufferSize) {
    final byte[] responseBuffer = new byte[bufferSize];
    final DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);

    try {
      this.getSocket().receive(responsePacket);
    } catch (SocketTimeoutException ste) {
      return null;
    }

    return responsePacket;
  }

  private List<Response> receiveResponses() {
    final List<Response> responses = new ArrayList<>();

    DatagramPacket responsePacket;

    while ((responsePacket = this.receiveResponse()) != null) {
      final Response response = Response.from(responsePacket);

      if (response != null) {
        responses.add(response);
      }
    }

    return responses;
  }
}
