package com.company;

public class Odbiorca implements Runnable
{
    private SkrzynkaPocztowa skrzynkaPocztowa;

    public Odbiorca(SkrzynkaPocztowa skrzynkaPocztowa, String nazwa) {
        this.skrzynkaPocztowa = skrzynkaPocztowa;
        new Thread(this, nazwa).start();
    }

    public void run() {
        for(int i = 0; i < 5; i++) {
            if (!skrzynkaPocztowa.odbierzLiczby()) i--;
        }
    }
}