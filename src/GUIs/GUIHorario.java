package GUIs;

import DAOs.DAOHorario;
import Entidades.Horario;
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
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static jdk.nashorn.internal.objects.NativeDebug.getClass;

public class GUIHorario extends JDialog {

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
    DAOHorario daoHorario = new DAOHorario();
    Horario horario;

    SpinnerDateModel dateModel = new SpinnerDateModel();
    JSpinner spinner = new JSpinner(dateModel);
    JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinner, "HH:mm");

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    JLabel lbidHorario = new JLabel("Id Horário");
    JTextField tfidHorario = new JTextField(0);
    JLabel lbcodigoHorario = new JLabel("Código do Horário");
    JTextField tfcodigoHorario = new JTextField(0);
    JLabel lbhorasHorario = new JLabel("Horas");
//    JTextField tfhorasHorario = new JTextField(0);

//        SpinnerModel value =  
//             new SpinnerNumberModel(5, //initial value  
//                0, //minimum value  
//                10, //maximum value  
//                1); //step  
//    JSpinner spinner = new JSpinner(value);   
//            spinner.setBounds(100,100,50,30);  
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
            boolean idHorario,
            boolean codigoHorario,
            boolean horasHorario
    ) {
        if (idHorario) {
            tfidHorario.requestFocus();
            tfidHorario.selectAll();
        }
        tfidHorario.setEnabled(idHorario);
        tfidHorario.setEditable(idHorario);

        tfcodigoHorario.setEditable(codigoHorario);

        spinner.setEnabled(horasHorario);
//      apagar  spinner.setEditable(horasHorario);

    }

    public void zerarAtributos() {
        tfcodigoHorario.setText("");
//        spinner.
//
//        tfhorasHorario.setText("");

    }

    public GUIHorario() {
        setTitle("Horario");

        setSize(600, 400);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        spinner.setEditor(timeEditor);
        Time time = new Time(dateModel.getDate().getTime());

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
        Toolbar1.add(lbidHorario);
        Toolbar1.add(tfidHorario);
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
        centro.add(lbidHorario);

        centro.add(tfidHorario);

        centro.add(lbcodigoHorario);

        centro.add(tfcodigoHorario);

        centro.add(lbhorasHorario);

        centro.add(spinner);

        aviso.add(labelAviso);
        aviso.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);
        tfidHorario.requestFocus();
        tfidHorario.selectAll();
        tfidHorario.setBackground(Color.GREEN);
        labelAviso.setText("Digite uma placa e clic [Pesquisar]");
        //setLocationRelativeTo(null); // posiciona no centro da tela principal
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                horario = new Horario();
                tfidHorario.setText(tfidHorario.getText().trim());//caso tenham sido digitados espaços

                if (tfidHorario.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    tfidHorario.requestFocus();
                    tfidHorario.selectAll();
                } else {
                    horario.setIdHorario(Integer.valueOf(tfidHorario.getText()));
                    horario = daoHorario.obter(horario.getIdHorario());
                    if (horario != null) { //se encontrou na lista
                        tfidHorario.setText(String.valueOf(horario.getIdHorario()));
                        tfcodigoHorario.setText(horario.getCodigoHorario());
                        Date hora = null;
                        try {
                            hora = sdf.parse(horario.getHorasHorario());
                        } catch (ParseException ex) {
                            Logger.getLogger(GUIHorario.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        dateModel.setValue(hora);

                        habilitarAtributos(true, false, false);
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
                tfcodigoHorario.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });
        btnSave.addActionListener(new ActionListener() {//não sei
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (acao.equals("insert")) {
                    horario = new Horario();

                    horario.setIdHorario(Integer.valueOf(tfidHorario.getText()));
                    horario.setCodigoHorario(tfcodigoHorario.getText());
                    Date data = dateModel.getDate();
                    horario.setHorasHorario(String.valueOf(new SimpleDateFormat("HH:mm").format(data)));
//                    Date date = (Date) spinner.ge
//                    horario.setHorasHorario((String.valueOf(spinner)).getText());
                    daoHorario.inserir(horario);
                    habilitarAtributos(true, false, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    horario.setIdHorario(Integer.valueOf(tfidHorario.getText()));
                    horario.setCodigoHorario(tfcodigoHorario.getText());
                    Date data = dateModel.getDate();
                    horario.setHorasHorario(String.valueOf(new SimpleDateFormat("HH:mm").format(data)));
                    daoHorario.atualizar(horario);
                    habilitarAtributos(true, false, false);
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
                GUIListagemHorario guiListagem = new GUIListagemHorario(daoHorario.listInOrderId());
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
                        "Confirma a exclusão do registro <ID = " + horario.getIdHorario() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoHorario.remover(horario);
                    zerarAtributos();
                    tfidHorario.requestFocus();
                    tfidHorario.selectAll();
                }
            }
        });
        tfidHorario.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                tfidHorario.setBackground(Color.GREEN);
                if (acao != "encontrou") {
                    labelAviso.setText("Digite uma Id e clic [Pesquisar]");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                tfidHorario.setBackground(Color.white);
            }
        });
        tfidHorario.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfidHorario.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfidHorario.setBackground(Color.white);
            }
        });
        tfcodigoHorario.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfcodigoHorario.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfcodigoHorario.setBackground(Color.white);
            }
        });
        spinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                Date date = (Date) ((JSpinner) e.getSource()).getValue();
//                labelTempo.setText(String.valueOf(new SimpleDateFormat("HH:mm").format(date)));
            }
        });
//   pode apagar     tfhorasHorario.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
//            @Override
//            public void focusGained(FocusEvent fe) {
//                tfhorasHorario.setBackground(Color.GREEN);
//            }
//
//            @Override
//            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
//                tfhorasHorario.setBackground(Color.white);
//            }
//        });
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
        new GUIHorario();
    }
}
