package br.edu.com.ifpb.tsi.poo;

public class Rota {
    private String destino;
    private String mascara;
    private String gateway;
    private Interface interfaceNome;
    
    // Construtores
    public Rota(String destino, String mascara, String gateway, Interface interfaceNome){
        this.destino = destino;
        this.mascara = mascara;
        this.gateway = gateway;
        this.interfaceNome = interfaceNome;
    }
    // Funcionalidades

    // Getters
    public String getDestino() {
        return destino;
    }
    public String getMascara() {
        return mascara;
    }
    public String getGateway() {
        return gateway;
    }
    public Interface getInterfaceNome() {
        return interfaceNome;
    }
    
    // Setters
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public void setMascara(String mascara) {
        this.mascara = mascara;
    }
    public void setGateway(String gateway) {
        this.gateway = gateway;
    }
    public void setInterfaceNome(Interface interfaceNome) {
        this.interfaceNome = interfaceNome;
    }

    public String toString(){
        return ("Destino      Gateway      Mascara      Interface\n" +
                this.getDestino() + "    " + this.getGateway() + "    " + this.getMascara() + "    " + this.getInterfaceNome().getNome());
    }
}