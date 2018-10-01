/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import DAOs.DAOHorario;
import DAOs.DAOTurma;
import java.util.List;

/**
 *
 * @author Ludmila
 */
public class Turma_Has_Horario {
    
    public static void main(String[] args) {
        
    }

    int retorno = 0;

    public Turma_Has_Horario(int idTurma, int idHorario) {
        Turma p = new Turma();
        Horario h = new Horario();
        DAOTurma daoTurma = new DAOTurma();
        DAOHorario daoHorario = new DAOHorario();
        h = daoHorario.obter(idHorario);
        List<Turma> lp = h.getTurmaList();
        if (lp.indexOf(daoTurma.obter(idTurma)) < 0) {
            lp.add(daoTurma.obter(idTurma));
            retorno = 0;
        } 
        else {
            System.out.println("ja existe!");
            retorno = 1;
        }

        h.setTurmaList(lp);
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
