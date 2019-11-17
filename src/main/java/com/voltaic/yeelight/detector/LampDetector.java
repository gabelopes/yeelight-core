package com.voltaic.yeelight.detector;

import com.voltaic.yeelight.detector.extractor.LampExtractor;
import com.voltaic.yeelight.lamp.Lamp;
import com.voltaic.yeelight.network.Address;
import com.voltaic.yeelight.ssdp.DeviceDiscoverer;
import com.voltaic.yeelight.ssdp.Response;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class LampDetector {
  private static final Address MULTICAST_ADDRESS = Address.parse("239.255.255.250:1982");
  private static final String LAMP_SEARCH_TARGET = "wifi_bulb";

  public static Set<Lamp> detect() {
    final DeviceDiscoverer deviceDiscoverer = new DeviceDiscoverer(MULTICAST_ADDRESS, LAMP_SEARCH_TARGET);
    final List<Response> deviceResponses = deviceDiscoverer.discover();

    return deviceResponses
      .stream()
      .map(LampExtractor::new)
      .map(LampExtractor::extract)
      .collect(Collectors.toSet());
  }
}
