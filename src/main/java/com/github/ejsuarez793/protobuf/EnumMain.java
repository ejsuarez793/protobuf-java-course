package com.github.ejsuarez793.protobuf;

import example.enumerations.EnumExample;
import example.enumerations.EnumExample.EnumMessage;

public class EnumMain {
    public static void main(String[] args) {
        System.out.println("Examplee for Enums");

        EnumMessage.Builder builder = EnumMessage.newBuilder();

        builder.setId(345);

        builder.setDayOfWeek(EnumExample.DayOfWeek.FRIDAY);

        EnumMessage message = builder.build();

        System.out.println(message);
    }
}
