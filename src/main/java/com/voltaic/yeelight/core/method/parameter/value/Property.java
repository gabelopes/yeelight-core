package com.voltaic.yeelight.core.method.parameter.value;

import com.voltaic.yeelight.core.Enumeration;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Property {
  POWERED("power"),
  BRIGHTNESS("bright"),
  TEMPERATURE("ct"),
  RGB("rgb"),
  HUE("hue"),
  SATURATION("sat"),
  COLOR_MODE("color_mode"),
  FLOW_MODE("flowing"),
  SLEEP_TIMER("delayoff"),
  FLOW_PARAMETERS("flow_params"),
  MUSIC_MODE("music_on"),
  NAME("name"),
  BACKGROUND_POWERED("bg_power"),
  BACKGROUND_FLOW_MODE("bg_flowing"),
  BACKGROUND_FLOW_PARAMETERS("bg_flow_params"),
  BACKGROUND_TEMPERATURE("bg_ct"),
  BACKGROUND_COLOR_MODE("bg_lmode"),
  BACKGROUND_BRIGHTNESS("bg_bright"),
  BACKGROUND_RGB("bg_rgb"),
  BACKGROUND_HUE("bg_hue"),
  BACKGROUND_SATURATION("bg_sat"),
  NIGHT_LIGHT_MODE_BRIGHTNESS("nl_br"),
  ACTIVE_MODE("active_mode");

  private String name;

  public static Property forName(String name) {
    return Enumeration.find(Property.class, Property::getName, name);
  }

  public static Set<Property> forNames(String... names) {
    return Stream.of(names)
      .map(Property::forName)
      .collect(Collectors.toUnmodifiableSet());
  }
}
