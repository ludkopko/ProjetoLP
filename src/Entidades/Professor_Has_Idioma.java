/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import DAOs.DAOIdioma;
import DAOs.DAOProfessor;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ludmila
 */
public class Professor_Has_Idioma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Professor p = new Professor();
//        Idioma i = new Idioma();
//        DAOProfessor daoProfessor = new DAOProfessor();
//        DAOIdioma daoIdioma = new DAOIdioma();
//        
//        i = daoIdioma.obter(idIdioma);
//        List<Professor> lp = i.getProfessorList();
//        lp.add(daoProfessor.obter(idProfessor));
//        
//        i.setProfessorList(lp);
//        daoIdioma.atualizar(i);

//        i = daoIdioma.obter(1);
//        List<Professor> lp = i.getProfessorList();
//        lp.add(daoProfessor.obter(1));
//        lp.add(daoProfessor.obter(2));
//        
//        
//        i.setProfessorList(lp);
//        daoIdioma.atualizar(i);
    }

    int retorno = 0;

    public Professor_Has_Idioma(int idProfessor, int idIdioma) {
        Professor p = new Professor();
        Idioma i = new Idioma();
        DAOProfessor daoProfessor = new DAOProfessor();
        DAOIdioma daoIdioma = new DAOIdioma();
        i = daoIdioma.obter(idIdioma);
        List<Professor> lp = i.getProfessorList();
        if (lp.indexOf(daoProfessor.obter(idProfessor)) < 0) {
            lp.add(daoProfessor.obter(idProfessor));
            retorno = 0;
        } else {
            System.out.println("ja existe!");
            retorno = 1;
        }

        i.setProfessorList(lp);
        daoIdioma.atualizar(i);
    }

    public int encontradoNaLista() {
        if (retorno == 1) {
            return 1;
        } else{
            return 0;
        }
    }

}
