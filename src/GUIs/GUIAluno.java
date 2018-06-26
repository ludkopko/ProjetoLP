package GUIs;

import DAOs.DAOAluno;
import Entidades.Aluno;
import static com.sun.glass.ui.Cursor.setVisible;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

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

public class GUIAluno extends JDialog {

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
    DAOAluno daoAluno = new DAOAluno();
    Aluno aluno;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    JLabel lbidAluno = new JLabel("idAluno");

    JTextField tfidAluno = new JTextField(0);

    JLabel lbnomeAluno = new JLabel("nomeAluno");

    JTextField tfnomeAluno = new JTextField(0);

    JLabel lbcpfAluno = new JLabel("cpfAluno");

    JTextField tfcpfAluno = new JTextField(0);

    JLabel lbdatanascimentoAluno = new JLabel("datanascimentoAluno");

    JTextField tfdatanascimentoAluno = new JTextField(0);

    JLabel lbcidadeAluno = new JLabel("cidadeAluno");

    JTextField tfcidadeAluno = new JTextField(0);

    JLabel lbenderecoAluno = new JLabel("enderecoAluno");

    JTextField tfenderecoAluno = new JTextField(0);

    JLabel lbemailAluno = new JLabel("emailAluno");

    JTextField tfemailAluno = new JTextField(0);

    JLabel lbtelefoneAluno = new JLabel("telefoneAluno");

