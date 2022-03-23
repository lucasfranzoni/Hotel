package br.com.lucasfranzoni.hotel.service;

import br.com.lucasfranzoni.hotel.util.Leitor;
import java.util.Scanner;

public class SistemaService {
    
    public void mostrarOpcoes(Scanner scanner, HospedeService hospedeService, 
            SuiteService suiteService, ReservaService reservaService) {
        System.out.println("1 - Hóspedes");
        System.out.println("2 - Suítes");
        System.out.println("3 - Reservas");
        System.out.println("4 - Encerrar sistema");
        System.out.println("");
        int opcao = Leitor.receberInt(scanner, "Digite a opção que você deseja: ");
        System.out.println("");
        
        switch (opcao) {
            case 1:
                hospedeService.mostrarOpcoes(scanner);
                this.mostrarOpcoes(scanner, hospedeService, suiteService, reservaService);
                break;
            case 2:
                suiteService.mostrarOpcoes(scanner);;
                this.mostrarOpcoes(scanner, hospedeService, suiteService, reservaService);
                break;
            case 3:
                reservaService.mostrarOpcoes(scanner, hospedeService, suiteService);
                this.mostrarOpcoes(scanner, hospedeService, suiteService, reservaService);
                break;
            case 4:
                break;
            default:
                System.out.println("Número inválido, escolha uma das opções abaixo!");
                this.mostrarOpcoes(scanner, hospedeService, suiteService, reservaService);
        }
    }
    
}
