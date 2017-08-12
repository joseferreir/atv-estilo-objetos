/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.node2;
import  br.edu.ifpb.pod.shared.service.Repositorio;
import br.edu.ifpb.pod.shared.entity.Salesman;
import br.edu.ifpb.pod.shared.service.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose2
 */
public class RepositorioNode2 implements Repositorio<Salesman>{

     private Connection conn;
    private final String host = "url=jdbc:postgresql://localhost:5432/db_node_2";

    @Override
    public boolean add(Salesman obj) {
        try {
            boolean result = false;
            System.err.println("------------------------------->");
            String query = "INSERT INTO SALESMAN(PERSONID,PHONE) VALUES(?,?)";
            this.conn = ConnectionFactory.of(host).getConnection();
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setInt(1, obj.getId());
            stat.setString(2, obj.getPhone());
            if (stat.executeUpdate() > 0) {
                System.err.println("metodo r");

                result = true;
            }
            conn.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(RepositorioNode2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(RepositorioNode2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public Salesman find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