    JTextField tftelefoneAluno = new JTextField(0);

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
            boolean idAluno,
             boolean nomeAluno,
             boolean cpfAluno,
             boolean datanascimentoAluno,
             boolean cidadeAluno,
             boolean enderecoAluno,
             boolean emailAluno,
             boolean telefoneAluno
    ) {
        if (idAluno) {
            tfidAluno.requestFocus();
            tfidAluno.selectAll();
        }
        tfidAluno.setEnabled(idAluno);
        tfidAluno.setEditable(idAluno);

        tfnomeAluno.setEditable(nomeAluno);

        tfcpfAluno.setEditable(cpfAluno);

        tfdatanascimentoAluno.setEditable(datanascimentoAluno);

        tfcidadeAluno.setEditable(cidadeAluno);

        tfenderecoAluno.setEditable(enderecoAluno);

        tfemailAluno.setEditable(emailAluno);

        tftelefoneAluno.setEditable(telefoneAluno);

    }

    public void zerarAtributos() {
        tfnomeAluno.setText("");

        tfcpfAluno.setText("");

        tfdatanascimentoAluno.setText("");

        tfcidadeAluno.setText("");

        tfenderecoAluno.setText("");

        tfemailAluno.setText("");

        tftelefoneAluno.setText("");

    }

    public GUIAluno() {
        setTitle("Aluno");

        setSize(600, 400);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);

        habilitarAtributos(true,
                 false,
                 false,
                 false,
                 false,
                 false,
                 false,
                 false
        );
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(lbidAluno);
        Toolbar1.add(tfidAluno);
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
        centro.setLayout(new GridLayout(9, 2));
        centro.add(lbidAluno);

        centro.add(tfidAluno);

        centro.add(lbnomeAluno);

        centro.add(tfnomeAluno);

        centro.add(lbcpfAluno);

        centro.add(tfcpfAluno);

        centro.add(lbdatanascimentoAluno);

        centro.add(tfdatanascimentoAluno);

        centro.add(lbcidadeAluno);

        centro.add(tfcidadeAluno);

        centro.add(lbenderecoAluno);

        centro.add(tfenderecoAluno);

        centro.add(lbemailAluno);

        centro.add(tfemailAluno);

        centro.add(lbtelefoneAluno);

        centro.add(tftelefoneAluno);

        aviso.add(labelAviso);
        aviso.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);
        tfidAluno.requestFocus();
        tfidAluno.selectAll();
        tfidAluno.setBackground(Color.GREEN);
        labelAviso.setText("Digite uma placa e clic [Pesquisar]");
        //setLocationRelativeTo(null); // posiciona no centro da tela principal
        
        tfidAluno.addActionListener(new ActionListener() {//pesquisa incremental no id
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                List<String> listaAuxiliar = daoAluno.listInOrderNomeStrings("id");
                        
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, 400, 400).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfidAluno.setText(aux[0]);
                        btnRetrieve.doClick();//aciona o botao buscar
                    } else {
                        tfidAluno.requestFocus();
                        tfidAluno.selectAll();
                    }
                }
            }
        });
        
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                aluno = new Aluno();
                tfidAluno.setText(tfidAluno.getText().trim());//caso tenham sido digitados espaços

                if (tfidAluno.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    tfidAluno.requestFocus();
                    tfidAluno.selectAll();
                } else {
                    aluno.setIdAluno(Integer.valueOf(tfidAluno.getText()));
                    aluno = daoAluno.obter(aluno.getIdAluno());
                    if (aluno != null) { //se encontrou na lista
                        tfidAluno.setText(String.valueOf(aluno.getIdAluno()));
                        tfnomeAluno.setText(aluno.getNomeAluno());
                        tfcpfAluno.setText(aluno.getCpfAluno());
                        tfdatanascimentoAluno.setText(sdf.format(aluno.getDatanascimentoAluno()));
                        tfcidadeAluno.setText(aluno.getCidadeAluno());
                        tfenderecoAluno.setText(aluno.getEnderecoAluno());
                        tfemailAluno.setText(aluno.getEmailAluno());
                        tftelefoneAluno.setText(aluno.getTelefoneAluno());
                        habilitarAtributos(true,false,false,false,false,false,false,false);
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
                         true
                );
                tfnomeAluno.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });
        btnSave.addActionListener(new ActionListener() {//não sei
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (acao.equals("insert")) {
                    aluno = new Aluno();

                    aluno.setIdAluno(Integer.valueOf(tfidAluno.getText()));
                    aluno.setNomeAluno(tfnomeAluno.getText());
                    aluno.setCpfAluno(tfcpfAluno.getText());
                    try {
                        aluno.setDatanascimentoAluno(sdf.parse(tfdatanascimentoAluno.getText()));
                    } catch (ParseException ex) {
                        System.out.println("Erro na data");
                    }
                    aluno.setCidadeAluno(tfcidadeAluno.getText());
                    aluno.setEnderecoAluno(tfenderecoAluno.getText());
                    aluno.setEmailAluno(tfemailAluno.getText());
                    aluno.setTelefoneAluno(tftelefoneAluno.getText());
                    daoAluno.inserir(aluno);
                    habilitarAtributos(true,false,false,false,false,false,false,false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    aluno.setIdAluno(Integer.valueOf(tfidAluno.getText()));
                    aluno.setNomeAluno(tfnomeAluno.getText());
                    aluno.setCpfAluno(tfcpfAluno.getText());
                    try {
                        aluno.setDatanascimentoAluno(sdf.parse(tfdatanascimentoAluno.getText()));
                    } catch (ParseException ex) {
                        System.out.println("Erro na data");
                    }
                    aluno.setCidadeAluno(tfcidadeAluno.getText());
                    aluno.setEnderecoAluno(tfenderecoAluno.getText());
                    aluno.setEmailAluno(tfemailAluno.getText());
                    aluno.setTelefoneAluno(tftelefoneAluno.getText());
                    daoAluno.atualizar(aluno);
                    habilitarAtributos(true,false,false,false,false,false,false,false);
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

                habilitarAtributos(true,
                         false,
                         false,
                         false,
                         false,
                         false,
                         false,
                         false
                );
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                GUIListagemAluno guiListagem = new GUIListagemAluno(daoAluno.listInOrderNome());
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);

                habilitarAtributos(false,
                         true,
                         true,
                         true,
                         true,
                         true,
                         true,
                         true
                );
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + aluno.getIdAluno() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoAluno.remover(aluno);
                    zerarAtributos();
                    tfidAluno.requestFocus();
                    tfidAluno.selectAll();
                }
            }
        });
        tfidAluno.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                tfidAluno.setBackground(Color.GREEN);
                if (acao != "encontrou") {
                    labelAviso.setText("Digite uma Id e clic [Pesquisar]");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                tfidAluno.setBackground(Color.white);
            }
        });
        tfidAluno.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfidAluno.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfidAluno.setBackground(Color.white);
            }
        });
        tfnomeAluno.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfnomeAluno.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfnomeAluno.setBackground(Color.white);
            }
        });
        tfcpfAluno.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfcpfAluno.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfcpfAluno.setBackground(Color.white);
            }
        });
        tfdatanascimentoAluno.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfdatanascimentoAluno.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfdatanascimentoAluno.setBackground(Color.white);
            }
        });
        tfcidadeAluno.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfcidadeAluno.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfcidadeAluno.setBackground(Color.white);
            }
        });
        tfenderecoAluno.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfenderecoAluno.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfenderecoAluno.setBackground(Color.white);
            }
        });
        tfemailAluno.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfemailAluno.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfemailAluno.setBackground(Color.white);
            }
        });
        tftelefoneAluno.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tftelefoneAluno.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tftelefoneAluno.setBackground(Color.white);
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
        new GUIAluno();
    }
}
