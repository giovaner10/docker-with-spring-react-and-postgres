package br.com.cwi.racha.factories;

import java.util.Random;

public class SimpleFactory {

    public static Long getRandomLong() {
        return new Random().nextLong();
    }

    public static String getEmail() {
        return "meu@cwi.com.br";
    }

}
