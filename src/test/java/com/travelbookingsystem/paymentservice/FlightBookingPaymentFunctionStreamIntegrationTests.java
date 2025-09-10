package com.travelbookingsystem.paymentservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelbookingsystem.paymentservice.event.messages.FlightBookingPaymentProcessedMessage;
import com.travelbookingsystem.paymentservice.event.messages.FlightBookingPaymentRequestedMessage;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.MessageBuilder;
import java.io.IOException;

@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
@Import(TestChannelBinderConfiguration.class)
class FlightBookingPaymentFunctionStreamIntegrationTests {

    @Autowired
    InputDestination input;

    @Autowired
    OutputDestination output;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void whenPaymentProcessedPublishPaymentProcessedEvent () throws IOException {
        long flightBookingId = 143;
        var inputMessage = MessageBuilder.withPayload(new FlightBookingPaymentRequestedMessage(flightBookingId)).build();
        var outputMessage = MessageBuilder.withPayload(new FlightBookingPaymentProcessedMessage(flightBookingId)).build();

        input.send(inputMessage);
        Assertions.assertThat(
                objectMapper.readValue(output.receive().
                        getPayload(),
                        FlightBookingPaymentProcessedMessage.class
                )
        ).isEqualTo(outputMessage.getPayload());
    }

}
