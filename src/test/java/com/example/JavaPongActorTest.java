package com.example;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.junit.Test;
import scala.concurrent.Future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static scala.compat.java8.FutureConverters.*;

import akka.pattern.Patterns;

import static org.junit.Assert.*;

public class JavaPongActorTest {

    ActorSystem actorSystem = ActorSystem.create();
    ActorRef actorRef = actorSystem.actorOf(Props.create(JavaPongActor.class));

    @Test
    public void testFunc() throws Exception {
        final Future sFuture = Patterns.ask(actorRef, "Ping", 1000);
        final CompletionStage<String> cs = toJava(sFuture);
        final CompletableFuture<String> jFuture = (CompletableFuture<String>) cs;
        assert (jFuture.get(1000, TimeUnit.MILLISECONDS).equals("Pong"));
        System.out.println(actorRef.path());
    }

    @Test
    public void printToConsole() throws Exception {
//Ex1
//        askPong("Ping").thenAccept(x -> System.out.println("replied with: " + x));
//        Thread.sleep(100);

//Ex2
        CompletionStage<Character> futureFuture = askPong("Ping").thenApply(x -> x.charAt(0));
        CompletableFuture<Character> cf = (CompletableFuture<Character>) futureFuture;

//Ex3
//        CompletionStage<String> futureFuture = askPong("Ping").thenCompose(x -> askPong("Ping"));
//        CompletableFuture<String> cf = (CompletableFuture<String>) futureFuture;

//Ex4
//        CompletionStage<String> futureFuture = askPong("Ping").handle((pong,ex) -> ex == null ? CompletableFuture.completedFuture(pong) : askPong("Pinga")).thenCompose(x -> x);
//        CompletableFuture<String> cf = (CompletableFuture<String>) futureFuture;

//Ex5
//        CompletionStage<String> futureFuture = askPong("Ping").thenCompose(x -> askPong("Ping" + x)).handle(
//                (x, t) -> {
//                    if (t != null) {
//                        return "default";
//                    } else {
//                        return x;
//                    }
//                });
//        CompletableFuture<String> cf = (CompletableFuture<String>) futureFuture;

//Ex6
//        CompletionStage<String> futureFuture = askPong("Ping").thenCombine(askPong("Ping"),
//                (a, b) -> {
//                    return a + b;
//                });
//        CompletableFuture<String> cf = (CompletableFuture<String>) futureFuture;

//Common
        System.out.println(cf.get());
    }

    public CompletionStage<String> askPong(String message) {
        Future sFuture = Patterns.ask(actorRef, message, 1000);
        CompletionStage<String> cs = toJava(sFuture);
        return cs;
    }
}