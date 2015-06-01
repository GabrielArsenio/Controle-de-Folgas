package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Gabriel
 */
@Entity
public class Area implements Serializable {

    @Id
    @GeneratedValue
    private int codigo;
    private String nome;

    public Area() {
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
}
