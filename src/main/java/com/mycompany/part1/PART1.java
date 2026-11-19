/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.part1;

/**
 *
 * @author itume
 */
import java.util.Scanner;
public class PART1 {
        
    public static void main(String[] args) {
        //Scanner for user inputs
        Scanner inputs = new Scanner(System.in);
        String name;
        String surname;
        String username = "";
        String password ="";
        String cellphoneNum ="";
        
        
        System.out.println("welcome to Quickchat");
        System.out.println("select 1 for registracion, 2 for Login");
        
        //Loop to prompt user for inputs while entered inputs are false
        do {            
        //Prompt users for surname
        System.out.println("Enter your name: ");
        name = inputs.nextLine();
        //Prompt users for surname
        System.out.println("Enter your surname: ");
        surname = inputs.nextLine();
        //Prompt users for username
        System.out.println("Enter your username: ");
        username = inputs.nextLine();
        //Prompt users for password
        System.out.println("Enter your password: ");
        password = inputs.nextLine();
        //Prompt users for cellphone number
        System.out.println("Enter your cell phone number: ");
        cellphoneNum = inputs.nextLine();
        
        //Print registration results
        System.out.println(checkUsername(username));
        System.out.println(checkUserPassword(password));
        System.out.println(checkUserCellphoneNum(cellphoneNum));
        }
        while (!
                checkUsername(username).contains("successfully")||!checkUserPassword(password).contains("successfully")||!checkUserCellphoneNum(cellphoneNum).contains("successfully"));
        
        //Login process
        String loginUsername;
        String loginPassword;
        boolean userLoggedIn;
        
        System.out.println("Account succefully registered, proceed to login.");
        do{
        Login userLogin = new Login();
        System.out.println("Enter your username: ");
        loginUsername = inputs.nextLine();
        
        System.out.println("Enter your password: ");
        loginPassword = inputs.nextLine();
        
        userLoggedIn = userLogin.checkLoginUser(loginUsername, loginPassword, username, password);
        String loginStatus = userLogin.returnLoginStatus(userLoggedIn, name, surname);
        System.out.println(loginStatus);
        }
        while(!userLoggedIn);
        
        System.out.println("Welcome to QuickChat");
        System.out.println("How many messages would you like to enter?");
        int numMessages = Integer.parseInt(inputs.nextLine().trim());
        int messagesEntered = 0;
        //Message process
        boolean running = true;
        while (running)
        {
            System.out.println("Choose an option:");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            
            int choice = Integer.parseInt(inputs.nextLine());
            
            switch (choice)
            {
                case 1:
                    if (messagesEntered >= numMessages) {
                        System.out.println("You have already entered all "+numMessages+" messages.");
                        break;}
                    
                    while (messagesEntered < numMessages)
                    {
                        System.out.println("Enter recipinet cell phone number:");
                        String recipientNum = inputs.nextLine();
                        
                        System.out.println("Enter message(max  250 characters):");
                        String msgText = inputs.nextLine();
                        
                        Message myMessage = new Message(recipientNum, msgText);
                        
                        if (!myMessage.checkRecipientCell())
                        {
                            System.out.println(checkUserCellphoneNum(recipientNum));
                               
                        }
                        else if (!myMessage.checkMessageLength())
                                    {
                                        System.out.println("Please enter a message of less than 250 characters.");
                                        
                                    }
                        else if (!myMessage.checkMessageID())
                        {
                            System.out.println("Message ID is invalid.");
                            
                        }
                        else
                        {
                            System.out.println("Message ready to send.");
                            System.out.println("Choose action: 1. Send | 2. Disregard | 3. Store");
                            int actionChoice = Integer.parseInt(inputs.nextLine());
                            System.out.println(myMessage.SentMessage(actionChoice));
                        
                            if (actionChoice == 1)
                            {
                                System.out.println("Message Details");
                                System.out.println(myMessage.printMessages());
                            }
                            messagesEntered++;
                        }
                    }
                    System.out.println("Total messages processed: " + Message.returnTotalMessages());
                    break;
                    
                case 2:
                    System.out.println("Coming Soon.");
                    break;
                
                case 3:
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;
                    
                case 4:
                    boolean inStored = true;
                    while (inStored)
                    {
                        System.out.println("Stored Messages:");
                        System.out.println("1 - Display sender and recipient of all stored messages:");
                        System.out.println("2 - Display Stored Messages:");
                        System.out.println("3 - Search for a message ID:");
                        System.out.println("4 - Search for all messages for a recipient");
                        System.out.println("5 - Delete a message using the message hash");
                        System.out.println("6 - Display a report of all stored messages");
                        System.out.println("7 - Back to main menu");
                        
                        int storedChoice = Integer.parseInt(inputs.nextLine().trim());
                        
                        switch (storedChoice)
                        {
                            case 1:
                                System.out.println(Message.displaySenderAndRecipient(name + " " + surname));
                                break;
                            case 2:
                                System.out.println(Message.longestMessaage());
                                break;
                            case 3:
                                System.out.print("Enter the message ID:");
                                System.out.println(Message.searchByID(inputs.nextLine().trim()));
                                break;
                            case 4:
                                System.out.print("Enter the c number:");
                                System.out.println(Message.searchByRecipient(inputs.nextLine().trim()));
                                break;
                            case 5:
                                System.out.print("Enter the message hash:");
                                System.out.println(Message.deleteByHash(inputs.nextLine().trim().toUpperCase()));
                                break;
                            case 6:
                                System.out.println(Message.displayReport(Message.storedMessages));
                                break;
                            case 7:
                                inStored = false;
                                break;
                            default:
                                System.out.println("Invalid option, please try again");
                                break;
                        }
                    }
                    
                default:
                    System.out.println("Invalid option, please try again");
            }
        }
    }
        
    //Methods to validate registration inputs
    static String checkUsername( String userAccountName)
            
        {
        
        int count = userAccountName.length();
        if (count<=5 && userAccountName.contains("_"))
        {
            return "Username successfully captured.";
        }
        else
        {
            return "Username is not correctly formatted "
                  +"please ensure that your username contains an underscore "
                  +"and is no more than five charactrers long";
        }
        }
    
        static String checkUserPassword( String userPassword){
        /*Declaration of counts to check if which character is entered,
        and if required characters are entered, because contins can only check for specific values */  
        int count1 = userPassword.length();
        int uppercaseCount = 0;
        int numCount = 0;
        int specialCharCount = 0;
        
        for (int i = 0; i < count1; i++)
        {
            //textbook, Table 7-1, pg333
            //checking for uppercase in password
            char aChar = userPassword.charAt(i);
            if (Character.isUpperCase(aChar))
            {
                uppercaseCount++;
            }
            
            //checking for uppercase in password
            else if (Character.isDigit(aChar))
            {
                numCount++;
            }
            
            //checking for uppercase in password
            else if (!Character.isLetterOrDigit(aChar))
            {
                specialCharCount++;
            }
        }
        
        if (count1>=8 && uppercaseCount>=1 && numCount>=1 && specialCharCount>=1)
        {
            return("Password successfully captured.");
        }
        else
        {
            return("Password is not correctly formatted "
                    +"please ensure that your password contains eight characters, "
                    +"a capital letter, a number, a special character.");
        }
        
        
        }
      static String checkUserCellphoneNum( String phoneNum)
            
        {
        //Regex
        String regex = "^\\+\\d{1,3}\\d{1,10}$";
        
        if (phoneNum.matches(regex))
        {
            return "Cell phone number successfully captured.";
        }
        else
        {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
                  
        } 
        }
}
