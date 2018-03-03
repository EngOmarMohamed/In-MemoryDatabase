package com.example;

import akka.actor.AbstractActor;
import akka.actor.Status;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.example.SetRequest;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;
import scala.runtime.Statics;

import java.util.HashMap;
import java.util.Map;


public class AkkademyDb extends AbstractActor {
    protected final LoggingAdapter log = Logging.getLogger(context().system(), this);
    protected final Map<String, Object> map = new HashMap<>();

//Ex1
//    private AkkademyDb() {
//        receive(ReceiveBuilder.match(SetRequest.class, message -> {
//            log.info("Received set requst - key:{} value: {}", message.getKey(), message.getValue());
//            map.put(message.getKey(), message.getValue());
//        }).matchAny(o -> log.info("received unknown message {}", o)).build());
//
//    }

    private AkkademyDb() {
        receive(
                ReceiveBuilder.match(SetRequest.class, message -> {

                    log.info("Received set requst - key:{} value: {}", message.getKey(), message.getValue());
                    map.put(message.getKey(), message.getValue());
                    sender().tell(new Status.Success(message.getKey()), self());

                }).match(GetRequest.class, message -> {

                    log.info("Received Get Request: {}", message);
                    Object value = map.get(message.key);
                    Object response = (value != null) ? value : new Status.Failure(new KeyNotFoundException(message.key));
                    sender().tell(response, self());

                }).matchAny(o -> {

                    log.info("received unknown message {}", o);
                    sender().tell(new Status.Failure(new ClassNotFoundException()), self());

                }).build()
        );
    }

    //Ex2
//    public PartialFunction rece() {
//        return ReceiveBuilder.match(SetRequest.class, message -> {
//
//            log.info("Received set requst - key:{} value: {}", message.getKey(), message.getValue());
//            map.put(message.getKey(), message.getValue());
//            sender().tell(new Status.Success(message.getKey()), self());
//
//        }).match(GetRequest.class, message -> {
//
//            log.info("Received Get Request: {}", message);
//            Object value = map.get(message.key);
//            Object response = (value != null) ? value : new Status.Failure(new KeyNotFoundException(message.key));
//            sender().tell(response, self());
//
//        }).matchAny(o -> {
//
//            log.info("received unknown message {}", o);
//            sender().tell(new Status.Failure(new ClassNotFoundException()), self());
//
//        }).build();
//    }
}
