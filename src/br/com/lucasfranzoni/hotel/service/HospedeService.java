package br.com.lucasfranzoni.hotel.service;

import br.com.lucasfranzoni.hotel.model.Hospede;
import br.com.lucasfranzoni.hotel.util.Leitor;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class HospedeService {
    
    private ArrayList<Hospede> hospedesCadastrados = new ArrayList<>();
    
    public void mostrarOpcoes(Scanner scanner) {
        System.out.println("1 - Adicionar hóspede");
        System.out.println("2 - Editar hóspede");
        System.out.println("3 - Excluir hóspede");
        System.out.println("4 - Listar hóspedes");
        System.out.println("5 - Voltar");
        System.out.println("");
        int opcao = Leitor.receberInt(scanner, "Digite a opção que você deseja: ");
        System.out.println("");
        
        switch (opcao) {
            case 1:
                this.adicionarHospede(scanner);
                this.mostrarOpcoes(scanner);
                break;
            case 2:
                this.editarHospede(scanner);
                this.mostrarOpcoes(scanner);
                break;
            case 3:
                this.excluirHospede(scanner);
                this.mostrarOpcoes(scanner);
                break;
            case 4:
                this.listarHospedes();
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
    
    private void adicionarHospede(Scanner scanner) {
        int codigo = hospedesCadastrados.size() + 1;
        System.out.println("Digite o nome do hóspede: ");
        String nome = scanner.nextLine();
        System.out.println("");
        System.out.println("Digite o endereço do hóspede: ");
        String endereco = scanner.nextLine();
        System.out.println("");
        int idade = Leitor.receberInt(scanner, "Digite a idade do hóspede: ");

        Hospede hospede = new Hospede();
        hospede.setCodigo(codigo);
        hospede.setNome(nome);
        hospede.setEndereco(endereco);
        hospede.setIdade(idade);
        
        hospedesCadastrados.add(hospede);

        System.out.println("");
        System.out.println("Hóspede cadastrado com sucesso!");
        System.out.println("");
    }
    
    private void editarHospede(Scanner scanner) {
        if (!hospedesCadastrados.isEmpty()) {
            listarHospedes();
            System.out.println("");
            int codigo = Leitor.receberInt(scanner, "Digite o codigo do hóspede que deseja editar: ");
            System.out.println("");

            Optional<Hospede> optional = hospedesCadastrados.stream()
                    .filter(hospede -> hospede.getCodigo() == codigo)
                    .findFirst();

            if (optional.isEmpty()) {
                System.out.println("Nenhum hóspede cadastrado com esse código, tente novamente!");
                System.out.println("");
            } else {
                Hospede hospede = optional.get();
                System.out.println("Digite o nome do hóspede: ");
                hospede.setNome(scanner.nextLine());
                System.out.println("");
                System.out.println("Digite o endereço do hóspede: ");
                hospede.setEndereco(scanner.nextLine());
                System.out.println("");
                hospede.setIdade(Leitor.receberInt(scanner, "Digite a idade do hóspede: "));

                System.out.println("");
                System.out.println("Hóspede editado com sucesso!");
                System.out.println("");
            }           
        } else {
                System.out.println("Não existe nenhum hóspede cadastrado para ser editado!");
                System.out.println("");
        }       
    }
    
    private void excluirHospede(Scanner scanner) {
        if (!hospedesCadastrados.isEmpty()) {
            listarHospedes();
            System.out.println("");
            int codigo = Leitor.receberInt(scanner, "Digite o codigo do hóspede que deseja excluir: ");
            System.out.println("");

            Optional<Hospede> optional = hospedesCadastrados.stream()
                    .filter(hospede -> hospede.getCodigo() == codigo)
                    .findFirst();

            if (optional.isEmpty()) {
                System.out.println("Nenhum hóspede cadastrado com esse código, tente novamente!");
                System.out.println("");
            } else {
                Hospede hospede = optional.get();
                
                hospedesCadastrados.remove(hospede);

                System.out.println("");
                System.out.println("Hóspede excluído com sucesso!");
                System.out.println("");
            }           
        } else {
                System.out.println("Não existe nenhum hóspede cadastrado para ser excluído!");
                System.out.println("");
        }     
    }
    
    private void listarHospedes() {
         if (!hospedesCadastrados.isEmpty()) {
             for (Hospede hospede : hospedesCadastrados) {      
                 listarHospede(hospede);
            }
         } else {
             System.out.println("Nenhum hóspede cadastrado!");
         }
        
        System.out.println("");
    }
    
    void listarHospede(Hospede hospede) {
        System.out.println("----------------------------------------------");
        System.out.println("Código: " + hospede.getCodigo());
        System.out.println("Nome: " + hospede.getNome());
        System.out.println("Endereço: " + hospede.getEndereco());
        System.out.println("Idade: " + hospede.getIdade());
        System.out.println("----------------------------------------------");
    }

    public ArrayList<Hospede> getHospedesCadastrados() {
        return hospedesCadastrados;
    }  
}
