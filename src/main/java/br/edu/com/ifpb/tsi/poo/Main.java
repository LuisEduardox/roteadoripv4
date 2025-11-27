package br.edu.com.ifpb.tsi.poo;

public class Main {
    public static void main(String[] args) {

        Interface interface1 = new Interface("lo0", null);
        System.out.println(interface1);

        System.out.println();
        
        Rota rota1 = new Rota("127.0.0.1", "255.0.0.0", "127.0.0.1", interface1);
        System.out.println(rota1);

        System.out.println();
        
        System.out.println("----- Antes -----");
        Roteador r1 =  new Roteador("R1");
        System.out.println(r1);
        System.out.println(r1.exibiTabelaDeRotas());
        
        System.out.println();
        
        System.out.println("----- Depois -----");
        System.out.println(r1.exibiTabelaDeRotas());
    }
}