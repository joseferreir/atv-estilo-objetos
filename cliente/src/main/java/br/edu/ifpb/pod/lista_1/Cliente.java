/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.lista_1;

import br.edu.ifpb.pod.shared.entity.Order;
import br.edu.ifpb.pod.shared.entity.Product;
import br.edu.ifpb.pod.shared.entity.Salesman;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.HTMLDocument;

/**
 *
 * @author jose2
 */
public class Cliente {

    private static ObjectInputStream resultado;

    public static void main(String[] args) {

        try {
            Salesman s = new Salesman("Josehp","43355-5623");
            Product p = new Product("note");
            p.setId(1);
            Order order = new Order(s, p, 2);
            rumNode3(order);
           // rum(s);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void rum(Salesman salesman) throws IOException {
        ObjectOutputStream output = null;
        Socket node1 = null;
        //Socket node2 = null;
        //Socket node3 = null;
            
               
                    node1 = new Socket("localhost", 10991);
                    output = new ObjectOutputStream(node1.getOutputStream());
                    output.writeObject(salesman);
                    output.flush();
                    output.close();
                    node1.close();
            
    }

    private static void lerDado() {
        int x, y, op;
        Random r = new Random();
        x = r.nextInt(100) + 1;
        y = r.nextInt(100) + 1;
        op = r.nextInt(2) + 1;
        System.err.println("valores gerados x " + x + " y " + y + "  opca dados" + op);
       

    }
     private static void rumNode3(Order salesman) throws IOException {
        ObjectOutputStream output = null;
        Socket node3 = null;
        
    
            
               
                    node3 = new Socket("localhost", 10993);
                    output = new ObjectOutputStream(node3.getOutputStream());
                    output.writeObject(salesman);
                    output.flush();
                    output.close();
                    node3.close();
            
    }

}
