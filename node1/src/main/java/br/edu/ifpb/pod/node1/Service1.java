/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.node1;

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
public class Service1 {
    public static void main(String[] args) {
        try {
            rum();
        } catch (IOException ex) {
            Logger.getLogger(Service1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Service1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void rum() throws IOException, ClassNotFoundException {

        // esperando conexão
        ServerSocket service1 = new ServerSocket(10991);
        //se conectando ao cliente
        System.err.println("servidor 1 ativo");
        Socket cliente = service1.accept();

        //recebendo dados do servidor
        ObjectInputStream read = new ObjectInputStream(cliente.getInputStream());

        //converte os dados do cliente 
        Salesman salesman = (Salesman) read.readObject();
        // salvar o dados no banco
        add(salesman);
        // manda os dados de vendedor para node2
        mandaParaNode2(salesman);
    }

    private static void add(Salesman salesman) {
        RepositorioNode1 repositorio = new RepositorioNode1();
        repositorio.add(salesman);
    }

    private static void mandaParaNode2(Salesman salesman) {
        try {
            Socket node2 = new Socket("localhost", 10992);
            ObjectOutputStream output = new ObjectOutputStream(node2.getOutputStream());
            output.writeObject(salesman);
            output.flush();
            output.close();
            node2.close();
        } catch (IOException ex) {
            Logger.getLogger(Service1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
