package com.company;

import java.util.ArrayList;
import java.util.List;

public class SkrzynkaPocztowa
{
    private Integer pojemnoscSkrzynki;
    private Integer liczbaLiczb = 0;
    private List<Integer> liczbySkrzynka = new ArrayList<Integer>();
    private Boolean pustaSkrzynia = true;
    private Boolean pelnaSkrzynia = false;

    public SkrzynkaPocztowa(Integer pojemnosc)
    {
        this.pojemnoscSkrzynki = pojemnosc;
    }

    public synchronized boolean generujLiczby(Integer liczba) {
        Integer liczbaDoDodania = liczba;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Przerwana transakcja.");
        }

        while (pelnaSkrzynia) {
            System.out.println("Skrzynka pełna, należy poczekać na miejsce..." + Thread.currentThread().getName());
            try {
                wait();
            } catch (InterruptedException e) {}
            return false;
        }
        liczbySkrzynka.add(liczbaDoDodania);
        System.out.println(liczbaDoDodania + " dodana do skrzynki." + Thread.currentThread().getName());
        this.liczbaLiczb++;
        pustaSkrzynia = false;
        if (this.liczbaLiczb.equals(this.pojemnoscSkrzynki)) pelnaSkrzynia = true;
        notifyAll();
        return true;
    }

    public synchronized boolean odbierzLiczby() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Przerwana transakcja.");
        }

        while (pustaSkrzynia) {
            System.out.println("Skrzynka pusta, należy poczekać na nowe listy..." + Thread.currentThread().getName());
            try {
                wait();
            } catch (InterruptedException e) {}
            return false;
        }

        Integer odebranaLiczba = liczbySkrzynka.get(0);
        System.out.println(odebranaLiczba + " odebrana ze skrzynki." + Thread.currentThread().getName());
        liczbySkrzynka.remove(0);
        this.liczbaLiczb--;
        pelnaSkrzynia = false;
        if (this.liczbaLiczb == 0) pustaSkrzynia = true;
        notifyAll();
        return true;
    }
}
