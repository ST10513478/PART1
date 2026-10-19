/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part1;

/**
 *
 * @author itume
 */
public class Login {
   
//Boolean method that checks if entered username and password match with username and password entered during registration
public boolean checkLoginUser(String enteredUsername, String enteredPassword, String registeredUsername, String registeredPassword)
{
    return enteredUsername.equals(registeredUsername) && enteredPassword.equals(registeredPassword);
}

//Outputs for successful or failed login
public String returnLoginStatus(boolean userLoggedIn, String name, String surname)
        {
    if (userLoggedIn)
    {
        return "Welcome "+name +","+surname+" it is great to see you again.";
    }
    else
    {
        return "Username or password incorrect, please try again";
    }
        }
}