package com.naru.katalk.domain;

public class LogOutUser extends User {

    public LogOutUser() {
        role = Role.GUEST;
    }

    public static void logIn(LoginInformation loginInfo) {
        System.out.println("Logged In!");
    }

    public static void register(UserInformation userInfo) {
        System.out.println("Registered!");
    }
}
