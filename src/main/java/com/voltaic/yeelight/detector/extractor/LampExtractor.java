package com.voltaic.yeelight.detector.extractor;

import com.voltaic.yeelight.lamp.Lamp;
import com.voltaic.yeelight.lamp.Model;
import com.voltaic.yeelight.lamp.Operation;
import com.voltaic.yeelight.lamp.color.Color;
import com.voltaic.yeelight.ssdp.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.URI;
import java.util.Set;

@Getter
@AllArgsConstructor
public class LampExtractor {
  private Response response;

  public Lamp extract() {
    return new Lamp(
      this.extractId(),
      this.extractLocation(),
      this.extractName(),
      this.extractModel(),
      this.extractPowered(),
      this.extractBrightness(),
      this.extractColor(),
      this.extractOperations(),
      this.firmwareVersion()
    );
  }

  private int extractId() {
    final String id = this.getResponse().getHeader("id");
    return Integer.decode(id);
  }

  private URI extractLocation() {
    final String location = this.getResponse().getHeader("Location");
    return URI.create(location);
  }

  private String extractName() {
    return this.getResponse().getHeader("name");
  }

  private Model extractModel() {
    final String model = this.getResponse().getHeader("model");
    return Model.forId(model);
  }

  private boolean extractPowered() {
    final String power = this.getResponse().getHeader("power");
    return power.equalsIgnoreCase("on");
  }

  private short extractBrightness() {
    final String bright = this.getResponse().getHeader("bright");
    return Short.parseShort(bright);
  }

  private Color extractColor() {
    return new ColorExtractor(this.getResponse()).extract();
  }

  private Set<Operation> extractOperations() {
    final String support = this.getResponse().getHeader("support");
    final String[] operations = support.split(" ");

    return Operation.forNames(operations);
  }

  private String firmwareVersion() {
    return this.getResponse().getHeader("fw_ver");
  }
}
