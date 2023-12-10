package com.unifebe.trabalhofx.trabalho_finalfx.model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe para a implementação do aplicativo com o banco de dados
 */
public class BancoDeDados implements IBancoDeDados {

    /**
     * metodo usada para fazer conexão com o banco de dados
     * @param database
     * @return
     */
    public Connection conectarBanco(String database){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, "root", "Pedro33514713");//cria conexão com o servidor

        }catch (Exception e){
            System.out.println("Um erro ocorreu na conexão com o banco");
            return null;
        }

    }

}
