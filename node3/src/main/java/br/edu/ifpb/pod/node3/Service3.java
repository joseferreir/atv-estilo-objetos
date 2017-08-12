/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.node3;

import br.edu.ifpb.pod.shared.entity.Order;
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
public class Service3 {

    public static void main(String[] args) {
        try {
            rum();
        } catch (IOException ex) {
            Logger.getLogger(Service3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Service3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void rum() throws IOException, ClassNotFoundException {

        // esperando conexão
        ServerSocket service3 = new ServerSocket(10993);
        //se conectando ao cliente
        System.err.println("servidor 3 ativo");
        Socket cliente = service3.accept();

        //recebendo dados do servidor
        ObjectInputStream read = new ObjectInputStream(cliente.getInputStream());

        //converte os dados do cliente 
        Order order = (Order) read.readObject();
        // salvar o dados no banco
        add(order);
        // manda os dados de vendedor para node2
        mandaParaNode2(order);
    }

    private static void add(Order order) {
        RepositorioNode3 repositorio = new RepositorioNode3();
        repositorio.add(order);
    }

    private static void mandaParaNode2(Order order) {
        try {
            Socket node2 = new Socket("localhost", 10992);
            ObjectOutputStream output = new ObjectOutputStream(node2.getOutputStream());
            output.writeObject(order.getSalesman());
            output.flush();
            output.close();
            node2.close();
        } catch (IOException ex) {
            Logger.getLogger(Service3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
