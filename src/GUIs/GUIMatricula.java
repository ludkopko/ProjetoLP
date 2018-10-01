/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIs;


import Entidades.Turma;
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
public class GUIMatricula extends JDialog {

    DAOs.DAOTurma daoTurma = new DAOs.DAOTurma();
    Entidades.Turma turma = new Entidades.Turma();
    DAOs.DAOAluno daoAluno = new DAOs.DAOAluno();
    Entidades.Aluno aluno = new Entidades.Aluno();

    JLabel lbInfo = new JLabel("Selecione um aluno por vez para determinado turma");

    JLabel lbNomeTurma = new JLabel("Turma");
    JTextField tfNomeTurma = new JTextField(20);
    JLabel lbNomeAluno = new JLabel("Aluno");
    JTextField tfNomeAluno = new JTextField(20);

    JButton btInserir = new JButton("Inserir");

    JPanel pnTotal = new JPanel(new GridLayout(3, 1));
    JPanel pnLabel = new JPanel(new FlowLayout(1));
    JPanel pnUm = new JPanel(new GridLayout(1, 2));
    JPanel pnUmUm = new JPanel(new FlowLayout(1));
    JPanel pnUmDois = new JPanel(new FlowLayout(1));
    JPanel pnDois = new JPanel(new FlowLayout(1));

    public GUIMatricula() {
        setTitle("Adicionar Aluno ao Turma");
        setSize(500, 300);//tamanho da janela
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros 
        
        pnLabel.add(lbInfo);

        pnUmUm.add(lbNomeTurma);
        pnUmUm.add(tfNomeTurma);
        pnUmDois.add(lbNomeAluno);
        pnUmDois.add(tfNomeAluno);

        pnUm.add(pnUmUm);
        pnUm.add(pnUmDois);
        pnDois.add(btInserir);

        pnTotal.add(pnLabel);
        pnTotal.add(pnUm);
        pnTotal.add(pnDois);

        cp.add(pnTotal);

        tfNomeTurma.addActionListener(new ActionListener() {//pesquisa incremental
            @Override
            public void actionPerformed(ActionEvent ae) {

                List<String> listaAuxiliar = daoTurma.listInOrderNomeStrings("id");

                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, 400, 400).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        //String[] aux = selectedItem.split("-");
                        tfNomeTurma.setText(selectedItem);
                    } else {
                        tfNomeTurma.requestFocus();
                        tfNomeTurma.selectAll();
                    }
                }
            }
        });

        tfNomeAluno.addActionListener(new ActionListener() {//pesquisa incremental
            @Override
            public void actionPerformed(ActionEvent ae) {

                List<String> listaAuxiliar = daoAluno.listInOrderNomeStrings("id");

                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, 400, 400).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        //String[] aux = selectedItem.split("-");
                        tfNomeAluno.setText(selectedItem);
                    } else {
                        tfNomeAluno.requestFocus();
                        tfNomeAluno.selectAll();
                    }
                }
            }
        });

        btInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String turmatf = tfNomeTurma.getText();
                String[] tur = turmatf.split("-");
                int idTurma = Integer.parseInt(tur[0]);

                String alunotf = tfNomeAluno.getText();
                String[] alu = alunotf.split("-");
                int idAluno = Integer.parseInt(alu[0]);

                System.out.println(idTurma + "," + idAluno);
                Entidades.Matricula matricula = new Entidades.Matricula(idTurma, idAluno);

                int retorno = matricula.encontradoNaLista();

                if (retorno == 1) {
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                            "Já existe este registro! \n"
                            + "Deseja excluir o <" + alu[1] + "> no turma <" + tur[1] + "> no banco de dados?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {

                        aluno = daoAluno.obter(Integer.valueOf(alu[0]));
                        List<Turma> lp = aluno.getTurmaList();
                        lp.remove(daoTurma.obter(Integer.valueOf(tur[0])));
                        daoAluno.atualizar(aluno);
                        tfNomeAluno.setText("");

//            lp.remove(daoProduto.obter(3));//remove o produto 3 na lista de produtos daquele fornecedor
//            daoFornecedor.atualizar(fornecedor); //atualiza a tabela que possui a entidade associativa em formato de List
//                        aluno = daoAluno.obter(idAluno);
//                        List<Turma> lp = aluno.getTurmaList();
//                        lp.add(daoTurma.obter(idTurma));
//                        aluno.setTurmaList(lp);
//                        daoAluno.remover(aluno);
                    }
                } else {
                    tfNomeAluno.setText("");

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
        new GUIMatricula();
    }

}
