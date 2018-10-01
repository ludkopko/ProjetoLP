/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ludmila
 */
@Entity
@Table(name = "idioma")
@NamedQueries({
    @NamedQuery(name = "Idioma.findAll", query = "SELECT i FROM Idioma i")})
public class Idioma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_idioma")
    private Integer idIdioma;
    @Column(name = "nome_idioma")
    private String nomeIdioma;
    @JoinTable(name = "professor_has_idioma", joinColumns = {
        @JoinColumn(name = "idioma_id_idioma", referencedColumnName = "id_idioma")}, inverseJoinColumns = {
        @JoinColumn(name = "professor_id_professor", referencedColumnName = "id_professor")})
    @ManyToMany
    private List<Professor> professorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiomaIdIdioma")
    private List<Nivel> nivelList;

    public Idioma() {
    }

    public Idioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getNomeIdioma() {
        return nomeIdioma;
    }

    public void setNomeIdioma(String nomeIdioma) {
        this.nomeIdioma = nomeIdioma;
    }

    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }

    public List<Nivel> getNivelList() {
        return nivelList;
    }

    public void setNivelList(List<Nivel> nivelList) {
        this.nivelList = nivelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIdioma != null ? idIdioma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Idioma)) {
            return false;
        }
        Idioma other = (Idioma) object;
        if ((this.idIdioma == null && other.idIdioma != null) || (this.idIdioma != null && !this.idIdioma.equals(other.idIdioma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ID: " + idIdioma + ", Nome:" + nomeIdioma;
    }
    
}
