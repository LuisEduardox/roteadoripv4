package br.edu.com.ifpb.tsi.poo;

public class Main {
    public static void main(String[] args) {

        Interface interface1 = new Interface(null, null);
        System.out.println(interface1);

        System.out.println();
        
        Rota rota1 = new Rota("127.0.0.1", "127.0.0.1", "255.0.0.0", interface1);
        System.out.println(rota1);

        System.out.println();

    }
}