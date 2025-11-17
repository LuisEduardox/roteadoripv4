package br.edu.com.ifpb.tsi.poo;

public class Interface {
    private String nome;
    private String enderecoIP;
    
    //Construtor
    public Interface(String nome, String enderecoIP){
        this.nome = nome;
        this.enderecoIP = enderecoIP;
    }
    
    // Funcionalidades
    
    // Getters
    public String getNome() {
        return nome;
    }
    public String getEnderecoIP() {
        return enderecoIP;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEnderecoIP(String enderecoIP) {
        this.enderecoIP = enderecoIP;
    }

    // toString
    public String toString(){
        return ("Interface      ip \n" + this.getNome() + "           " +this.getEnderecoIP());
    }
}
