/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Ludmila
 */
@Entity
@Table(name = "horario")
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h")})
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_horario")
    private Integer idHorario;
    @Column(name = "codigo_horario")
    private String codigoHorario;
    @Column(name = "horas_horario")
    private String horasHorario;
    @JoinTable(name = "professor_has_horario", joinColumns = {
        @JoinColumn(name = "horario_id_horario", referencedColumnName = "id_horario")}, inverseJoinColumns = {
        @JoinColumn(name = "professor_id_professor", referencedColumnName = "id_professor")})
    @ManyToMany
    private List<Professor> professorList;
    @JoinTable(name = "turma_has_horario", joinColumns = {
        @JoinColumn(name = "horario_id_horario", referencedColumnName = "id_horario")}, inverseJoinColumns = {
        @JoinColumn(name = "turma_id_turma", referencedColumnName = "id_turma")})
    @ManyToMany
    private List<Turma> turmaList;

    public Horario() {
    }

    public Horario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public String getCodigoHorario() {
        return codigoHorario;
    }

    public void setCodigoHorario(String codigoHorario) {
        this.codigoHorario = codigoHorario;
    }

    public String getHorasHorario() {
        return horasHorario;
    }

    public void setHorasHorario(String horasHorario) {
        this.horasHorario = horasHorario;
    }

    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }

    public List<Turma> getTurmaList() {
        return turmaList;
    }

    public void setTurmaList(List<Turma> turmaList) {
        this.turmaList = turmaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorario != null ? idHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.idHorario == null && other.idHorario != null) || (this.idHorario != null && !this.idHorario.equals(other.idHorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ID:" + idHorario + "- Código: " + codigoHorario+ "- Horas:" + horasHorario;
    }
    
}
