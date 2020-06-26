package io.github.zeroone3010.hsl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.zeroone3010.hsl.deserializers.VehicleMessageDeserializer;
import io.github.zeroone3010.hsl.domain.VehicleMessage;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.UUID;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.NANO_OF_SECOND;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;

public class HslMqtt {

  private static final DateTimeFormatter TIMESTAMP_FORMATTER = new DateTimeFormatterBuilder()
      .parseCaseInsensitive()
      .append(ISO_LOCAL_DATE)
      .appendLiteral('T')
      .appendValue(HOUR_OF_DAY, 2)
      .appendLiteral(':')
      .appendValue(MINUTE_OF_HOUR, 2)
      .appendLiteral(':')
      .appendValue(SECOND_OF_MINUTE, 2)
      .appendFraction(NANO_OF_SECOND, 3, 3, true)
      .appendOffsetId()
      .toFormatter(Locale.ENGLISH);

  public static void main(String... args) throws MqttException {

    if (args == null || args.length < 2) {
      System.out.println("Parameters required: <speed_limit> <MQTT_topic> [<MQTT_topic>]*");
      System.out.println("For example:");
      System.out.println("\t40 \"/hfp/v2/journey/ongoing/vp/+/+/+/+/+/+/+/+/+/60;24/19/85/#\"");
      System.out.println("...for vehicle positions around the Sörnäinen region in Helsinki, with");
      System.out.println("vehicle speeds above 40 km/h highlighted in stderr instead of stdout.");
      System.exit(0);
    }

    final Double speedLimit = Double.parseDouble(args[0]);
    final String[] topicFilters = new String[args.length - 1];
    System.arraycopy(args, 1, topicFilters, 0, args.length - 1);

    final String clientId = UUID.randomUUID().toString();
    final IMqttClient client = new MqttClient("wss://mqtt.hsl.fi:443/", clientId);

    final MqttConnectOptions options = new MqttConnectOptions();
    options.setAutomaticReconnect(true);
    options.setCleanSession(true);
    options.setConnectionTimeout(10);
    client.connect(options);


    client.subscribe(topicFilters, new int[topicFilters.length]);

    final ObjectMapper mapper = new ObjectMapper();
    final SimpleModule module = new SimpleModule();
    module.addDeserializer(VehicleMessage.class, new VehicleMessageDeserializer());
    mapper.registerModule(module);

    client.setCallback(new MqttCallback() {
      @Override
      public void connectionLost(Throwable cause) {
        System.err.println("Connection lost! Exception: " + cause);
      }

      @Override
      public void messageArrived(String topic, MqttMessage rawMessage) throws Exception {
        final VehicleMessage msg = mapper.readValue(rawMessage.toString(), VehicleMessage.class);
        final PrintStream printStream = msg.getSpeed().getKilometersPerHour() > speedLimit ? System.err : System.out;
        printStream.println(msg.getTimestamp().format(TIMESTAMP_FORMATTER) +
            "\t" + msg.getEventType() +
            "\t" + msg.getDesignation() +
            "\t(" + msg.getOperatorName() + ")" +
            "\tv=" + msg.getSpeed().getKilometersPerHour() + " km/h" +
            "\ta=" + msg.getAccelerationMetersPerSecondSquared() + " m/s²" +
            "\theading=" + msg.getHeading() + '⁰' +
            "\tat N " + msg.getLatitude() + ", E " + msg.getLongitude() +
            "\toffset from schedule: " + msg.getOffsetFromScheduleInSeconds() + " s" +
            "\tstartTime: " + msg.getStartTime() +
            "\trouteId: " + msg.getRouteId());
      }

      @Override
      public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("Delivery complete: " + token);
      }
    });
  }
}
