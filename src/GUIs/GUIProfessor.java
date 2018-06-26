package GUIs;

import DAOs.DAOProfessor;
import Entidades.Professor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

public class GUIProfessor extends JDialog {

    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);

    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";//variavel para facilitar insert e update
    DAOProfessor daoProfessor = new DAOProfessor();
    Professor professor;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    JLabel lbidProfessor = new JLabel("idProfessor");

    JTextField tfidProfessor = new JTextField(0);

    JLabel lbnomeProfessor = new JLabel("nomeProfessor");

    JTextField tfnomeProfessor = new JTextField(0);

    JLabel lbcpfProfessor = new JLabel("cpfProfessor");

    JTextField tfcpfProfessor = new JTextField(0);

    JLabel lbdatanascimentoProfessor = new JLabel("datanascimentoProfessor");

    JTextField tfdatanascimentoProfessor = new JTextField(0);

    JLabel lbcidadeProfessor = new JLabel("cidadeProfessor");

    JTextField tfcidadeProfessor = new JTextField(0);

    JLabel lbenderecoProfessor = new JLabel("enderecoProfessor");

    JTextField tfenderecoProfessor = new JTextField(0);

    JLabel lbemailProfessor = new JLabel("emailProfessor");

    JTextField tfemailProfessor = new JTextField(0);

    JLabel lbtelefoneProfessor = new JLabel("telefoneProfessor");

    JTextField tftelefoneProfessor = new JTextField(0);

    JLabel lbcrachaProfessor = new JLabel("crachaProfessor");

    JTextField tfcrachaProfessor = new JTextField(0);

    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
        btnCreate.setEnabled(c);
        btnRetrieve.setEnabled(r);
        btnUpdate.setEnabled(u);
        btnDelete.setEnabled(d);
        btnList.setEnabled(r);
    }

    public void mostrarBotoes(boolean visivel) {
        btnCreate.setVisible(visivel);
        btnRetrieve.setVisible(visivel);
        btnUpdate.setVisible(visivel);
        btnDelete.setVisible(visivel);
        btnList.setVisible(visivel);
        btnSave.setVisible(!visivel);
        btnCancel.setVisible(!visivel);
    }

    private void habilitarAtributos(
            boolean idProfessor,
             boolean nomeProfessor,
             boolean cpfProfessor,
             boolean datanascimentoProfessor,
             boolean cidadeProfessor,
             boolean enderecoProfessor,
             boolean emailProfessor,
             boolean telefoneProfessor,
             boolean crachaProfessor
    ) {
        if (idProfessor) {
            tfidProfessor.requestFocus();
            tfidProfessor.selectAll();
        }
        tfidProfessor.setEnabled(idProfessor);
        tfidProfessor.setEditable(idProfessor);

        tfnomeProfessor.setEditable(nomeProfessor);

        tfcpfProfessor.setEditable(cpfProfessor);

        tfdatanascimentoProfessor.setEditable(datanascimentoProfessor);

        tfcidadeProfessor.setEditable(cidadeProfessor);

        tfenderecoProfessor.setEditable(enderecoProfessor);

        tfemailProfessor.setEditable(emailProfessor);

        tftelefoneProfessor.setEditable(telefoneProfessor);

        tfcrachaProfessor.setEditable(crachaProfessor);

    }

    public void zerarAtributos() {
        tfnomeProfessor.setText("");

        tfcpfProfessor.setText("");

        tfdatanascimentoProfessor.setText("");

        tfcidadeProfessor.setText("");

        tfenderecoProfessor.setText("");

        tfemailProfessor.setText("");

        tftelefoneProfessor.setText("");

        tfcrachaProfessor.setText("");

    }

    public GUIProfessor() {
        setTitle("Professor");

        setSize(600, 400);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);

        habilitarAtributos(true,false,false,false,false,false,false,false,false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(lbidProfessor);
        Toolbar1.add(tfidProfessor);
        Toolbar1.add(btnRetrieve);
        Toolbar1.add(btnCreate);
        Toolbar1.add(btnUpdate);
        Toolbar1.add(btnDelete);
        Toolbar1.add(btnSave);
        Toolbar1.add(btnCancel);
        Toolbar1.add(btnList);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);  //atributos
        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(10, 2));
        centro.add(lbidProfessor);

        centro.add(tfidProfessor);

        centro.add(lbnomeProfessor);

        centro.add(tfnomeProfessor);

        centro.add(lbcpfProfessor);

        centro.add(tfcpfProfessor);

        centro.add(lbdatanascimentoProfessor);

        centro.add(tfdatanascimentoProfessor);

        centro.add(lbcidadeProfessor);

        centro.add(tfcidadeProfessor);

        centro.add(lbenderecoProfessor);

        centro.add(tfenderecoProfessor);

        centro.add(lbemailProfessor);

        centro.add(tfemailProfessor);

        centro.add(lbtelefoneProfessor);

        centro.add(tftelefoneProfessor);

        centro.add(lbcrachaProfessor);

        centro.add(tfcrachaProfessor);

        aviso.add(labelAviso);
        aviso.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);
        tfidProfessor.requestFocus();
        tfidProfessor.selectAll();
        tfidProfessor.setBackground(Color.GREEN);
        labelAviso.setText("Digite uma placa e clic [Pesquisar]");
        //setLocationRelativeTo(null); // posiciona no centro da tela principal
        
        tfidProfessor.addActionListener(new ActionListener() {//pesquisa incremental no id
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                List<String> listaAuxiliar = daoProfessor.listInOrderNomeStrings("id");
                        
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, 400, 400).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfidProfessor.setText(aux[0]);
                        btnRetrieve.doClick();//aciona o botao buscar
                    } else {
                        tfidProfessor.requestFocus();
                        tfidProfessor.selectAll();
                    }
                }
            }
        });
        
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                professor = new Professor();
                tfidProfessor.setText(tfidProfessor.getText().trim());//caso tenham sido digitados espaços

                if (tfidProfessor.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    tfidProfessor.requestFocus();
                    tfidProfessor.selectAll();
                } else {
                    professor.setIdProfessor(Integer.valueOf(tfidProfessor.getText()));
                    professor = daoProfessor.obter(professor.getIdProfessor());
                    if (professor != null) { //se encontrou na lista
                        tfidProfessor.setText(String.valueOf(professor.getIdProfessor()));
                        tfnomeProfessor.setText(professor.getNomeProfessor());
                        tfcpfProfessor.setText(professor.getCpfProfessor());
                        tfdatanascimentoProfessor.setText(sdf.format(professor.getDatanascimentoProfessor()));
                        tfcidadeProfessor.setText(professor.getCidadeProfessor());
                        tfenderecoProfessor.setText(professor.getEnderecoProfessor());
                        tfemailProfessor.setText(professor.getEmailProfessor());
                        tftelefoneProfessor.setText(professor.getTelefoneProfessor());
                        tfcrachaProfessor.setText(String.valueOf(professor.getCrachaProfessor()));//imagem
                        habilitarAtributos(true,false,false,false,false,false,false,false,false);
                        atvBotoes(false, true, true, true);
                        labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                        acao = "encontrou";

                    } else {
                        atvBotoes(true, true, false, false);
                        zerarAtributos();
                        labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                    }
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();

                habilitarAtributos(false,
                         true,
                         true,
                         true,
                         true,
                         true,
                         true,
                         true,
                         true
                );
                tfnomeProfessor.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });
        btnSave.addActionListener(new ActionListener() {//não sei
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (acao.equals("insert")) {
                    professor = new Professor();

                    professor.setIdProfessor(Integer.valueOf(tfidProfessor.getText()));
                    professor.setNomeProfessor(tfnomeProfessor.getText());
                    professor.setCpfProfessor(tfcpfProfessor.getText());
                    try {
                        professor.setDatanascimentoProfessor(sdf.parse(tfdatanascimentoProfessor.getText()));
                    } catch (ParseException ex) {
                        System.out.println("Erro na data");
                    }
                    professor.setCidadeProfessor(tfcidadeProfessor.getText());
                    professor.setEnderecoProfessor(tfenderecoProfessor.getText());
                    professor.setEmailProfessor(tfemailProfessor.getText());
                    professor.setTelefoneProfessor(tftelefoneProfessor.getText());
                    professor.setCrachaProfessor(Integer.valueOf(tfcrachaProfessor.getText()));
                    daoProfessor.inserir(professor);
                    habilitarAtributos(true,false,false,false,false,false,false,false,false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    professor.setIdProfessor(Integer.valueOf(tfidProfessor.getText()));
                    professor.setNomeProfessor(tfnomeProfessor.getText());
                    professor.setCpfProfessor(tfcpfProfessor.getText());
                    try {
                        professor.setDatanascimentoProfessor(sdf.parse(tfdatanascimentoProfessor.getText()));
                    } catch (ParseException ex) {
                        System.out.println("Erro na data");
                    }
                    professor.setCidadeProfessor(tfcidadeProfessor.getText());
                    professor.setEnderecoProfessor(tfenderecoProfessor.getText());
                    professor.setEmailProfessor(tfemailProfessor.getText());
                    professor.setTelefoneProfessor(tftelefoneProfessor.getText());
                    professor.setCrachaProfessor(Integer.valueOf(tfcrachaProfessor.getText()));
                    daoProfessor.atualizar(professor);
                    habilitarAtributos(true,false,false,false,false,false,false,false,false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro atualizado...");
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);

                habilitarAtributos(true,false,false,false,false,false,false,false,false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                GUIListagemProfessor guiListagem = new GUIListagemProfessor(daoProfessor.listInOrderNome());
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);

                habilitarAtributos(false,true,true,true,true,true,true,true,true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + professor.getIdProfessor() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoProfessor.remover(professor);
                    zerarAtributos();
                    tfidProfessor.requestFocus();
                    tfidProfessor.selectAll();
                }
            }
        });
        tfidProfessor.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                tfidProfessor.setBackground(Color.GREEN);
                if (acao != "encontrou") {
                    labelAviso.setText("Digite uma Id e clic [Pesquisar]");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                tfidProfessor.setBackground(Color.white);
            }
        });
        tfidProfessor.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfidProfessor.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfidProfessor.setBackground(Color.white);
            }
        });
        tfnomeProfessor.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfnomeProfessor.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfnomeProfessor.setBackground(Color.white);
            }
        });
        tfcpfProfessor.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfcpfProfessor.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfcpfProfessor.setBackground(Color.white);
            }
        });
        tfdatanascimentoProfessor.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfdatanascimentoProfessor.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfdatanascimentoProfessor.setBackground(Color.white);
            }
        });
        tfcidadeProfessor.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfcidadeProfessor.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfcidadeProfessor.setBackground(Color.white);
            }
        });
        tfenderecoProfessor.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfenderecoProfessor.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfenderecoProfessor.setBackground(Color.white);
            }
        });
        tfemailProfessor.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfemailProfessor.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfemailProfessor.setBackground(Color.white);
            }
        });
        tftelefoneProfessor.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tftelefoneProfessor.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tftelefoneProfessor.setBackground(Color.white);
            }
        });
        tfcrachaProfessor.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfcrachaProfessor.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfcrachaProfessor.setBackground(Color.white);
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                // Sai do sistema  
                System.exit(0);
            }
        });
        
        setLocation(300, 200);
        setModal(true);
        setVisible(true);//faz a janela ficar visível
    }

    public static void main(String[] args) {
        new GUIProfessor();
    }
}
