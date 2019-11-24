package com.voltaic.yeelight.ssdp;

import com.voltaic.yeelight.exception.IllegalFormatException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class ResponseParser {
  public static final Pattern REQUEST_PATTERN = Pattern.compile("^HTTP/(?<httpVersion>\\d+(?:\\.\\d+)?)\\s+(?<status>\\d+)\\s+\\w+$", Pattern.MULTILINE);
  public static final Pattern HEADER_PATTERN = Pattern.compile("^(?<name>.+?)\\s*:(?:\\s*?|\\s*(?<value>.+))$", Pattern.MULTILINE);

  private String responseText;

  public Response parse() {
    try {
      final Response response = this.parseResponseLine();
      final List<Header> headers = this.parseHeaders();

      response.setHeaders(headers);

      return response;
    } catch (IllegalFormatException ife) {
      return null;
    }
  }

  private Response parseResponseLine() {
    final Matcher matcher = REQUEST_PATTERN.matcher(this.getResponseText());

    if (!matcher.find()) { throw new IllegalFormatException(this.getResponseText()); }

    final String httpVersion = matcher.group("httpVersion");
    final String status = matcher.group("status");
    final int statusCode = Integer.parseInt(status);

    return new Response(httpVersion, statusCode);
  }

  private List<Header> parseHeaders() {
    final List<Header> headers = new LinkedList<>();
    final Matcher matcher = HEADER_PATTERN.matcher(this.getResponseText());

    while (matcher.find()) {
      final String name = matcher.group("name");
      final String value = matcher.group("value");
      final Header header = new Header(name, value);

      headers.add(header);
    }

    return headers;
  }
}
