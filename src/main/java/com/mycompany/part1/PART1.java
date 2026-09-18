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
        
        System.out.println(checkUsername(username));
        System.out.println(checkUserPassword(password));
        System.out.println(checkUserCellphoneNum(cellphoneNum));
        
        
      
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
                    +"please ensure thst your username contains eight charactrers, "
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
