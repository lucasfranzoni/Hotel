package br.com.lucasfranzoni.hotel.model;

import java.util.ArrayList;

public class Reserva {
    
    private ArrayList<Hospede> hospedes;
    private Suite suite;
    private int quantidadePessoas;
    private int quantidadeDias;
    
    public Reserva() {
        this.hospedes = new ArrayList<>();
    }
    
    public boolean verificarCapacidade() {
        return this.quantidadePessoas < suite.getCapacidade();
    }
    
    public double calcularDiaria() {
        double valorTotal = this.quantidadeDias * suite.getValorDiaria();
        
        if (this.quantidadeDias > 7) {
            valorTotal *= 0.9; 
        }
        
        return valorTotal;
    }

    public ArrayList<Hospede> getHospedes() {
        return hospedes;
    }

    public Suite getSuite() {
        return suite;
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public int getQuantidadeDias() {
        return quantidadeDias;
    }

    public void setHospedes(ArrayList<Hospede> hospedes) {
        this.hospedes = hospedes;
    }

    public void setSuite(Suite suite) {
        this.suite = suite;
    }

    public void setQuantidadePessoas(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public void setQuantidadeDias(int quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }
}
