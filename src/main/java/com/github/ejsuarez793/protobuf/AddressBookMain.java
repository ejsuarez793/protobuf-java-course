package com.github.ejsuarez793.protobuf;

import com.example.tutorial.protos.AddressBook;
import com.example.tutorial.protos.Person;
import com.google.gson.stream.JsonToken;
import com.google.protobuf.Timestamp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class AddressBookMain {
    public static void main(String[] args) throws IOException {
        System.out.println("AddressBookMain Run!");
        Person.PhoneNumber phoneNumber1 = createPhoneNumber("123", Person.PhoneType.WORK);
        Person.PhoneNumber phoneNumber2 = createPhoneNumber("456", Person.PhoneType.MOBILE);
        Person.PhoneNumber phoneNumber3 = createPhoneNumber("789", Person.PhoneType.HOME);
        Person.PhoneNumber phoneNumber4 = createPhoneNumber("10", Person.PhoneType.MOBILE);

        Person person = createPerson(1, "First Person", "example@example.com", Arrays.asList(phoneNumber1, phoneNumber2));
        Person secondPerson = createPerson(2, "Second Person", "example2@example.com", Arrays.asList(phoneNumber4));

        System.out.println("Person Created!");
        System.out.println(person.toString());
        System.out.println("Going to save person to file: person.bin");

        FileOutputStream fileOutputStream = new FileOutputStream("person.bin");
        fileOutputStream.write(person.toByteArray());
        // person.writeTo(fileOutputStream); other way to write person

        System.out.println("Reading person from file: person.bin");
        FileInputStream fileInputStream = new FileInputStream("person.bin");
        Person person2 = Person.parseFrom(fileInputStream);

        System.out.println("New person information:");
        person2 = addAllPhoneNumbers(person2, Arrays.asList(phoneNumber3));
        System.out.println(person2.toString());


        System.out.println("Address Book created!");
        AddressBook addressBook = createAddressBook(Arrays.asList(person2));
        System.out.println(addressBook.toString());


        System.out.println("Address Book updated!");
        addressBook = addAllPersons(addressBook, Arrays.asList(secondPerson));
        System.out.println(addressBook.toString());

    }

    private static AddressBook createAddressBook(List<Person> personList) {
        AddressBook.Builder builder = AddressBook.newBuilder();
        builder.addAllPeople(personList);
        return builder.build();
    }

    private static AddressBook addAllPersons(AddressBook addressBook, List<Person> personList) {
        // you could check if persons is already in address book but it is not relevant for this example
        return addressBook.toBuilder()
                .addAllPeople(personList)
                .build();
    }

    private static Timestamp createTimestamp(){
        Instant instant = Instant.now();
        long seconds = instant.getEpochSecond();
        int nanos = instant.getNano();
        return Timestamp.newBuilder().setSeconds(seconds).setNanos(nanos).build();
    }

    private static Person createPerson(Integer id, String name, String email, List<Person.PhoneNumber> phoneList){
        Timestamp timestamp = createTimestamp();

        return Person.newBuilder()
                .setId(id)
                .setName(name)
                .setEmail(email)
                .setLastUpdated(timestamp)
                .addAllPhones(phoneList)
                .build();
    }

    private static Person.PhoneNumber createPhoneNumber(String phoneNumber, Person.PhoneType phoneType){
        return Person.PhoneNumber.newBuilder()
                .setNumber(phoneNumber)
                .setType(phoneType)
                .build();
    }

    private static Person addAllPhoneNumbers(Person person, List<Person.PhoneNumber> phoneNumber) {
        // you could check if phone is already in person's phone numbers but it is not relevant for this example
        Timestamp timestamp = createTimestamp();

        return person.toBuilder()
                .setLastUpdated(timestamp)
                .addAllPhones(phoneNumber)
                .build();
    }
}
