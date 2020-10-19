package com.naru.katalk.domain;

public class Guest implements User {

    public static void logIn(LoginInformation loginInfo) {
        System.out.println("Logged In!");
    }

    public static void register(MemberInformation userInfo) {
        System.out.println("Registered!");
    }
}
