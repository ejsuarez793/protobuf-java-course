package com.github.ejsuarez793.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import example.simple.Simple;

import java.util.Arrays;

public class ProtoToJsonMain {
    public static void main(String[] args) throws InvalidProtocolBufferException {

        System.out.println("ProtoToJson Run!");

        Simple.SimpleMessage.Builder builder = Simple.SimpleMessage.newBuilder();

        // simple fields
        builder.setId(42)
                .setIsSimple(true)
                .setName("My Simple Message Name");

        // repeated field
        builder.addSampleList(1)
                .addSampleList(2)
                .addSampleList(3)
                .addAllSampleList(Arrays.asList(4, 5, 6));

        // Print this as JSON
        String jsonString = JsonFormat.printer().print(builder);
        System.out.println(jsonString);


        // parse JSON into Protobuf (if you ever need to do this, not recommended in production)
        Simple.SimpleMessage.Builder builder2 = Simple.SimpleMessage.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(jsonString, builder2);

        System.out.println(builder2);


    }
}
