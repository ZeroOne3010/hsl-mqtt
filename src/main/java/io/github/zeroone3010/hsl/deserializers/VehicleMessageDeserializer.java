package io.github.zeroone3010.hsl.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.zeroone3010.hsl.domain.VehicleMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class VehicleMessageDeserializer extends StdDeserializer<VehicleMessage> {

  private final ObjectMapper mapper = new ObjectMapper();
  private final Properties operators = new Properties();

  public VehicleMessageDeserializer() {
    this(null);
  }

  private VehicleMessageDeserializer(Class<?> vc) {
    super(vc);
    mapper.registerModule(new JavaTimeModule());

    final String operatorsPropertiesPath = Thread.currentThread().getContextClassLoader()
        .getResource("").getPath() + "operators.properties";
    try {
      operators.load(new FileInputStream(operatorsPropertiesPath));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public VehicleMessage deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    final JsonNode node = jp.getCodec().readTree(jp);
    final String eventType = node.fieldNames().next();

    try {
      final VehicleMessage vehicleMessage = mapper.treeToValue(node.get(eventType), VehicleMessage.class);
      vehicleMessage.setEventType(eventType);
      vehicleMessage.setOperatorName(Optional.ofNullable(operators.getProperty(vehicleMessage.getOperatorId().toString()))
          .orElse("Unknown"));
      return vehicleMessage;
    } catch (final JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
