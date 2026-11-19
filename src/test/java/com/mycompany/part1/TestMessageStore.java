/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.part1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

/**
 *
 * @author itume
 */
public class TestMessageStore {
   private Message m1, m2, m3, m4, m5;

    @BeforeEach
    void setUp() {
        Message.storageFile = "test-messages.json";
        new File("test-messages.json").delete();
        Message.resetCounters();

        //Test data messages 1 to 5 (fixed IDs so the tests are predictable)
        m1 = new Message("1111111111", "+27834557896", "Did you get the cake?", 0);
        m2 = new Message("2222222222", "+27838884567",
                "Where are you? You are late! I have asked you to be on time.", 1);
        m3 = new Message("3333333333", "+27834484567", "Yohoooo, I am at your gate.", 2);
        m4 = new Message("0838884567", "0838884567", "It is dinner time!", 3);
        m5 = new Message("5555555555", "+27838884567", "Ok, I am leaving without you.", 4);

        m1.SentMessage(1);   // Sent
        m2.SentMessage(3);   // Stored
        m3.SentMessage(2);   // Disregard
        m4.SentMessage(1);   // Sent
        m5.SentMessage(3);   // Stored
    }

    @AfterEach
    void cleanUp() {
        new File("test-messages.json").delete();
        Message.storageFile = "messages.json";
    }

    @Test
    void sentMessagesArrayCorrectlyPopulated() {
        assertEquals(2, Message.sentMessages.size());
        assertEquals("Did you get the cake?", Message.sentMessages.get(0).getMessage());
        assertEquals("It is dinner time!", Message.sentMessages.get(1).getMessage());
    }

    @Test
    void otherArraysCorrectlyPopulated() {
        assertEquals(1, Message.disregardedMessages.size());
        assertEquals("Yohoooo, I am at your gate.", Message.disregardedMessages.get(0).getMessage());
        assertEquals(2, Message.storedMessages.size());
        assertEquals(5, Message.messageIDs.size());
        assertEquals(5, Message.messageHashes.size());
    }

    @Test
    void storedArrayIsReadFromJSONFile() {
        Message.storedMessages.clear();
        Message.loadStoredMessages();

        assertEquals(2, Message.storedMessages.size());
        assertEquals(m2.getMessageID(), Message.storedMessages.get(0).getMessageID());
        assertEquals("Ok, I am leaving without you.", Message.storedMessages.get(1).getMessage());
    }

    @Test
    void displayTheLongestMessage() {
        assertEquals("Where are you? You are late! I have asked you to be on time.",
                Message.longestMessage());
    }

    @Test
    void searchForMessageID() {
        assertEquals("It is dinner time!", Message.searchByID("0838884567"));
    }

    @Test
    void searchForMessageIDNotFound() {
        assertEquals("Message ID not found.", Message.searchByID("9999999999"));
    }

    @Test
    void searchAllMessagesForARecipient() {
        assertEquals("Where are you? You are late! I have asked you to be on time.\n"
                + "Ok, I am leaving without you.",
                Message.searchByRecipient("+27838884567"));
    }

    @Test
    void deleteMessageUsingHash() {
        assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" "
                + "successfully deleted.",
                Message.deleteByHash(m2.getMessageHash()));
        assertEquals(1, Message.storedMessages.size());

        //The JSON file is updated too
        Message.storedMessages.clear();
        Message.loadStoredMessages();
        assertEquals(1, Message.storedMessages.size());
    }

    @Test
    void displayReport() {
        String report = Message.displayReport(Message.sentMessages);

        assertTrue(report.contains(m1.getMessageHash()));
        assertTrue(report.contains("+27834557896"));
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains(m4.getMessageHash()));
        assertTrue(report.contains("0838884567"));
        assertTrue(report.contains("It is dinner time!"));
    } 

}
