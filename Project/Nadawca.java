package com.company;

public class Nadawca implements Runnable
{
    private SkrzynkaPocztowa skrzynkaPocztowa;

    public Nadawca(SkrzynkaPocztowa skrzynkaPocztowa, String nazwa) {
        this.skrzynkaPocztowa = skrzynkaPocztowa;
        new Thread(this, nazwa).start();
    }

    public void run() {
        for(int i = 0; i < 5; i++) {
            Integer liczba = (int)(Math.random()* 10+1);
            if (!skrzynkaPocztowa.generujLiczby(liczba)) i--;
        }
    }
}
