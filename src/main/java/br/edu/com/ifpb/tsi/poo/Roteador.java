package br.edu.com.ifpb.tsi.poo;

import java.util.ArrayList;
import java.util.List;

public class Roteador {
    private String nome;
    private List<Rota> tabelaDeRotas;
    private List<Interface> interfaces;
    private ModoExibicao modoExibicao;

    // Construtores
        public Roteador(){
            this.tabelaDeRotas = new ArrayList<>();
            this.interfaces = new ArrayList<>();
            this.modoExibicao = ModoExibicao.MASCARA;
    }
    
    public Roteador(String nome){
        this.nome = nome;
        this.tabelaDeRotas = new ArrayList<>();
        this.interfaces = new ArrayList<>();
        this.modoExibicao = ModoExibicao.MASCARA;
    }

    // Funcionalidades

    private int mascaraParaCIDR(String mascara) {
        String[] octetos = mascara.split("\\.");
        int cidr = 0;
        
        for (String octeto : octetos) {
            int valor = Integer.parseInt(octeto);
            String binario = Integer.toBinaryString(valor);
            cidr += binario.replace("0", "").length();
        }
        
        return cidr;
    }

    public String exibiTabelaDeRotas(){
        if(this.modoExibicao == ModoExibicao.CIDR){
            return exibiTabelaDeRotasCIDR();
        } else {
            return exibiTabelaDeRotasMascara();
        }
    }

    public String exibiTabelaDeRotasMascara(){
        String tabela = "Destino      Gateway      Mascara      Interface\n";

        if(this.tabelaDeRotas.isEmpty()){
        return "Tabela de rotas vazia.";
    }

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

    public String exibiTabelaDeRotasCIDR(){
        String tabela = "Destino/CIDR      Gateway      Interface\n";

        if(this.tabelaDeRotas.isEmpty()){
        return "Tabela de rotas vazia.";
    }

        for(int i = 0; i < tabelaDeRotas.size(); i++){
            Rota rota = tabelaDeRotas.get(i);
            
            String destino = rota.getDestino();
            String gateway = rota.getGateway();
            int cidr = mascaraParaCIDR(rota.getMascara());
            String interfac = rota.getInterfaceNome().getNome();

            tabela += destino + "/" + cidr + "   " + gateway + "   " + interfac + " \n";
        }

        return tabela;
    }

    public boolean cadastrarInterface(Interface novaInterface){
        for (Interface i : this.interfaces){
            if(i.getNome().equalsIgnoreCase(novaInterface.getNome())){
                return false;
            }
        }
        this.interfaces.add(novaInterface);
        return true;
    }

    public Interface buscarInterfaceNome(String nome){
        for(Interface i : this.interfaces){
           if(i.getNome().equalsIgnoreCase(nome)){
            return i;
           }
        }
        return null;
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

    public boolean cadastrarRota(Rota rota){ 

        if (rota == null){
            return false;
        }

        if(buscarIndiceRota(rota) != -1){
            return false;
        }

        this.tabelaDeRotas.add(rota);
        return true;
    }

    public void alterarRota(Rota rotaAntiga, Rota rotaNova){
        if (rotaAntiga == null || rotaNova == null){
            return;
        }

        int indiceRotaAntiga = buscarIndiceRota(rotaAntiga);

        if(indiceRotaAntiga != -1){
            this.tabelaDeRotas.set(indiceRotaAntiga, rotaNova);
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

    public void resetarTabela(){
        this.tabelaDeRotas.clear();
    }

    // A lógica agora é dp longest mach, vou te explicar direitinha por que é muito complexo ai vou te explicar bireitinho

    private long ipLong(String ip){ //como a gente tá pegando o ip do nosso usuário em string, vou convereter ele para long para poder fazer a conta, "mas pq long?" eu pesquisei a melhor forma de fazer esse calculo e com long é melhor por que tem menos chance de dá erro
     String partes[] = ip.trim().split("\\.");
     long resultado = 0;
     for (int i=0; i < 4; i++){
        int parte = Integer.parseInt(partes[i]); 
        
        resultado = resultado * 256;   //Multiplico por 256, para preencher as 4 "casas" do ip
        resultado = resultado + parte; //coloco na casinha
        
     }
     return resultado;
    }

   public Rota rotear(String ipDestino){
    if(this.tabelaDeRotas.isEmpty()){
        return null;
    }
    try{
        long ipDestinoLong = ipLong(ipDestino); // a gente vai transformar o ip do nosso usuário em long
        Rota melhorRota = null;
        long maiorMascara = -1; //guaradr a máscara que melhor se encaixa

        for(Rota rota: this.tabelaDeRotas){
            long redeRota = ipLong(rota.getDestino());
            long mascaraRota = ipLong(rota.getMascara());

        // agr vou usar a lógica que o professor ensinou sexta
        if ((ipDestinoLong & mascaraRota) == (redeRota & mascaraRota)){ 
            if(mascaraRota > maiorMascara){
                maiorMascara = mascaraRota;
                melhorRota = rota;
             }
        }
    }

    return melhorRota;
        
    } catch(Exception e) {
        System.out.println("Erro ao processar IP: " + e.getMessage());
        return null;
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
    public ModoExibicao getModoExibicao() {
        return modoExibicao;
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
    public void setModoExibicao(ModoExibicao modoExibicao) {
        this.modoExibicao = modoExibicao;
    }

    public String toString(){
        return getNome();
    }

}





