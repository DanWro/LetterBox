package com.company;

public class Main {
    public static void main(String[] args) {
        SkrzynkaPocztowa SP = new SkrzynkaPocztowa(3);

        for (int i = 1; i < 3; i++) {
            new Nadawca(SP, "P" + Integer.toString(i));
            new Odbiorca(SP, "P" + Integer.toString(i+2));
        }
    }
}
