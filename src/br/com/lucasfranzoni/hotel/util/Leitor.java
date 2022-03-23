package br.com.lucasfranzoni.hotel.util;

import java.util.Scanner;

public class Leitor {
    
    public static int receberInt(Scanner scanner, String mensagem) {       
        try {
            System.out.println(mensagem);
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("");
            System.out.println("Esse campo deve ser preenchido com um número, tente novamente!");
            System.out.println("");
            return receberInt(scanner, mensagem);
        }
    }
    
        public static double receberDouble(Scanner scanner, String mensagem) {       
        try {
            System.out.println(mensagem);
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("");
            System.out.println("Esse campo deve ser preenchido com um número, tente novamente!");
            System.out.println("");
            return receberDouble(scanner, mensagem);
        }
    }
    
}
