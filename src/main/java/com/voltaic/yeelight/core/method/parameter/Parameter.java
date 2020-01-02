package com.voltaic.yeelight.core.method.parameter;

import com.voltaic.yeelight.core.Describable;
import com.voltaic.yeelight.core.method.parameter.bound.*;
import com.voltaic.yeelight.core.method.parameter.value.ColorFlowAction;
import com.voltaic.yeelight.core.method.parameter.value.Mode;
import com.voltaic.yeelight.core.method.parameter.value.MusicAction;
import lombok.*;

@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
@RequiredArgsConstructor
public class Parameter implements Describable {
  @NonNull
  private Bound bound;
  private String name;
  private boolean optional;

  public Parameter named(String name) {
    this.setName(name);

    return this;
  }

  public Parameter optional() {
    this.setOptional(true);

    return this;
  }

  public String describe() {
    final var descriptionBuilder = new StringBuilder(this.getName());

    if (this.isOptional()) {
      descriptionBuilder.append("?");
    }

    descriptionBuilder
      .append(": ")
      .append(this.getBound().describe());

    return descriptionBuilder.toString();
  }

  public boolean validate(Object value) {
    return this.getBound().accepts(value);
  }

  public static Parameter string() {
    return new Parameter(new StringBound());
  }

  public static Parameter integer() {
    return new Parameter(new IntegerBound());
  }

  public static <T> Parameter value(T value) {
    return new Parameter(new FixedBound<>(value));
  }

  public static Parameter range(int from, int to) {
    return new Parameter(new RangeBound(from, to));
  }

  public static Parameter lowerLimit(int limit) {
    return new Parameter(new LowerLimitBound(limit));
  }

  public static Parameter ip() {
    return new Parameter(new InetAddressBound()).named("host");
  }

  public static Parameter port() {
    return Parameter.range(0, 65535).named("port");
  }

  public static Parameter property() {
    return new Parameter(new PropertyBound());
  }

  public static Parameter effect() {
    return new Parameter(new EffectBound()).named("effect");
  }

  public static Parameter power() {
    return new Parameter(new PowerBound()).named("power");
  }

  public static Parameter sceneClass() {
    return new Parameter(new SceneClassBound()).named("class");
  }

  public static Parameter flowExpression() {
    return new Parameter(new FlowExpressionBound()).named("flow_expression");
  }

  public static Parameter mode() {
    return new Parameter(new IntegerEnumBound<>(Mode.class)).named("mode");
  }

  public static Parameter colorFlowAction() {
    return new Parameter(new IntegerEnumBound<>(ColorFlowAction.class)).named("action");
  }

  public static Parameter musicAction() {
    return new Parameter(new IntegerEnumBound<>(MusicAction.class)).named("action");
  }

  public static Parameter colorAction() {
    return new Parameter(new ColorActionBound()).named("action");
  }

  public static Parameter colorProperty() {
    return new Parameter(new ColorPropertyBound()).named("prop");
  }

  public static Parameter duration() {
    return Parameter.lowerLimit(30).named("duration");
  }

  public static Parameter temperature() {
    return Parameter.range(1700, 6500).named("ct_value");
  }

  public static Parameter rgb() {
    return Parameter.range(0, 0xFFFFFF).named("rgb_value");
  }

  public static Parameter hue() {
    return Parameter.range(0, 359).named("hue");
  }

  public static Parameter saturation() {
    return Parameter.range(0, 100).named("sat");
  }

  public static Parameter brightness() {
    return Parameter.range(1, 100).named("brightness");
  }

  public static Parameter percentage() {
    return Parameter.range(-100, 100).named("percentage");
  }
}
