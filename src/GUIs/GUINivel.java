package GUIs;

import DAOs.DAOIdioma;
import DAOs.DAONivel;
import Entidades.Idioma;
import Entidades.Nivel;
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

public class GUINivel extends JDialog {

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
    DAONivel daoNivel = new DAONivel();
    DAOIdioma daoIdioma = new DAOIdioma();
    Nivel nivel;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    JLabel lbidNivel = new JLabel("idNivel");

    JTextField tfidNivel = new JTextField(0);

    JLabel lbnomeNivel = new JLabel("nomeNivel");

    JTextField tfnomeNivel = new JTextField(0);

    JLabel lbidiomaIdIdioma = new JLabel("idiomaIdIdioma");

    JTextField tfidiomaIdIdioma = new JTextField(0);

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
            boolean idNivel,
             boolean nomeNivel,
             boolean idiomaIdIdioma
    ) {
        if (idNivel) {
            tfidNivel.requestFocus();
            tfidNivel.selectAll();
        }
        tfidNivel.setEnabled(idNivel);
        tfidNivel.setEditable(idNivel);

        tfnomeNivel.setEditable(nomeNivel);

        tfidiomaIdIdioma.setEditable(idiomaIdIdioma);

    }

    public void zerarAtributos() {
        tfnomeNivel.setText("");

        tfidiomaIdIdioma.setText("");

    }

    public GUINivel() {
        setTitle("Nivel");

        setSize(600, 400);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);

        habilitarAtributos(true,
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
        Toolbar1.add(lbidNivel);
        Toolbar1.add(tfidNivel);
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
        centro.setLayout(new GridLayout(4, 2));
        centro.add(lbidNivel);

        centro.add(tfidNivel);

        centro.add(lbnomeNivel);

        centro.add(tfnomeNivel);

        centro.add(lbidiomaIdIdioma);

        centro.add(tfidiomaIdIdioma);

        aviso.add(labelAviso);
        aviso.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);
        tfidNivel.requestFocus();
        tfidNivel.selectAll();
        tfidNivel.setBackground(Color.GREEN);
        labelAviso.setText("Digite uma placa e clic [Pesquisar]");
        //setLocationRelativeTo(null); // posiciona no centro da tela principal
        
        tfidNivel.addActionListener(new ActionListener() {//pesquisa incremental
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                List<String> listaAuxiliar = daoNivel.listInOrderNomeStrings("id");
                        
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, 400, 400).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfidNivel.setText(aux[0]);
                        btnRetrieve.doClick();//aciona o botao buscar
                    } else {
                        tfidNivel.requestFocus();
                        tfidNivel.selectAll();
                    }
                }
            }
        });
        
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                nivel = new Nivel();
                tfidNivel.setText(tfidNivel.getText().trim());//caso tenham sido digitados espaços

                if (tfidNivel.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    tfidNivel.requestFocus();
                    tfidNivel.selectAll();
                } else {
                    nivel.setIdNivel(Integer.valueOf(tfidNivel.getText()));
                    nivel = daoNivel.obter(nivel.getIdNivel());
                    if (nivel != null) { //se encontrou na lista
                        tfidNivel.setText(String.valueOf(nivel.getIdNivel()));
                        tfnomeNivel.setText(nivel.getNomeNivel());
                        Idioma idioma = daoIdioma.obter(Integer.valueOf(nivel.getIdiomaIdIdioma().getIdIdioma()));
                        tfidiomaIdIdioma.setText(String.valueOf(idioma.getIdIdioma()+
                                "-" + idioma.getNomeIdioma()));
                        habilitarAtributos(true,false,false);
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
                         true
                );
                tfnomeNivel.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });
        btnSave.addActionListener(new ActionListener() {//não sei
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (acao.equals("insert")) {
                    nivel = new Nivel();

                    nivel.setIdNivel(Integer.valueOf(tfidNivel.getText()));
                    nivel.setNomeNivel(tfnomeNivel.getText());
                    
                    String[] aux = tfidiomaIdIdioma.getText().split("-");
                    Idioma idioma = daoIdioma.obter(Integer.valueOf(aux[0]));
                    nivel.setIdiomaIdIdioma(idioma);
                    
                    daoNivel.inserir(nivel);
                    habilitarAtributos(true,false,false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    nivel.setIdNivel(Integer.valueOf(tfidNivel.getText()));
                    nivel.setNomeNivel(tfnomeNivel.getText());
                    
                    String[] aux = tfidiomaIdIdioma.getText().split("-");
                    Idioma idioma = daoIdioma.obter(Integer.valueOf(aux[0]));
                    nivel.setIdiomaIdIdioma(idioma);
                    
                    daoNivel.atualizar(nivel);
                    habilitarAtributos(true,false,false);
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
                         false
                );
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                GUIListagemNivel guiListagem = new GUIListagemNivel(daoNivel.listInOrderNome());
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);

                habilitarAtributos(false,
                         true,
                         true
                );
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + nivel.getIdNivel() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoNivel.remover(nivel);
                    zerarAtributos();
                    tfidNivel.requestFocus();
                    tfidNivel.selectAll();
                }
            }
        });
        tfidiomaIdIdioma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                List<String> listaAuxiliar = daoIdioma.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, 750, 200).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split(";");
                        tfidiomaIdIdioma.setText(aux[0]);
                    } else {
                        tfidiomaIdIdioma.requestFocus();
                        tfidiomaIdIdioma.selectAll();
                    }
                }
                
            }
        });
        
        
        tfidNivel.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                tfidNivel.setBackground(Color.GREEN);
                if (acao != "encontrou") {
                    labelAviso.setText("Digite uma Id e clic [Pesquisar]");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                tfidNivel.setBackground(Color.white);
            }
        });
        tfidNivel.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfidNivel.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfidNivel.setBackground(Color.white);
            }
        });
        tfnomeNivel.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfnomeNivel.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfnomeNivel.setBackground(Color.white);
            }
        });
        tfidiomaIdIdioma.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfidiomaIdIdioma.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfidiomaIdIdioma.setBackground(Color.white);
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
        new GUINivel();
    }
}
