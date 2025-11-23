package br.edu.com.ifpb.tsi.poo;

import java.util.ArrayList;
import java.util.List;

public class Roteador {
    private String nome;
    private List<Rota> tabelaDeRotas;
    private List<Interface> interfaces;

    // Construtores
        public Roteador(){
        this.tabelaDeRotas = new ArrayList<>();
        this.interfaces = new ArrayList<>();
    }
    
    public Roteador(String nome){
        this.nome = nome;
        this.tabelaDeRotas = new ArrayList<>();
        this.interfaces = new ArrayList<>();
    }

    // Funcionalidades

    public void cadastrarInterface(Interface interfac){
        this.interfaces.add(interfac);
    }

    public void cadastrarRota(Rota rota){
        this.tabelaDeRotas.add(rota);
    }

    // Getters
    public String getNome() {
        return nome;
    }
    public List<Rota> getTabelaDeRotas() {
        return tabelaDeRotas;
    }
    public List<Interface> getInterfaces() {
        return interfaces;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTabelaDeRotas(List<Rota> tabelaDeRotas) {
        this.tabelaDeRotas = tabelaDeRotas;
    }
    public void setInterfaces(List<Interface> interfaces) {
        this.interfaces = interfaces;
    }

}
