/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIs;

import Entidades.Professor;
import Entidades.Professor_Has_Horario;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author Ludmila
 */
public class GUIProfessorHasHorario extends JDialog {

    DAOs.DAOProfessor daoProfessor = new DAOs.DAOProfessor();
    Entidades.Professor professor = new Entidades.Professor();
    DAOs.DAOHorario daoHorario = new DAOs.DAOHorario();
    Entidades.Horario horario = new Entidades.Horario();

    JLabel lbVazio = new JLabel("Selecione um horario por vez para determinado professor");

    JLabel lbNomeProfessor = new JLabel("Professor");
    JTextField tfNomeProfessor = new JTextField(20);
    JLabel lbNomeHorario = new JLabel("Horário");
    JTextField tfNomeHorario = new JTextField(20);

    JButton btInserir = new JButton("Inserir");

    JPanel pnTotal = new JPanel(new GridLayout(3, 1));
    JPanel pnUm = new JPanel(new GridLayout(1, 2));
    JPanel pnUmUm = new JPanel(new FlowLayout(1));
    JPanel pnUmDois = new JPanel(new FlowLayout(1));
    JPanel pnDois = new JPanel(new FlowLayout(1));

    public GUIProfessorHasHorario() {
        setTitle("Adicionar Horário ao Professor");
        setSize(500, 300);//tamanho da janela
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros 

        pnUmUm.add(lbNomeProfessor);
        pnUmUm.add(tfNomeProfessor);
        pnUmDois.add(lbNomeHorario);
        pnUmDois.add(tfNomeHorario);

        pnUm.add(pnUmUm);
        pnUm.add(pnUmDois);
        pnDois.add(btInserir);

        pnTotal.add(lbVazio);
        pnTotal.add(pnUm);
        pnTotal.add(pnDois);

        cp.add(pnTotal);

        tfNomeProfessor.addActionListener(new ActionListener() {//pesquisa incremental
            @Override
            public void actionPerformed(ActionEvent ae) {

                List<String> listaAuxiliar = daoProfessor.listInOrderNomeStrings("id");

                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, 400, 400).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        //String[] aux = selectedItem.split("-");
                        tfNomeProfessor.setText(selectedItem);
                    } else {
                        tfNomeProfessor.requestFocus();
                        tfNomeProfessor.selectAll();
                    }
                }
            }
        });

        tfNomeHorario.addActionListener(new ActionListener() {//pesquisa incremental
            @Override
            public void actionPerformed(ActionEvent ae) {

                List<String> listaAuxiliar = daoHorario.listInOrderNomeStrings("id");

                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, 400, 400).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        //String[] aux = selectedItem.split("-");
                        tfNomeHorario.setText(selectedItem);
                    } else {
                        tfNomeHorario.requestFocus();
                        tfNomeHorario.selectAll();
                    }
                }
            }
        });

        btInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String professortf = tfNomeProfessor.getText();
                String[] prof = professortf.split("-");
                int idProfessor = Integer.parseInt(prof[0]);

                String horariotf = tfNomeHorario.getText();
                String[] idm = horariotf.split("-");
                int idHorario = Integer.parseInt(idm[0]);

                System.out.println(idProfessor + "," + idHorario);
                Professor_Has_Horario professor_Has_Horario = new Professor_Has_Horario(idProfessor, idHorario);

                int retorno = professor_Has_Horario.encontradoNaLista();

                if (retorno == 1) {
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                            "Já existe este registro! \n"
                            + "Deseja excluir o <" + idm[1] + "> no professor <" + prof[1] + "> no banco de dados?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {

                        horario = daoHorario.obter(Integer.valueOf(idm[0]));
                        List<Professor> lp = horario.getProfessorList();
                        lp.remove(daoProfessor.obter(Integer.valueOf(prof[0])));
                        daoHorario.atualizar(horario);
                        tfNomeHorario.setText("");

//            lp.remove(daoProduto.obter(3));//remove o produto 3 na lista de produtos daquele fornecedor
//            daoFornecedor.atualizar(fornecedor); //atualiza a tabela que possui a entidade associativa em formato de List
//                        horario = daoHorario.obter(idHorario);
//                        List<Professor> lp = horario.getProfessorList();
//                        lp.add(daoProfessor.obter(idProfessor));
//                        horario.setProfessorList(lp);
//                        daoHorario.remover(horario);
                    }
                } else {
                    tfNomeHorario.setText("");

                }

            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                // Sai do sistema  
                dispose();
            }
        });

        setLocation(300, 200);
        setModal(true);
        setVisible(true);//faz a janela ficar visível

    }

    public static void main(String[] args) {
        new GUIProfessorHasHorario();
    }

}
