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
        //Prompt users for surname
        System.out.println("Enter your name: ");
        String name = inputs.nextLine();
        //Prompt users for surname
       System.out.println("Enter your surname: ");
        String surname = inputs.nextLine();
        //Prompt users for username
        System.out.println("Enter your username: ");
        String username = inputs.nextLine();
        //Prompt users for password
        System.out.println("Enter your password: ");
        String password = inputs.nextLine();
        //Prompt users for cellphone number
        System.out.println("Enter your cell phone number: ");
        String cellphoneNum = inputs.nextLine();
        //Print registration results
        System.out.println(checkUsername(username));
        System.out.println(checkUserPassword(password));
        System.out.println(checkUserCellphoneNum(cellphoneNum));
        
        //Login process
        Login userLogin = new Login();
        System.out.println("Enter your username: ");
        String loginUsername = inputs.nextLine();
        
        System.out.println("Enter your password: ");
        String loginPassword = inputs.nextLine();
        
        boolean userLoggedIn = userLogin.checkLoginUser(loginUsername, loginPassword, username, password);
        String loginStatus = userLogin.returnLoginStatus(userLoggedIn, name, surname);
        System.out.println(loginStatus);
        
        // System.out.println("Welcome to QuickChat");
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
                    System.out.println("How many messages would you like to enter?");
                    int numMessages = Integer.parseInt(inputs.nextLine());
                    
                    for (int i = 0; i < numMessages; i++)
                    {
                        System.out.println("Enter recipinet cell phone number:");
                        String recipientNum = inputs.nextLine();
                        
                        System.out.println("Enter message(max  250 characters):");
                        String msgText = inputs.nextLine();
                        
                        if (Message.checkMessageLength() && Message.checkRecipientCell() && Message.checkMessageID())
                        {
                            System.out.println("Choose action: 1. Send | 2. Disregard | 3. Store");
                            int actionChoice = Integer.parseInt(inputs.nextLine());
                            System.out.println(Message.SentMessage(action));
                        
                            if (actionChoice == 1)
                            {
                                System.out.println("Message Details");
                                System.out.println(Message.printMessages());
                            }
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
                  +"please ensure thst your username contains an underscore "
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
        
        if (count1>=8 && uppercaseCount>1 && numCount>1 && specialCharCount>1)
        {
            return("Password successfully captured.");
        }
        else
        {
            return("Password is not correctly formatted "
                    +"please ensure that your password contains eight charactrers, "
                    +"a capital letter, a number, a special character.");
        }
        
        
        }
      static String checkUserCellphoneNum( String phoneNum)
            
        {
        //Regex
        String regex = "^\\+\\d{1,3}\\d{1,10}$";
        
        if (phoneNum.matches(regex))
        {
            return "Cell phone number successfully added.";
        }
        else
        {
            return "Cell phone number is incorrectly formatted or does not contain international code";
                  
        } 
        }
}
