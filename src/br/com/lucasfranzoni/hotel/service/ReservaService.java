package br.com.lucasfranzoni.hotel.service;

import br.com.lucasfranzoni.hotel.model.Reserva;
import br.com.lucasfranzoni.hotel.model.Hospede;
import br.com.lucasfranzoni.hotel.model.Suite;
import br.com.lucasfranzoni.hotel.util.Leitor;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class ReservaService {
    
    private ArrayList<Reserva> reservas = new ArrayList<>();
    
    public void mostrarOpcoes(Scanner scanner, HospedeService hospedeService, SuiteService suiteService) {
        System.out.println("1 - Adicionar reserva");
        System.out.println("2 - Editar reserva");
        System.out.println("3 - Excluir reserva");
        System.out.println("4 - Listar reservas");
        System.out.println("5 - Voltar");
        System.out.println("");
        int opcao = Leitor.receberInt(scanner, "Digite a opção que você deseja: ");
        System.out.println("");
        
        switch (opcao) {
            case 1:
                this.adicionarReserva(scanner, hospedeService, suiteService);
                this.mostrarOpcoes(scanner, hospedeService, suiteService);
                break;
            case 2:
                this.editarReserva(scanner, hospedeService, suiteService);
                this.mostrarOpcoes(scanner, hospedeService, suiteService);
                break;
            case 3:
                this.excluirReserva(scanner, hospedeService, suiteService);
                this.mostrarOpcoes(scanner, hospedeService, suiteService);
                break;
            case 4:
                this.listarReservas(hospedeService, suiteService);
                this.mostrarOpcoes(scanner, hospedeService, suiteService);
                break;
             case 5:
                break;
            default:
                System.out.println("Número inválido, escolha uma das opções abaixo!");
                System.out.println("");
                this.mostrarOpcoes(scanner, hospedeService, suiteService);
        }
        
    }
    
    private void adicionarReserva(Scanner scanner, HospedeService hospedeService, SuiteService suiteService) {
        
        try {
            Reserva reserva = new Reserva();
            
            adicionarSuiteNaReserva(scanner, reserva, suiteService);
            adicionarHospedeNaReserva(scanner, reserva, hospedeService);
            adicionarQuantidadeDiasNaReserva(scanner, reserva);
            
            reservas.add(reserva);
            
            System.out.println("Reserva cadastrada com sucesso!");
            System.out.println("");
        } catch (RuntimeException e) {
            
        }
    }
    
    private void editarReserva(Scanner scanner, HospedeService hospedeService, SuiteService suiteService) {
        if (!reservas.isEmpty()) {
            listarReservas(hospedeService, suiteService);
            System.out.println("");
            int codigo = Leitor.receberInt(scanner, "Digite o codigo da reserva que deseja editar: ");
            System.out.println("");
            
            try {
                reservas.get(codigo -1);
                Reserva reserva = new Reserva();
                
                adicionarSuiteNaReserva(scanner, reserva, suiteService);
                adicionarHospedeNaReserva(scanner, reserva, hospedeService);
                adicionarQuantidadeDiasNaReserva(scanner, reserva);
                
                reservas.set(codigo -1, reserva);

                System.out.println("");
                System.out.println("Reserva editada com sucesso!");
                System.out.println("");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Nenhuma reserva cadastrada com esse código, tente novamente!");
                System.out.println("");
            }
        } else {
            System.out.println("Não existe nenhuma reserva cadastrada para ser editada!");
            System.out.println("");
        }
    }
    
    private void excluirReserva(Scanner scanner, HospedeService hospedeService, SuiteService suiteService) {
        if (!reservas.isEmpty()) {
            listarReservas(hospedeService, suiteService);
            System.out.println("");
            int codigo = Leitor.receberInt(scanner, "Digite o codigo da reserva que deseja excluir: ");
            System.out.println("");
            
            try {                
                reservas.remove(codigo -1);
                
                System.out.println("");
                System.out.println("Reserva excluída com sucesso!");
                System.out.println("");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Nenhuma reserva cadastrada com esse código, tente novamente!");
                System.out.println("");
            }
        } else {
            System.out.println("Não existe nenhuma reserva cadastrada para ser excluída!");
            System.out.println("");
        }
    }
    
    private void listarReservas(HospedeService hospedeService, SuiteService suiteService) {
        if (!reservas.isEmpty()) {
            int contador = 1;
             for (Reserva reserva : reservas) {      
                 System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                 System.out.println("Código: " + contador);
                 System.out.println("Hóspedes:");
                 for (Hospede hospode : reserva.getHospedes()) {
                     hospedeService.listarHospede(hospode);
                 }
                 System.out.println("Suíte: ");
                 suiteService.listarSuite(reserva.getSuite());
                 System.out.println("Quantidade de pessoas: " + reserva.getQuantidadePessoas());
                 System.out.println("Quantidade de dias: " + reserva.getQuantidadeDias());
                 System.out.println("Valor total: R$ " + reserva.calcularDiaria());
                 System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                 contador++;
            }
         } else {
             System.out.println("Nenhuma reserva cadastrada!");
         }
        
        System.out.println("");
        
    }
    
    private void adicionarSuiteNaReserva(Scanner scanner, Reserva reserva, SuiteService suiteService) {
        if (!suiteService.getSuitesCadastradas().isEmpty()) {
            for (Suite suite : suiteService.getSuitesCadastradas()) {
                suiteService.listarSuite(suite);
            }
            System.out.println("");
            int numero = Leitor.receberInt(scanner, "Digite o número da suíte que deseja reservar: ");
            System.out.println("");
            Optional<Suite> optional = suiteService.getSuitesCadastradas().stream()
                    .filter(suite -> suite.getNumero() == numero)
                    .findFirst();

            if (optional.isEmpty()) {
                System.out.println("Nenhuma suíte disponível com esse número, tente novamente!");
                System.out.println("");
                adicionarSuiteNaReserva(scanner, reserva, suiteService);
            } else {
                Suite suite = optional.get();
                
                reserva.setSuite(suite);

                System.out.println("");
                System.out.println("Suíte adicionada com sucesso!");
                System.out.println("");
            }
        }
        
    }
    
    private void adicionarHospedeNaReserva(Scanner scanner, Reserva reserva, HospedeService hospedeService) {
        ArrayList<Hospede> hospedesDisponiveis = new ArrayList<>();
                
        hospedesDisponiveis.addAll(hospedeService.getHospedesCadastrados());
        hospedesDisponiveis.removeAll(reserva.getHospedes());
        
        if (!hospedesDisponiveis.isEmpty()) {
            for (Hospede hospede : hospedesDisponiveis) {
                hospedeService.listarHospede(hospede);
            }
            System.out.println("");
            int codigo = Leitor.receberInt(scanner, "Digite o codigo do hóspede que deseja adicionar: ");
            System.out.println("");
            Optional<Hospede> optional = hospedesDisponiveis.stream()
                    .filter(hospede -> hospede.getCodigo() == codigo)
                    .findFirst();

            if (optional.isEmpty()) {
                System.out.println("Nenhum hóspede disponível com esse código, tente novamente!");
                System.out.println("");
                adicionarHospedeNaReserva(scanner, reserva, hospedeService);
            } else {
                Hospede hospede = optional.get();
                
                boolean hospedeTemMaisQueDoisAnos = hospede.getIdade() > 2;
                boolean temCapacidade = reserva.verificarCapacidade();
                
                if (hospedeTemMaisQueDoisAnos && !temCapacidade) {
                    String mensagem = "Você ultrapassou o limite de " + reserva.getSuite().getCapacidade() + " hóspedes para essa suíte, tente novamente!";
                    System.out.println(mensagem);
                    System.out.println("");
                    throw new RuntimeException(mensagem);
                }
                
                reserva.getHospedes().add(hospede);
                
                if (hospedeTemMaisQueDoisAnos) {
                    reserva.setQuantidadePessoas(reserva.getQuantidadePessoas() + 1);
                }

                System.out.println("");
                System.out.println("Hóspede adicionado com sucesso!");
                System.out.println("");
                
                if (desejaAdicionarOutroHospede(scanner)) {
                    adicionarHospedeNaReserva(scanner, reserva, hospedeService);
                }    
            }   
        } else {
            System.out.println("Nenhum hóspede disponível para ser adicionado!");
            System.out.println("");
            throw new RuntimeException("Nenhum hóspede disponível para ser adicionado!");
        }
        
    }
    
    private boolean desejaAdicionarOutroHospede(Scanner scanner) {
        
    
        System.out.println("Deseja adicionar outro hóspede?");
        System.out.println("");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        System.out.println("");
        int opcao = Leitor.receberInt(scanner, "Digite a opção que você deseja: ");
        System.out.println("");

       switch (opcao) {
        case 1:
            return true;
        case 2:
            return false;
        default:
            System.out.println("Número inválido, escolha uma das opções abaixo!");
            System.out.println("");
            return desejaAdicionarOutroHospede(scanner);
        }
    }
    
    private void adicionarQuantidadeDiasNaReserva(Scanner scanner, Reserva reserva) {
        reserva.setQuantidadeDias(Leitor.receberInt(scanner, "Digite a quantidade de dias que deseja reservar: "));
        System.out.println("");
    }
}
