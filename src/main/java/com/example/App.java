package com.example;

public class App {

    public static int add(int a, int b) {
        return a + b;
    }

    public static String greet(String name) {
        return "Hello, " + name + "!";
    }

    public static void main(String[] args) {
        System.out.println(greet("Jenkins CI/CD"));
        System.out.println("Addition: " + add(10, 20));
    }
}
