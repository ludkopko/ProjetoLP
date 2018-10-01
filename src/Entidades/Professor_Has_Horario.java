/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import DAOs.DAOHorario;
import DAOs.DAOProfessor;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ludmila
 */
public class Professor_Has_Horario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }

    int retorno = 0;

    public Professor_Has_Horario(int idProfessor, int idHorario) {
        Professor p = new Professor();
        Horario h = new Horario();
        DAOProfessor daoProfessor = new DAOProfessor();
        DAOHorario daoHorario = new DAOHorario();
        h = daoHorario.obter(idHorario);
        List<Professor> lp = h.getProfessorList();
        if (lp.indexOf(daoProfessor.obter(idProfessor)) < 0) {
            lp.add(daoProfessor.obter(idProfessor));
            retorno = 0;
        } else {
            System.out.println("ja existe!");
            retorno = 1;
        }

        h.setProfessorList(lp);
        daoHorario.atualizar(h);
    }

    public int encontradoNaLista() {
        if (retorno == 1) {
            return 1;
        } else{
            return 0;
        }
    }

}