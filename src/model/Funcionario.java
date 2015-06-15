package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gabriel
 */
@Entity
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue
    private int codigo;
    @Column(length = 150)
    private String nome;
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Temporal(TemporalType.DATE)
    private Date dataEfetivo;
    private char sexo;
    @ManyToOne
    private Setor setor;
    @ManyToOne
    private Cargo cargo;
    @OneToOne
    private Usuario usuario;
    @Column(length = 15)
    private String telefone;
    @Column(length = 50)
    private String email;

    public Funcionario() {
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

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataEfetivo() {
        return dataEfetivo;
    }

    public void setDataEfetivo(Date dataEfetivo) {
        this.dataEfetivo = dataEfetivo;
    }

    public String getSexo() {
        switch (sexo) {
            case 'M':
                return "Masculino";
            case 'F':
                return "Feminino";
            default:
                return "";
        }
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Setor getArea() {
        return setor;
    }

    public void setArea(Setor area) {
        this.setor = area;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
