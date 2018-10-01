/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import DAOs.DAOAluno;
import DAOs.DAOTurma;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ludmila
 */
public class Matricula {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }

    int retorno = 0;

    public Matricula(int idTurma, int idAluno) {
        Turma t = new Turma();
        Aluno a = new Aluno();
        DAOTurma daoTurma = new DAOTurma();
        DAOAluno daoAluno = new DAOAluno();
        a = daoAluno.obter(idAluno);
        List<Turma> lp = a.getTurmaList();
        if (lp.indexOf(daoTurma.obter(idTurma)) < 0) {
            lp.add(daoTurma.obter(idTurma));
            retorno = 0;
        } else {
            System.out.println("ja existe!");
            retorno = 1;
        }

        a.setTurmaList(lp);
        daoAluno.atualizar(a);
    }

    public int encontradoNaLista() {
        if (retorno == 1) {
            return 1;
        } else {
            return 0;
        }
    }

}
