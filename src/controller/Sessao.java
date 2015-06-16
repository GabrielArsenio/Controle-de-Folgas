package controller;

import model.Usuario;

/**
 * Classe que armazena a sessão com o usuário logado.
 *
 * @author Gabriel
 */
public class Sessao {

    private static Sessao instance = null;
    private Usuario usuario;

    private Sessao() {
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public static Sessao getInstance() {
        if (instance == null) {
            instance = new Sessao();
        }
        return instance;
    }
}