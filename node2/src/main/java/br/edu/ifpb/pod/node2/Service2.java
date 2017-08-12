/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.node2;

import br.edu.ifpb.pod.shared.entity.Salesman;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose2
 */
public class Service2 {

    public static void main(String[] args) {
        try {
            rum();
        } catch (IOException ex) {
            Logger.getLogger(Service2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Service2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void rum() throws IOException, ClassNotFoundException {

        // esperando conexão
        ServerSocket service2 = new ServerSocket(10992);
        //se conectando ao cliente
        System.err.println("servidor 2 ativo");
        Socket cliente = service2.accept();

        //recebendo dados do servidor
        ObjectInputStream read = new ObjectInputStream(cliente.getInputStream());

        //converte os dados do cliente 
        Salesman salesman = (Salesman) read.readObject();
        // salvar o dados no banco
        add(salesman);
        // manda os dados de vendedor para node2
        mandaParaNode1(salesman);
        service2.close();
    }

    private static void add(Salesman salesman) {
        RepositorioNode2 repositorio = new RepositorioNode2();
        repositorio.add(salesman);
    }

    private static void mandaParaNode1(Salesman salesman) {
        try {
            Socket node1 = new Socket("localhost", 10991);
            ObjectOutputStream output = new ObjectOutputStream(node1.getOutputStream());
            output.writeObject(salesman);
            output.flush();
            output.close();
            node1.close();
        } catch (IOException ex) {
            Logger.getLogger(Service2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
