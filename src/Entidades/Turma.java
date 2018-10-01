/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ludmila
 */
@Entity
@Table(name = "turma")
@NamedQueries({
    @NamedQuery(name = "Turma.findAll", query = "SELECT t FROM Turma t")})
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_turma")
    private Integer idTurma;
    @Column(name = "nome_turma")
    private String nomeTurma;
    @Column(name = "dia_turma")
    private String diaTurma;
    @Column(name = "horarioinicio_turma")
    @Temporal(TemporalType.TIME)
    private Date horarioinicioTurma;
    @Column(name = "horariofim_turma")
    @Temporal(TemporalType.TIME)
    private Date horariofimTurma;
    @ManyToMany(mappedBy = "turmaList")
    private List<Aluno> alunoList;
    @ManyToMany(mappedBy = "turmaList")
    private List<Horario> horarioList;
    @JoinColumn(name = "nivel_id_nivel", referencedColumnName = "id_nivel")
    @ManyToOne(optional = false)
    private Nivel nivelIdNivel;
    @JoinColumn(name = "professor_id_professor", referencedColumnName = "id_professor")
    @ManyToOne(optional = false)
    private Professor professorIdProfessor;

    public Turma() {
    }

    public Turma(Integer idTurma) {
        this.idTurma = idTurma;
    }

    public Integer getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Integer idTurma) {
        this.idTurma = idTurma;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public String getDiaTurma() {
        return diaTurma;
    }

    public void setDiaTurma(String diaTurma) {
        this.diaTurma = diaTurma;
    }

    public Date getHorarioinicioTurma() {
        return horarioinicioTurma;
    }

    public void setHorarioinicioTurma(Date horarioinicioTurma) {
        this.horarioinicioTurma = horarioinicioTurma;
    }

    public Date getHorariofimTurma() {
        return horariofimTurma;
    }

    public void setHorariofimTurma(Date horariofimTurma) {
        this.horariofimTurma = horariofimTurma;
    }

    public List<Aluno> getAlunoList() {
        return alunoList;
    }

    public void setAlunoList(List<Aluno> alunoList) {
        this.alunoList = alunoList;
    }

    public List<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }

    public Nivel getNivelIdNivel() {
        return nivelIdNivel;
    }

    public void setNivelIdNivel(Nivel nivelIdNivel) {
        this.nivelIdNivel = nivelIdNivel;
    }

    public Professor getProfessorIdProfessor() {
        return professorIdProfessor;
    }

    public void setProfessorIdProfessor(Professor professorIdProfessor) {
        this.professorIdProfessor = professorIdProfessor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTurma != null ? idTurma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turma)) {
            return false;
        }
        Turma other = (Turma) object;
        if ((this.idTurma == null && other.idTurma != null) || (this.idTurma != null && !this.idTurma.equals(other.idTurma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "idTurma:" + idTurma + " ";
    }
    
}
