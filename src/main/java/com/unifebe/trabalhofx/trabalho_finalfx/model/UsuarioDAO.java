package com.unifebe.trabalhofx.trabalho_finalfx.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//TIPO
//1 = ADM
//2 = NORMAL

/**
 * Classe para controle da tabela Usuario no banco de dados
 * @extend BancoDeDados
 * @author Pedro A. Visconti
 */
public class UsuarioDAO extends BancoDeDados implements IUsuario {

    private Connection connection;
    private String database = "unifebe";
    public static Usuario usuario_logado = new Usuario();
    private List<Usuario> consultaUsuarios = new ArrayList<Usuario>();

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
     * Retorna a lista de usuarios cadastrados no sistema
     * @return lista de usuarios cadastrados
     * @throws SQLException
     * @author Pedro A. Visconti
     */
    public List<Usuario> retornarUsuarios() throws SQLException {

        PreparedStatement prst = null;
        ResultSet rs = null;

        try{

            String SQL = "SELECT * FROM tbusuario";
            prst = connection.prepareStatement(SQL);
            rs = prst.executeQuery();

            while(rs.next()){

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNome(rs.getString(2));
                usuario.setSobrenome(rs.getString(3));
                usuario.setSenha(rs.getString(4));
                usuario.setIdTipo(rs.getInt(5));
                usuario.setLogin(rs.getString(6));


                consultaUsuarios.add(usuario);

            }

            return consultaUsuarios;

        } catch (Exception e){

            System.out.println("| Um erro ocorreu na consulta de Usuarios |");

        } finally {
            rs.close();
            prst.close();
        }
        return null;

    }

    /**
     * Metodo para apagar o usuario do banco de dados
     * @param id id do usuario a ser apagado
     * @author Pedro A. Visconti
     */
    public void apagarUsuario(int id){
        PreparedStatement prst;
        try{

            String SQL = "DELETE from tbusuario WHERE idusuario = " + id ;

            prst = connection.prepareStatement(SQL);
            prst.executeUpdate();

            System.out.println("Usuario de id: " + id + " deletado");

        }catch (Exception e){

            System.out.println("Um erro ocorreu na exclusão de um usuario");

        }
    }

    /**
     * Atualiza o usuario no banco de dados
     * @param usuario Usuario a ser editado, com seus dados novos
     * @throws SQLException
     * @author Pedro A. Visconti
     */

    public void atualizarUsuario(Usuario usuario) throws SQLException {
        PreparedStatement prst = null;
        String SQL = null;
        try{

            SQL = "UPDATE tbusuario SET nome = " + "'"+usuario.getNome()+"'" +
                    ", sobrenome = " + usuario.getSobrenome() +
                    ", login = " + usuario.getLogin() +
                    ", senha =  " + usuario.getSenha() +
                    " WHERE idusuario = " + usuario.getIdUsuario();


            prst = connection.prepareStatement(SQL);
            prst.executeUpdate();
            System.out.println("Usuario de id: " + usuario.getIdUsuario() + " Alterado com Sucesso");
        } catch (Exception e){
            System.out.println("Um erro ocorreu na atualização de dados");
        } finally {
            prst.close();
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
