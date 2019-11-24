package com.voltaic.yeelight.lamp;

import com.voltaic.yeelight.Enumerator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Method {
  GET_PROPERTY("get_prop"),
  SET_TEMPERATURE("set_ct_abx"),
  SET_RGB("set_rgb"),
  SET_HSV("set_hsv"),
  SET_BRIGHTNESS("set_bright"),
  SET_POWERED("set_power"),
  TOGGLE("toggle"),
  SET_AS_DEFAULT("set_default"),
  START_COLOR_FLOW("start_cf"),
  STOP_COLOR_FLOW("stop_cf"),
  SET_SCENE("set_scene"),
  ADD_CRON_JOB("cron_add"),
  GET_CRON_JOB("cron_get"),
  DELETE_CRON_JOB("cron_del"),
  SET_ADJUSTMENT("set_adjust"),
  SET_MUSIC("set_music"),
  SET_NAME("set_name"),
  SET_BACKGROUND("bg_set_xxx"),
  TOGGLE_BACKGROUND("bg_toggle"),
  TOGGLE_BOTH_LIGHTS("dev_toggle"),
  ADJUST_BRIGHTNESS("adjust_bright"),
  ADJUST_TEMPERATURE("adjust_ct"),
  ADJUST_COLOR("adjust_color"),
  ADJUST_BACKGROUND_LIGHT("bg_adjust_xx");

  private String name;

  public static Method forName(String name) {
    return Enumerator.find(Method.class, method -> Objects.equals(method.getName(), name));
  }

  public static Set<Method> forNames(String... names) {
    return Stream.of(names)
      .map(Method::forName)
      .collect(Collectors.toUnmodifiableSet());
  }
}
