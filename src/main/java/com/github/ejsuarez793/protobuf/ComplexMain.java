package com.github.ejsuarez793.protobuf;

import example.complex.Complex.ComplexMessage;
import example.complex.Complex.DummyMessage;

import java.util.Arrays;

public class ComplexMain {
    public static void main(String[] args) {
        System.out.println("Complex example");

        DummyMessage oneDummy = newDummyMessage(55, "Name");

        ComplexMessage.Builder builder = ComplexMessage.newBuilder();

        builder.setOneDummy(oneDummy);

        builder.addMultipleDummy(newDummyMessage(66, "second dummy"));
        builder.addMultipleDummy(newDummyMessage(67, "third dummy"));
        builder.addMultipleDummy(newDummyMessage(68, "fourth dummy"));

        builder.addAllMultipleDummy(Arrays.asList(newDummyMessage(69, "other dummy"),
                newDummyMessage(70, "other other dummy")));

        ComplexMessage message = builder.build();

        System.out.println(message.toString());
    }

    public static DummyMessage newDummyMessage(Integer id, String name){
        DummyMessage.Builder builder = DummyMessage.newBuilder();
        DummyMessage message = builder.setId(id)
                .setName(name)
                .build();
        return message;
    }
}
