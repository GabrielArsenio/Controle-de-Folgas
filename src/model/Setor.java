package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Gabriel
 */
@Entity
public class Setor implements Serializable {

    @Id
    @GeneratedValue
    private int codigo;
    private String nome;
    private String descricao;
    @ManyToOne
    private Funcionario coordenador;

    public Setor() {
    }

    public Setor(String nome, String descricao, Funcionario coordenador) {
        this.nome = nome;
        this.descricao = descricao;
        this.coordenador = coordenador;
    }

    @Override
    public String toString() {
        return nome;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Funcionario getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Funcionario coordenador) {
        this.coordenador = coordenador;
    }
}
