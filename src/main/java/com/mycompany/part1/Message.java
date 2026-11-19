/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part1;
import java.util.Random;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;

/**
 *
 * @author itume
 */
public class Message {
   
    private String recipientNumber;
    private String messageID;
    private static int numOfMessages = 0;
    private static String allSentMessages ="";
    private int messageNumber;
    private String messageHash;
    private String messageStatus;
    private String message;
    private int maxIDLength = 10;
    
    public static ArrayList<Message> sentMessages = new ArrayList<>();
    public static ArrayList<Message> disregardedMessages = new ArrayList<>(); 
    public static ArrayList<Message> storedMessages = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();
    
    
    public Message(String recipientNumber, String message)
    {
        this.messageID = generateMessageID();
        this.recipientNumber = recipientNumber;
        this.message = message;
        this.messageNumber = numOfMessages;
        this.messageHash = createMessageHash();   
    }
    
    private String generateMessageID()
    {
        Random random = new Random();
        StringBuilder idBuild = new StringBuilder();
        for (int i = 0; i<maxIDLength; i++)
        {
            idBuild.append(random.nextInt(10));
        }
        return idBuild.toString();
    }
    
    public boolean checkMessageID()
    {
        if (this.messageID == null)
        {
            return false;
        }
        int count = 0;
        for (int i = 0; i < this.messageID.length(); i++)
        {
            count++;
        }
        return count<=maxIDLength;
    }
    
    public boolean checkRecipientCell()
    {
        String checkResult = PART1.checkUserCellphoneNum(this.recipientNumber);
        return checkResult.equals("Cell phone number successfully captured.");
    }
    
    public String createMessageHash()
    {
        if (messageID == null || messageID.length() <2)
        {
            return "";
        }
        String firstTwo = messageID.substring(0,2);
        String[] words = message.split(" \\s+");
        String firstWord = words[0].replaceAll("[^A-Za-z0-9]", "");
        String lastWord =  words[words.length -1].replaceAll("[^A-Za-z0-9]","");
        
        String combinedWords = (firstWord + lastWord).toUpperCase();
        
        return firstTwo + ":" + messageNumber + ":" + combinedWords;
    }
    
    public String SentMessage(int choice)
    {
        switch (choice)
        {
            case 1:
                this.messageStatus = "Sent";
                numOfMessages++;
                allSentMessages = allSentMessages + messageDetails()+"\n\n";
                return "Message successfully sent.";
            case 2:
                this.messageStatus = "Disregarded";
                return "Press 0 to delete the message.";
            case 3:
                this.messageStatus = "Stored";
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid choice";
                
        }
    }
    public boolean checkMessageLength()
    {
        return this.message.length() <=250;
    }
    
    public String messageDetails()
    {
        return "Message ID: "+messageID+ "\n"
                + "Message Hash: "+messageHash+"\n"
                + "Recipient: "+recipientNumber +"\n"
                + "Message: "+message;
    }
    
    public String printMessages()
    {
        /*return "MessageID: " + messageID + " " + "Message Hash: " + messageHash + " " + 
                "Recipient: " + recipientNumber + " " + "Message: " + message;*/
        return allSentMessages;
    }
    public static int returnTotalMessages()
    {
        return numOfMessages;
    }
    public void storeMessage()
    {
        JSONObject messageJSON = new JSONObject();
        messageJSON.put("messageID", messageID); 
        messageJSON.put("messageHash", messageHash);
        messageJSON.put("Recipient", recipientNumber);
        messageJSON.put("message", message);
        
        try {
            FileWriter file = new FileWriter("messages.json", true);
            file.write(messageJSON.toJSONString() + "\n");
            file.close();
        } catch (Exception e) {
            System.out.println("Messages could not be stored");
        }
        
    }
    }
    

