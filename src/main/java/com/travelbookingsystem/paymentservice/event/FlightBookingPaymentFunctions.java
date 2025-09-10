package com.travelbookingsystem.paymentservice.event;

import com.travelbookingsystem.paymentservice.event.messages.FlightBookingPaymentProcessedMessage;
import com.travelbookingsystem.paymentservice.event.messages.FlightBookingPaymentRequestedMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import java.util.function.Function;

@Slf4j
@Configuration
public class FlightBookingPaymentFunctions {

    @Bean
    public Function<FlightBookingPaymentRequestedMessage, Long> processPayment () {
        return message -> {
            log.info("Processing payment for Flight booking ID {} ...", message.id());
            return message.id();
        };
    }

    @Bean
    public Function<Flux<Long>, Flux<FlightBookingPaymentProcessedMessage>> publishPaymentProcessedEvent() {
        return bookingIdFlux -> bookingIdFlux.map(id -> {
            log.info("Payment processed for flight booking ID {}.", id);
            return new FlightBookingPaymentProcessedMessage(id);
        });
    }

}
