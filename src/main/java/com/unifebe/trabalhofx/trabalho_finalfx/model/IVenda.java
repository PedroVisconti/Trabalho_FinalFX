package com.unifebe.trabalhofx.trabalho_finalfx.model;

        import java.sql.SQLException;

/**
 * Classe responsavel pelo controle dos metodos da classe Venda
 * @author Pedro A. Visconti
 */
public interface IVenda {

    public void salvarVenda(Carrinho carrinho) throws SQLException;

    private int retornaIDVenda() {
        return 0;
    }

}
