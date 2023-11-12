package com.unifebe.trabalhofx.trabalho_finalfx.model;

import java.sql.Connection;

public interface IBancoDeDados {
    public Connection conectarBanco(String database);

}