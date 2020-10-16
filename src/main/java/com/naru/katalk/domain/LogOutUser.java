package com.naru.katalk.domain;

public class LogOutUser extends User {

    public static void logIn() {
        System.out.println("Logged In!");
    }

    public static void register(String confirmPassword, Profile profile) {
        System.out.println("Registered!");
    }
}
