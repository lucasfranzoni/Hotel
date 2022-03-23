package br.com.lucasfranzoni.hotel.service;

import br.com.lucasfranzoni.hotel.model.Suite;
import br.com.lucasfranzoni.hotel.util.Leitor;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class SuiteService {
    
    private ArrayList<Suite> suitesCadastradas = new ArrayList<>();
    
    public void mostrarOpcoes(Scanner scanner) {
        System.out.println("1 - Adicionar suíte");
        System.out.println("2 - Editar suíte");
        System.out.println("3 - Excluir suíte");
        System.out.println("4 - Listar suítes");
        System.out.println("5 - Voltar");
        System.out.println("");
        int opcao = Leitor.receberInt(scanner, "Digite a opção que você deseja: ");
        System.out.println("");
        
        switch (opcao) {
            case 1:
                this.adicionarSuite(scanner);
                this.mostrarOpcoes(scanner);
                break;
            case 2:
                this.editarSuite(scanner);
                this.mostrarOpcoes(scanner);
                break;
            case 3:
                this.excluirSuite(scanner);
                this.mostrarOpcoes(scanner);
                break;
            case 4:
                this.listarSuites();
                this.mostrarOpcoes(scanner);
                break;
             case 5:
                break;
            default:
                System.out.println("Número inválido, escolha uma das opções abaixo!");
                System.out.println("");
                this.mostrarOpcoes(scanner);
        }
        
    }
    
    private void adicionarSuite(Scanner scanner) {
        int numero = Leitor.receberInt(scanner, "Digite o número da suite: ");
        Optional<Suite> optional = suitesCadastradas.stream()
                .filter(suite -> suite.getNumero()== numero)
                .findFirst();
        if (optional.isPresent()) {
                System.out.println("");
                System.out.println("A suíte " + numero + " já está cadastrada!");
                System.out.println("");
                return;
        }
        System.out.println("");
        System.out.println("Digite o tipo da suíte: ");
        String tipo = scanner.nextLine();
        System.out.println("");
        int capacidade = Leitor.receberInt(scanner, "Digite a capacidade da suíte: ");
        System.out.println("");
        double valorDiaria = Leitor.receberDouble(scanner, "Digite o valor da diária da suíte: ");

        Suite suite = new Suite();
        suite.setNumero(numero);
        suite.setTipo(tipo);
        suite.setCapacidade(capacidade);
        suite.setValorDiaria(valorDiaria);
        
        suitesCadastradas.add(suite);

        System.out.println("");
        System.out.println("Suíte cadastrada com sucesso!");
        System.out.println("");
        
    }
    
    private void editarSuite(Scanner scanner) {
        if (!suitesCadastradas.isEmpty()) {
            listarSuites();
            System.out.println("");
            int numero = Leitor.receberInt(scanner, "Digite o número da suíte que deseja editar: ");
            System.out.println("");

            Optional<Suite> optional = suitesCadastradas.stream()
                    .filter(suite -> suite.getNumero()== numero)
                    .findFirst();

            if (optional.isEmpty()) {
                System.out.println("Nenhuma suíte cadastrada com esse número, tente novamente!");
                System.out.println("");
            } else {
                Suite suite = optional.get();
                int novoNumero = Leitor.receberInt(scanner, "Digite o  número da suite: ");
                Optional<Suite> optionalJaCadastrado = suitesCadastradas.stream().filter(
                        suiteJaCadastrada -> suiteJaCadastrada.getNumero()== novoNumero).findFirst();
                if (optionalJaCadastrado.isPresent() && suite.getNumero() != novoNumero) {
                    System.out.println("");
                    System.out.println("A suíte " + novoNumero + " já está cadastrada!");
                    System.out.println("");
                    return;
                } else {
                    suite.setNumero(novoNumero);
                }
                
                System.out.println("");
                System.out.println("Digite o tipo da suíte: ");
                suite.setTipo(scanner.nextLine());
                System.out.println("");
                suite.setCapacidade(Leitor.receberInt(scanner, "Digite a capacidade da suíte: "));
                System.out.println("");
                suite.setValorDiaria(Leitor.receberDouble(scanner, "Digite o valor da diária da suíte: "));

                System.out.println("");
                System.out.println("Suíte editada com sucesso!");
                System.out.println("");
            }           
        } else {
                System.out.println("Não existe nenhuma suíte cadastrada para ser editada!");
                System.out.println("");
        }       
        
    }
    
    private void excluirSuite(Scanner scanner) {
        if (!suitesCadastradas.isEmpty()) {
            listarSuites();
            System.out.println("");
            int numero = Leitor.receberInt(scanner, "Digite o número da suíte que deseja excluir: ");
            System.out.println("");

            Optional<Suite> optional = suitesCadastradas.stream().filter(
                    suite -> suite.getNumero()== numero).findFirst();

            if (optional.isEmpty()) {
                System.out.println("Nenhuma suíte cadastrada com esse número, tente novamente!");
                System.out.println("");
            } else {
                Suite suite = optional.get();
                
                suitesCadastradas.remove(suite);

                System.out.println("");
                System.out.println("Suíte excluída com sucesso!");
                System.out.println("");
            }           
        } else {
                System.out.println("Não existe nenhuma suíte cadastrada para ser excluída!");
                System.out.println("");
        }   
    }
    
    private void listarSuites() {
        if (!suitesCadastradas.isEmpty()) {
             for (Suite suite : suitesCadastradas) {      
                listarSuite(suite);
            }
         } else {
             System.out.println("Nenhuma suíte cadastrada!");
         }
        
        System.out.println("");
    }
    
    void listarSuite(Suite suite) {
        System.out.println("----------------------------------------------");
        System.out.println("Número: " + suite.getNumero());
        System.out.println("Tipo: " + suite.getTipo());
        System.out.println("Capacidade: " + suite.getCapacidade());
        System.out.println("Valor diária: R$ " + suite.getValorDiaria());
        System.out.println("----------------------------------------------");
    }

    public ArrayList<Suite> getSuitesCadastradas() {
        return suitesCadastradas;
    }   
}
