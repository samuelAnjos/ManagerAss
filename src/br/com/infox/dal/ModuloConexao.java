/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.dal;

import java.sql.*;

/**
 *
 * @author samue
 */
public class ModuloConexao {
    
    //metodo resposavel por estabelecer conexao com o banco ed dados
    public static  Connection conector(){
        java.sql.Connection conexao = null;
      
        // a linha abaixo "CHAMA" o driver
        String driver = "com.mysql.jdbc.Driver";
        
        //armazena informacoes referemtes ao banco
        String url = "jdbc:mysql://localhost:3306/dbinfox";
        String user = "root";
        String password = "ovelhapreta";
        
        //estabelecendo a conexao com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            //System.out.println(e);
            return null;
        }
        
    }
}
