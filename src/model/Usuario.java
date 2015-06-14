/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Gabriel
 */
@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    private int codigo;
    @Column(length = 100)
    private String nome;
    @Column(length = 16)
    private String usuario;
    @Column(length = 16)
    private String senha;
    @OneToOne
    private Funcionario funionario;
    private int nivel;

    public Usuario() {
    }

    @Override
    public String toString() {
        return nome;
    }

    public Funcionario getFunionario() {
        return funionario;
    }

    public void setFunionario(Funcionario funionario) {
        this.funionario = funionario;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
