/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part1;
import java.util.Random;
/**
 *
 * @author itume
 */
public class Message {
   
    private String recipientNumber;
    private int messageID;
    private int numOfMessages;
    private int messageNumber;
    private String messageHash;
    private String messageStatus;
    private String message;
            
    public Message(String recipienNumber, String message)
    {
        this.messageID = generateMessageID();
        this.recipientNumber = recipientNumber;
        this.message = message;
        numOfMessages++
        this.messageNumber = numOfMessages;
        this.messageHash = createMessageHash();   
    }
    
    private static String generateMessageID()
    {
        Random random = new Random();
        StringBuilder idBuild = new StringBuilder();
        for (int i = 0; 1<maxIDLength; i++)
        {
            idBuild.append(random.nextInt(10));
        }
        return idBuild.toString();
    }
    
    public boolean checkMessageID()
    {
        if (this.messageID == null)
        {
            return false
        }
        int count = 0;
        for (int i = 0; i < this.messageID.length(); i++)
        {
            count++
        }
        return count<=maxIDLength;
    }
    
    public boolean checkRecipientCell()
    {
        String checkResult = PART1.checkUserCellphoneNum(this.recipientNumber);
        return checkResult.equals("cell phone number successfully captured");
    }
    
    public String createMessageHash()
    {
        if (messageID == null || messageID.length() <2)
        {
            return ""
        }
        String firstTwo = messageID.substring(0,2);
        
        String[] words = messageText.split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length -1]: firstWord;
        
        String combinedWords = (fisrtWord + lastWord).toUpperCase();
        
        return fisrtTwo + ":" + messageNumber + ":" + combinedWords;
    }
    
    public String SentMessage(int choice)
    {
        switch (choice)
        {
            case 1:
                this.messageStatus = "Sent";
                return "Message successfully sent"
            case 2:
                this.messageStatus = "Disregarded";
                return "Press 0 to delete the message";
            case 3:
                this.messageStatus = "Stored";
                reurn "Message successfully stored";
            default:
                return "Invalid choice";
                
        }
    }
    
    public String printMessages()
    {
        return "MessageID: " + messageID + " " + "Message Hash: " + messageHash + " " + 
                "Recipient: " + recipientNumber + " " + "Message: " + message;
    }
    public static int returnTotalMessages()
    {
        return numOfMessages;
    }
    }
    

