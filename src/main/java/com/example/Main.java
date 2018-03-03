package com.example;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
    public static void main(String... args){
        ActorSystem system = ActorSystem.create("akkademy");
        ActorRef actorRef = system.actorOf(Props.create(AkkademyDb.class), "akkademydb");
        System.out.println(actorRef.path());
    }
}
