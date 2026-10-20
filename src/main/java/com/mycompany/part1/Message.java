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
    private String messageHash;
    private String messageStatus;
    private String message;
            
    public Message(String recipienNumber, String message)
    {
        this.messageID = newMessageID();
        this.recipientNumber = recipientNumber;
        this.message = message; 
    }
    private static String generateMessageID()
    {
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
        String checkResult = checkUserCellphoneNum(this.recipientNumber);
        return checkResult.equals("cell phone number successfully captured");
    }
    public String createMessageHash()
    {
        String firstTwo = messageID.substring(0,2)
        String words = messageText.split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length -1]
    }
    }
          
    }
    // System.out.println("Welcome to QuickChat");
}
