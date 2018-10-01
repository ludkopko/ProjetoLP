package GUIs;

import DAOs.DAOIdioma;
import Entidades.Idioma;
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

public class GUIIdioma extends JDialog {

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
    DAOIdioma daoIdioma = new DAOIdioma();
    Idioma idioma;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    JLabel lbidIdioma = new JLabel("idIdioma");

    JTextField tfidIdioma = new JTextField(0);

    JLabel lbnomeIdioma = new JLabel("nomeIdioma");

    JTextField tfnomeIdioma = new JTextField(0);

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
            boolean idIdioma,
             boolean nomeIdioma
    ) {
        if (idIdioma) {
            tfidIdioma.requestFocus();
            tfidIdioma.selectAll();
        }
        tfidIdioma.setEnabled(idIdioma);
        tfidIdioma.setEditable(idIdioma);

        tfnomeIdioma.setEditable(nomeIdioma);

    }

    public void zerarAtributos() {
        tfnomeIdioma.setText("");

    }

    public GUIIdioma() {
        setTitle("Idioma");

        setSize(600, 400);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);

        habilitarAtributos(true,
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
        Toolbar1.add(lbidIdioma);
        Toolbar1.add(tfidIdioma);
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
        centro.setLayout(new GridLayout(3, 2));
        centro.add(lbidIdioma);

        centro.add(tfidIdioma);

        centro.add(lbnomeIdioma);

        centro.add(tfnomeIdioma);

        aviso.add(labelAviso);
        aviso.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);
        tfidIdioma.requestFocus();
        tfidIdioma.selectAll();
        tfidIdioma.setBackground(Color.GREEN);
        labelAviso.setText("Digite uma placa e clic [Pesquisar]");
        //setLocationRelativeTo(null); // posiciona no centro da tela principal
        
        tfidIdioma.addActionListener(new ActionListener() {//pesquisa incremental no id
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                List<String> listaAuxiliar = daoIdioma.listInOrderNomeStrings("id");
                        
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, 400, 400).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfidIdioma.setText(aux[0]);
                        btnRetrieve.doClick();//aciona o botao buscar
                    } else {
                        tfidIdioma.requestFocus();
                        tfidIdioma.selectAll();
                    }
                }
            }
        });
        
        
        
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                idioma = new Idioma();
                tfidIdioma.setText(tfidIdioma.getText().trim());//caso tenham sido digitados espaços

                if (tfidIdioma.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    tfidIdioma.requestFocus();
                    tfidIdioma.selectAll();
                } else {
                    idioma.setIdIdioma(Integer.valueOf(tfidIdioma.getText()));
                    idioma = daoIdioma.obter(idioma.getIdIdioma());
                    if (idioma != null) { //se encontrou na lista
                        tfidIdioma.setText(String.valueOf(idioma.getIdIdioma()));
                        tfnomeIdioma.setText(idioma.getNomeIdioma());
                        habilitarAtributos(true,false);
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
                         true
                );
                tfnomeIdioma.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });
        btnSave.addActionListener(new ActionListener() {//não sei
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (acao.equals("insert")) {
                    idioma = new Idioma();

                    idioma.setIdIdioma(Integer.valueOf(tfidIdioma.getText()));
                    idioma.setNomeIdioma(tfnomeIdioma.getText());
                    daoIdioma.inserir(idioma);
                    habilitarAtributos(true,
                             false
                    );
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    idioma.setIdIdioma(Integer.valueOf(tfidIdioma.getText()));
                    idioma.setNomeIdioma(tfnomeIdioma.getText());
                    daoIdioma.atualizar(idioma);
                    habilitarAtributos(true,
                             false
                    );
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
                         false
                );
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
//                GUIListagemIdioma guiListagem = new GUIListagemIdioma(daoIdioma.listInOrderNome());
                new GUIListagemIdioma(daoIdioma.listInOrderNomeStrings("id"), cp);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);

                habilitarAtributos(false,
                         true
                );
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + idioma.getIdIdioma() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoIdioma.remover(idioma);
                    zerarAtributos();
                    tfidIdioma.requestFocus();
                    tfidIdioma.selectAll();
                }
            }
        });
        tfidIdioma.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                tfidIdioma.setBackground(Color.GREEN);
                if (acao != "encontrou") {
                    labelAviso.setText("Digite uma Id e clic [Pesquisar]");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                tfidIdioma.setBackground(Color.white);
            }
        });
        tfidIdioma.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfidIdioma.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfidIdioma.setBackground(Color.white);
            }
        });
        tfnomeIdioma.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfnomeIdioma.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfnomeIdioma.setBackground(Color.white);
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
        new GUIIdioma();
    }
}
