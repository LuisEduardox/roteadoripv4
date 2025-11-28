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

    public String exibiTabelaDeRotas(){
        String tabela = "Destino      Gateway      Mascara      Interface\n";

        for(int i = 0; i < tabelaDeRotas.size(); i++){
            Rota rota = tabelaDeRotas.get(i);
            
            String destino = rota.getDestino();
            String gateway = rota.getGateway();
            String mascara = rota.getMascara();
            String interfac = rota.getInterfaceNome().getNome();

            tabela += destino + "   " + gateway + "   " + mascara + "   " + interfac + " \n";
        }

        return tabela;
    }

    public String exibiInterfaces(){
        String tabela = "Interface      IP\n";

        for(int i = 0; i < interfaces.size(); i++){
            Interface interfac = interfaces.get(i);
            
            String nome = interfac.getNome();
            String ip = interfac.getEnderecoIP();

            tabela += nome + "            " + ip + " \n";
        }

        return tabela;
    }

    public void cadastrarInterface(Interface interfac){
        boolean x = true;
        for(int i = 0 ; i < interfaces.size(); i++){
            if(interfaces.get(i).getNome() == interfac.getNome()){
                x = false;
            }
        }

        if(interfac == null){
            return;
        }

        if(x){
            this.interfaces.add(interfac);
        }
    }

    private int buscarIndiceRota(Rota rota) {
    String destinoBusca = rota.getDestino();
    String mascaraBusca = rota.getMascara();
    String gatewayBusca = rota.getGateway();

    for (int i = 0; i < this.tabelaDeRotas.size(); i++) {
        Rota rotaLista = this.tabelaDeRotas.get(i);

        if (rotaLista.getDestino().trim().equals(destinoBusca.trim()) &&
            rotaLista.getMascara().trim().equals(mascaraBusca.trim()) &&
            rotaLista.getGateway().trim().equals(gatewayBusca.trim())){

            return i; 
        }
    }

    return -1; 
}

    public void cadastrarRota(Rota rota){ 
        if (rota == null){
            return;
        }

        if(buscarIndiceRota(rota) != -1){
            return;
        }

        this.tabelaDeRotas.add(rota);
    }

    public void alterarRota(Rota rotaAlterada){
        if (rotaAlterada == null){
            return;
        }

        int indiceRotaAntiga = buscarIndiceRota(rotaAlterada);

        if(indiceRotaAntiga != -1){
            this.tabelaDeRotas.set(indiceRotaAntiga, rotaAlterada);
        } else {
            return;
        }
    }

    public void removerRota(Rota rotaRemover){
        if (rotaRemover == null){
            return;
        }

        int indiceEncontrado = buscarIndiceRota(rotaRemover);

        if(indiceEncontrado != -1){
            this.tabelaDeRotas.remove(indiceEncontrado);
        } else {
            return;
        }


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

    public String toString(){
        return getNome();
    }

}

