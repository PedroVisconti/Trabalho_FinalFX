package com.unifebe.trabalhofx.trabalho_finalfx.model;

import java.sql.*;

//TIPO
//1 = ADM
//2 = NORMAL

public class UsuarioDAO extends BancoDeDados implements IUsuario {

    private Connection connection;
    private String database = "unifebe";
    public static Usuario usuario_logado = new Usuario();

    public UsuarioDAO() throws SQLException {
        this.connection = conectarBanco(database);
    }


    /**
     * Comando usado para salvar um novo usuario no banco de dados
     * @param usuario
     * @throws SQLException
     */
    @Override
    public void salvarUsuario(Usuario usuario) throws SQLException {

        PreparedStatement pstmt = null;
        try {
            String SQL = "INSERT INTO tbusuario (nome, sobrenome, senha, login, idTipo)" +
                    "VALUES (?,?,?,?,?)";

            pstmt = connection.prepareStatement(SQL); //prepareStatement é uma forma de você fazer uma inserção no banco mais segura, onde você prepara os parametros para serem inseridos.

            pstmt.setString(1, usuario.getNome()); //seta em cada "?" as informações
            pstmt.setString(2, usuario.getSobrenome());
            pstmt.setString(3, usuario.getSenha());
            pstmt.setString(4, usuario.getLogin());
            pstmt.setInt(5, usuario.getIdTipo());

            pstmt.executeUpdate();

            System.out.println("Usuario " + usuario.getNome() + " salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pstmt.close();
        }

    }

    /**
     * Metodo para realizar o login do usuario
     * @param login
     * @param senha
     * @return boolean
     * @throws SQLException
     * @author Pedro Visconti
     */
    @Override
    public boolean login(String login, String senha) throws SQLException {

        usuario_logado = getloginBanco(login);

        if(usuario_logado == null){
            return false;
        }

        try {
            if (login.equals(usuario_logado.getLogin()) && senha.equals(usuario_logado.getSenha())) {
                return true;
            } else {
                System.out.println("Senha incorreto");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Um erro ocorreu");
        }


        return false;
    }

    /**
     * Metodo usado para realizar a recuperação dos dados do login do usuario
     * @param login
     * @return Usuario
     * @throws SQLException
     * @author Pedro Visconti
     */
    @Override
    public Usuario getloginBanco(String login) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String SQL = "SELECT login, senha, idTipo, nome, sobrenome " +
                    "FROM tbusuario " +
                    "WHERE login = ? ";
            pstmt = connection.prepareStatement(SQL);
            pstmt.setString(1, login); //where login = ?

            rs = pstmt.executeQuery();

            if (rs.next()) {//se encontrou o estudante
                Usuario usuario = new Usuario();
                usuario.setLogin(rs.getString("login")); //onde coluna for login
                usuario.setSenha(rs.getString("senha")); //onde coluna for senha
                usuario.setIdTipo(rs.getInt("idTipo")); //onde coluna for idTipo
                usuario.setNome(rs.getString("Nome")); //onde coluna for nome
                usuario.setSobrenome(rs.getString("Sobrenome")); //onde coluna for sobrenome
                return usuario;

            } else { //se não foi encontrado nenhum estudante com a matricula informada
                System.out.println("Nenhum usuario com este login");
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pstmt.close();
            rs.close();
        }
    }

    /**
     * Metodo usado para atualizar o tipo do usuario com base no seu login
     * @param id
     * @param login
     * @throws SQLException
     * @author Pedro Visconti
     */
    public void updateTipoUsuario(int id, String login) throws SQLException {
        PreparedStatement query = null;
        try{
            String SQL = "UPDATE tbusuario SET idTipo = " + id + " WHERE login = '" + login + "'" ;
            query = connection.prepareStatement(SQL);
            query.executeUpdate();
            System.out.println("Tipo de usuario do login " + login + " alterado");
        } finally {
            query.close();
        }
    }

    public int getIDUsuario_logado(){
        return usuario_logado.getIdTipo();
    }

    public String getNomeUsuario_logado(){
        return usuario_logado.getNome();
    }

    public String getSobrenomeUsuario_logado(){
        return usuario_logado.getSobrenome();
    }

}
