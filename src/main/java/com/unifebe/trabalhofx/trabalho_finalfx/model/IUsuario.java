package com.unifebe.trabalhofx.trabalho_finalfx.model;

import java.sql.SQLException;

public interface IUsuario {
    public void salvarUsuario(Usuario usuario) throws SQLException;
    public boolean login(String login, String senha ) throws SQLException;
    public Usuario getloginBanco(String login) throws SQLException;
}
