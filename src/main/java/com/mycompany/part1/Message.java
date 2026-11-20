/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part1;
import java.io.BufferedReader;
import java.io.File;
import java.util.Random;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
    
    public static String storageFile = "messages.json";
    
    public Message(String recipientNumber, String message)
    {
        this.messageID = generateMessageID();
        this.recipientNumber = recipientNumber;
        this.message = message;
        this.messageNumber = numOfMessages;
        this.messageHash = createMessageHash();   
    }
    
    public Message(String messageID, String recipientNumber, String message, int messageNumber)
    {
        this.messageID = messageID;
        this.recipientNumber = recipientNumber;
        this.message = message;
        this.messageNumber = messageNumber;
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
        String[] words = message.split("\\s+");
        String firstWord = words[0].replaceAll("[^A-Za-z0-9]", "");
        String lastWord =  words[words.length -1].replaceAll("[^A-Za-z0-9]","");
        
        String combinedWords = (firstWord + lastWord).toUpperCase();
        
        return firstTwo + ":" + messageNumber + ":" + combinedWords;
    }
    
    public String SentMessage(int choice)
    {
        if (choice >= 1 && choice <=3)
        {
            messageIDs.add(messageID);
            messageHashes.add(messageHash);   
        }
        
        switch (choice)
        {
            case 1:
                this.messageStatus = "Sent";
                numOfMessages++;
                sentMessages.add(this);
                allSentMessages = allSentMessages + messageDetails()+"\n\n";
                return "Message successfully sent.";
            case 2:
                this.messageStatus = "Disregarded";
                disregardedMessages.add(this);
                return "Press 0 to delete the message.";
            case 3:
                this.messageStatus = "Stored";
                storeMessage();
                storedMessages.add(this);
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
        return allSentMessages;
    }
    
    public static int returnTotalMessages()
    {
        return numOfMessages;
    }
       
    
    //Getters
    public String getMessageID(){return messageID;}
    public String getMessageHash(){return messageHash;}
    public String getMessage(){return message;}
    public String getRecipient(){return recipientNumber;}
    
    private JSONObject toJSON()
    {
        JSONObject messageJSON = new JSONObject();
        messageJSON.put("messageID", messageID); 
        messageJSON.put("messageHash", messageHash);
        messageJSON.put("messageNumber", messageNumber);
        messageJSON.put("recipient", recipientNumber);
        messageJSON.put("message", message);
        return messageJSON;
    }   
    
    public void storeMessage()
    {
        try {
            FileWriter file = new FileWriter("messages.json", true);
            file.write(toJSON().toJSONString() + "\n");
            file.close();
        } catch (Exception e) {
            System.out.println("Messages could not be stored");
        }
    }    
    
    
    public static void loadStoredMessages()
    {
        storedMessages.clear();
        File file = new File(storageFile);
        
        if (!file.exists())
        {
            return;
        }
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            JSONParser parser = new JSONParser();
            String line;
            
            while ((line = reader.readLine()) != null)
            {
                if (line.trim().isEmpty())
                {
                    continue;
                }
                
                JSONObject obj = (JSONObject) parser.parse(line);
                String id = (String) obj.get("messageID");
                String recipient = (String) obj.get("recipient");
                String text = (String) obj.get("message");
                int number = ((Long) obj.get("messageNumber")).intValue();
                
                Message m = new Message(id, recipient, text, number);
                m.messageStatus = "Stored";
                storedMessages.add(m);
                messageIDs.add(m.getMessageID());
                messageHashes.add(m.getMessageHash());
            }
            reader.close();
            
        } catch (Exception e) {
            System.out.println("Could not read the stored messages.");
        }
    }
    
    public static String displaySenderAndRecipient(String sender) {
        if (storedMessages.isEmpty())
        {
            return "No Stored messages.";
        }
        
        String result = "";
        for (Message m: storedMessages)
        {
            result = result + "Sender: "+sender +" | Recipient: " + m.recipientNumber + "\n";
            
        }
        return result;
    }
    public static String longestMessage()
    {
        if (storedMessages.isEmpty())
        {
            return "No stored messages.";
        }
        
        String longest = storedMessages.get(0).message;
        for (Message m: storedMessages)
        {
            if (m.message.length() > longest.length())
            {
                longest = m.message;
            }
        }
        return longest;
    }
    
    //Search for message using message ID
    public static String searchByID(String id)
    {
        for (Message m : sentMessages)
        {
            if (m.messageID.equals(id))
            {
                return m.message;
            }
        }
        for (Message m : storedMessages)
        {
           if (m.messageID.equals(id))
            {
                return m.message;
            } 
        }
        return "Message ID not found.";
    }
    
    public static String searchByRecipient(String recipient)
    {
        String result ="";
        for (Message m : sentMessages)
        {
            if (m.recipientNumber.equals(recipient))
            {
                result = result + m.message + "\n";
            }
        }
        for (Message m : storedMessages)
        {
            if (m.recipientNumber.equals(recipient))
            {
                result = result + m.message + "\n";
            }
        }
        if (result.equals(""))
        {
            return "No messages found for that recipient.";
        }
        return result.trim();
    }
    
    public static String deleteByHash(String hash)
    {
        for(int i = 0; i < storedMessages.size(); i++)
        {
            if (storedMessages.get(i).messageHash.equals(hash))
                    {
                        String text = storedMessages.get(i).message;
                        storedMessages.remove(i);
                        rewriteStoredFile();
                        return "Message: \"" + text +"\" Successfully deleted.";
                    }
        }
        return "Message hash not found.";
    }
    
    private static void rewriteStoredFile()
    {
        try {
            FileWriter file = new FileWriter(storageFile, false);
            for (Message m: storedMessages)
            {
                file.write(m.toJSON().toJSONString() + "\n");
            }
            file.close();
        } catch (Exception e) {
            System.out.println("Could not update the stored messages file.");
        }
    }
    
    public static String displayReport(ArrayList<Message>list)
    {
        if (list.isEmpty())
        {
            return "No messages to report.";
        }
        String report = "";
        for (Message m : list)
        {
            report = report + m.messageDetails() + "\n\n";
        }
        return report;
    }
    
    public static void resetCounters()
    {
        numOfMessages = 0;
        allSentMessages ="";
        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();
    }
    
    public String messageLengthResult()
    {
        if(checkMessageLength()) {return "Message ready to send.";}
        return "Message exceeds 250 characters by " + (message.length() - 250) + " please reduce the size.";
    }
    
    public String messageIDGenerated()
    {
        return "Message ID generated: " + messageID;
    }

    }
    

