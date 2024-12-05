package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {


    public Connection getConexao(){

        try {

            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/precoperifericos", "user", "pwd");
            return conn;


        } catch (ClassNotFoundException e) {
            System.out.println("Problema ao carregar o drive   " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        catch (SQLException ex){
            System.out.println("SQL " + ex);
            ex.printStackTrace();
            return null;
        }


    }

}
