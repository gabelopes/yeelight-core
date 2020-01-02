package com.voltaic.yeelight.core.method;

import com.voltaic.yeelight.core.Describable;
import com.voltaic.yeelight.core.Enumeration;
import com.voltaic.yeelight.core.method.parameter.Parameter;
import com.voltaic.yeelight.core.method.parameter.arity.Arity;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum Method implements Describable {
  GET_PROPERTY("get_prop", Arity.notEmpty(), Parameter.property().named("property")),
  SET_TEMPERATURE("set_ct_abx", Arity.fixed(3), Parameter.temperature(), Parameter.effect(), Parameter.duration()),
  SET_RGB("set_rgb", Arity.fixed(3), Parameter.rgb(), Parameter.effect(), Parameter.duration()),
  SET_HSV("set_hsv", Arity.fixed(4), Parameter.hue(), Parameter.saturation(), Parameter.effect(), Parameter.duration()),
  SET_BRIGHTNESS("set_bright", Arity.fixed(3), Parameter.brightness(), Parameter.effect(), Parameter.duration()),
  SET_POWERED("set_power", Arity.anyOf(3, 4), Parameter.power(), Parameter.effect(), Parameter.duration(), Parameter.mode().optional()),
  TOGGLE("toggle", Arity.none()),
  SET_AS_DEFAULT("set_default", Arity.none()),
  START_COLOR_FLOW("start_cf", Arity.fixed(3), Parameter.lowerLimit(0).named("count"), Parameter.colorFlowAction(), Parameter.flowExpression()),
  STOP_COLOR_FLOW("stop_cf", Arity.none()),
  SET_SCENE("set_scene", Arity.ranged(3, 4), Parameter.sceneClass(), Parameter.integer().named("val")),
  START_CRON_JOB("cron_add", Arity.fixed(2), Parameter.value(0).named("type"), Parameter.lowerLimit(0).named("value")),
  GET_CRON_JOB_PROPERTY("cron_get", Arity.fixed(1), Parameter.value(0).named("type")),
  STOP_CRON_JOB("cron_del", Arity.fixed(1), Parameter.value(0).named("type")),
  SET_ADJUSTMENT("set_adjust", Arity.fixed(2), Parameter.colorAction(), Parameter.colorProperty()),
  SET_MUSIC("set_music", Arity.anyOf(1, 3), Parameter.musicAction(), Parameter.ip(), Parameter.port()),
  SET_NAME("set_name", Arity.fixed(1), Parameter.string().named("name")),
  SET_BACKGROUND_RGB("bg_set_rgb", Arity.fixed(3), Parameter.rgb(), Parameter.effect(), Parameter.duration()),
  SET_BACKGROUND_HSV("bg_set_hsv", Arity.fixed(4), Parameter.hue(), Parameter.saturation(), Parameter.effect(), Parameter.duration()),
  SET_BACKGROUND_TEMPERATURE("bg_set_ct_abx", Arity.fixed(3), Parameter.temperature(), Parameter.effect(), Parameter.duration()),
  START_BACKGROUND_COLOR_FLOW("bg_start_cf", Arity.fixed(3), Parameter.lowerLimit(0).named("count"), Parameter.colorFlowAction(), Parameter.flowExpression()),
  STOP_BACKGROUND_COLOR_FLOW("bg_stop_cf", Arity.none()),
  SET_BACKGROUND_SCENE("bg_set_scene", Arity.ranged(3, 4), Parameter.sceneClass(), Parameter.integer().named("val")),
  SET_BACKGROUND_AS_DEFAULT("bg_set_default", Arity.none()),
  SET_BACKGROUND_POWER("bg_set_power", Arity.anyOf(3, 4), Parameter.power(), Parameter.effect(), Parameter.duration(), Parameter.mode().optional()),
  SET_BACKGROUND_BRIGHTNESS("bg_set_bright", Arity.fixed(3), Parameter.brightness(), Parameter.effect(), Parameter.duration()),
  SET_SET_ADJUSTMENT("bg_set_adjust", Arity.fixed(2), Parameter.colorAction(), Parameter.colorProperty()),
  TOGGLE_BACKGROUND("bg_toggle", Arity.none()),
  TOGGLE_BOTH_LIGHTS("dev_toggle", Arity.none()),
  ADJUST_BRIGHTNESS("adjust_bright", Arity.fixed(2), Parameter.percentage(), Parameter.duration()),
  ADJUST_TEMPERATURE("adjust_ct", Arity.fixed(2), Parameter.percentage(), Parameter.duration()),
  ADJUST_COLOR("adjust_color", Arity.fixed(2), Parameter.percentage(), Parameter.duration()),
  ADJUST_BACKGROUND_BRIGHTNESS("bg_adjust_bright", Arity.fixed(2), Parameter.percentage(), Parameter.duration()),
  ADJUST_BACKGROUND_TEMPERATURE("bg_adjust_ct", Arity.fixed(2), Parameter.percentage(), Parameter.duration());

  private String name;
  private Arity arity;
  private Parameter[] parameters;

  Method(String name, Arity arity, Parameter... parameters) {
    this.name = name;
    this.arity = arity;
    this.parameters = parameters;
  }

  public String describe() {
    final var descriptionBuilder = new StringBuilder(this.getName());
    final var describedParameters = Stream.of(this.getParameters())
      .map(Parameter::describe)
      .collect(Collectors.joining(", "));

    descriptionBuilder
      .append("[")
      .append(this.getArity().describe())
      .append("]");

    descriptionBuilder
      .append("(")
      .append(describedParameters)
      .append(")");

    return descriptionBuilder.toString();
  }

  public static Method forName(String name) {
    return Enumeration.find(Method.class, Method::getName, name);
  }

  public static Set<Method> forNames(String... names) {
    return Stream.of(names)
      .map(Method::forName)
      .collect(Collectors.toUnmodifiableSet());
  }
}
