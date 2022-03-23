package br.com.lucasfranzoni.hotel;

import br.com.lucasfranzoni.hotel.service.HospedeService;
import br.com.lucasfranzoni.hotel.service.ReservaService;
import br.com.lucasfranzoni.hotel.service.SistemaService;
import br.com.lucasfranzoni.hotel.service.SuiteService;
import java.util.Scanner;

public class Hotel {

    public static void main(String[] args) {
        SistemaService sistemaService = new SistemaService();
        Scanner scanner = new Scanner(System.in);
        HospedeService hospedeService = new HospedeService();
        SuiteService suiteService = new SuiteService();
        ReservaService reservaService = new ReservaService();
        
        System.out.println("BEM VINDO AO SISTEMA DE RESERVA DE HOSPEDAGEM");
        System.out.println("----------------------------------------------");
        System.out.println("");
        
        sistemaService.mostrarOpcoes(scanner, hospedeService, suiteService, reservaService);
        scanner.close();
    }
    
    
    
}
