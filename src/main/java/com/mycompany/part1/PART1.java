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
        String name;
        String surname;
        String username;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
    }
    static String accountRegistration(String userName, String userSurname, String userAccountName, String userPassword)
        {
        Scanner inputs = new Scanner(System.in);
        //Prompt users for surname
        System.out.println("Enter your name: ");
        userName = inputs.nextLine();
        //Prompt users for surname
       System.out.println("Enter your name: ");
        userSurname = inputs.nextLine();
        //Prompt users for username
        System.out.println("Enter your username: ");
        userAccountName = inputs.nextLine();
        int count = userAccountName.length();
        if (count<=5 && userAccountName.contains("_"))
        {
            System.out.println("Username successfully captured.");
        }
        else
        {
            System.out.println("Username is not correctly formatted"
                            +"please ensure thst your useername contains an underscore "
                            +"and is no more than five charactrers long");
        }
        //Prompt users for password
        System.out.println("Enter your username: ");
        userPassword = inputs.nextLine();
        int count = userPassword.length();
        if (count<=8 && userPassword.contains("_"))
        {
            System.out.println("Password successfully captured.");
        }
        else
        {
            System.out.println("Password is not correctly formatted"
                            +"please ensure thst your useername contains eight charactrers, "
                            +"a capital letter, a number, a special character.");
        }
        
        
        }
}
