//package com.travelbookingsystem.paymentservice;
//
//import com.travelbookingsystem.paymentservice.event.messages.FlightBookingPaymentProcessedMessage;
//import com.travelbookingsystem.paymentservice.event.messages.FlightBookingPaymentRequestedMessage;
//import lombok.AccessLevel;
//import lombok.experimental.FieldDefaults;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.function.context.FunctionCatalog;
//import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
//import reactor.core.publisher.Flux;
//import reactor.test.StepVerifier;
//
//import java.util.function.Function;
//
//@FunctionalSpringBootTest
//@FieldDefaults(level = AccessLevel.PRIVATE)
//class FlightBookingPaymentFunctionsIntegrationTests {
//
//    @Autowired
//    FunctionCatalog functionCatalog;
//
//    @Test
//    void processPaymentAndPublishPaymentProcessedEvent() {
//        Function<FlightBookingPaymentRequestedMessage, Flux<FlightBookingPaymentProcessedMessage>>
//                composed = functionCatalog.lookup(Function.class, "processPayment|publishPaymentProcessedEvent");
//        long flightBookingId = 576;
//
//        StepVerifier.create(composed.apply(
//                new FlightBookingPaymentRequestedMessage(flightBookingId)
//        )).expectNextMatches(message->
//                message.equals(new FlightBookingPaymentProcessedMessage(flightBookingId))
//        ).verifyComplete();
//    }
//
//}
