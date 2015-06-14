package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gabriel
 * @since 31/05/2015
 */
@Entity
public class Folga implements Serializable {

    @Id
    @GeneratedValue
    private int codigo;
    @ManyToOne
    private Funcionario funcionario;
//    @ManyToOne
//    private Area area;
    private char tipo;
    @Temporal(TemporalType.DATE)
    private Date dtInicio;
    @Temporal(TemporalType.DATE)
    private Date dtFim;
    @Temporal(TemporalType.TIME)
    private Date hrManhaInicio;
    @Temporal(TemporalType.TIME)
    private Date hrManhaFim;
    @Temporal(TemporalType.TIME)
    private Date hrTardeInicio;
    @Temporal(TemporalType.TIME)
    private Date hrTardeFim;
    private String motivo;
    private char status;
    private String observacao;

    public Folga() {
    }

    @Override
    public String toString() {
        return motivo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

//    public Area getArea() {
//        return area;
//    }
//
//    public void setArea(Area area) {
//        this.area = area;
//    }

    public String getTipo() {
        switch (tipo) {
            case 'O':
                return "Folga";
            case 'E':
                return "Férias";
            case 'S':
                return "Saída";
            default:
                return "Não definida.";
        }
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

    public Date getHrManhaInicio() {
        return hrManhaInicio;
    }

    public void setHrManhaInicio(Date hrManhaInicio) {
        this.hrManhaInicio = hrManhaInicio;
    }

    public Date getHrManhaFim() {
        return hrManhaFim;
    }

    public void setHrManhaFim(Date hrManhaFim) {
        this.hrManhaFim = hrManhaFim;
    }

    public Date getHrTardeInicio() {
        return hrTardeInicio;
    }

    public void setHrTardeInicio(Date hrTardeInicio) {
        this.hrTardeInicio = hrTardeInicio;
    }

    public Date getHrTardeFim() {
        return hrTardeFim;
    }

    public void setHrTardeFim(Date hrTardeFim) {
        this.hrTardeFim = hrTardeFim;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getStatus() {
        switch (status) {
            case 'A':
                return "Aprovada";
            case 'G':
                return "Aguardando Aprovação";
            case 'R':
                return "Reprovada";
            default:
                return "Não definido.";
        }
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
