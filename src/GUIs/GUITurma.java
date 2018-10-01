package GUIs;

import DAOs.DAOHorario;
import DAOs.DAONivel;
import DAOs.DAOProfessor;
import DAOs.DAOTurma;
import Entidades.Horario;
import Entidades.Nivel;
import Entidades.Professor;
import Entidades.Turma;
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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

public class GUITurma extends JDialog {

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
    DAOTurma daoTurma = new DAOTurma();
    DAONivel daoNivel = new DAONivel();
    DAOHorario daoHorario = new DAOHorario();
    DAOProfessor daoProfessor = new DAOProfessor();
    Horario horario = new Horario();
    Turma turma;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    JLabel lbidTurma = new JLabel("idTurma");

    JTextField tfidTurma = new JTextField(0);

    JLabel lbnomeTurma = new JLabel("nomeTurma");

    JTextField tfnomeTurma = new JTextField(0);

    JLabel lbdiaTurma = new JLabel("diaTurma");
    JComboBox comboDiaTurma = new JComboBox();

    //JTextField tfdiaTurma = new JTextField(0);
    JLabel lbhorarioinicioTurma = new JLabel("horarioinicioTurma");

    JTextField tfhorarioinicioTurma = new JTextField(0);

    JLabel lbhorariofimTurma = new JLabel("horariofimTurma");

    JTextField tfhorariofimTurma = new JTextField(0);

    JLabel lbnivelIdNivel = new JLabel("nivelIdNivel");

    JTextField tfnivelIdNivel = new JTextField(0);

    JLabel lbprofessorIdProfessor = new JLabel("professorIdProfessor");

    JTextField tfprofessorIdProfessor = new JTextField(0);

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
            boolean idTurma,
            boolean nomeTurma,
            boolean diaTurma,
            boolean horarioinicioTurma,
            boolean horariofimTurma,
            boolean nivelIdNivel,
            boolean professorIdProfessor
    ) {
        if (idTurma) {
            tfidTurma.requestFocus();
            tfidTurma.selectAll();
        }
        tfidTurma.setEnabled(idTurma);
        tfidTurma.setEditable(idTurma);

        tfnomeTurma.setEditable(nomeTurma);

        comboDiaTurma.setEnabled(diaTurma);

        tfhorarioinicioTurma.setEditable(horarioinicioTurma);

        tfhorariofimTurma.setEditable(horariofimTurma);

        tfnivelIdNivel.setEditable(nivelIdNivel);

        tfprofessorIdProfessor.setEditable(professorIdProfessor);

    }

    public void zerarAtributos() {
        tfnomeTurma.setText("");

        //comboDiaTurma.;
        tfhorarioinicioTurma.setText("");

        tfhorariofimTurma.setText("");

        tfnivelIdNivel.setText("");

        tfprofessorIdProfessor.setText("");

    }

    public GUITurma() {
        setTitle("Turma");

        setSize(600, 400);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        comboDiaTurma.addItem("*SELECIONE*");
        comboDiaTurma.addItem("Segunda");
        comboDiaTurma.addItem("Terça");
        comboDiaTurma.addItem("Quarta");
        comboDiaTurma.addItem("Quinta");
        comboDiaTurma.addItem("Sexta");

        atvBotoes(false, true, false, false);

        habilitarAtributos(true, false, false, false, false, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(lbidTurma);
        Toolbar1.add(tfidTurma);
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
        centro.setLayout(new GridLayout(8, 2));

        centro.add(lbidTurma);
        centro.add(tfidTurma);
        centro.add(lbnomeTurma);
        centro.add(tfnomeTurma);
        centro.add(lbdiaTurma);
        centro.add(comboDiaTurma);
        centro.add(lbhorarioinicioTurma);
        centro.add(tfhorarioinicioTurma);
        centro.add(lbhorariofimTurma);
        centro.add(tfhorariofimTurma);
        centro.add(lbnivelIdNivel);
        centro.add(tfnivelIdNivel);
        centro.add(lbprofessorIdProfessor);
        centro.add(tfprofessorIdProfessor);

        aviso.add(labelAviso);
        aviso.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);
        tfidTurma.requestFocus();
        tfidTurma.selectAll();
        tfidTurma.setBackground(Color.GREEN);
        labelAviso.setText("Digite uma placa e clic [Pesquisar]");
        //setLocationRelativeTo(null); // posiciona no centro da tela principal
        //setLocation(300, 200);
        //setVisible(true);//faz a janela ficar visível

        tfidTurma.addActionListener(new ActionListener() {//pesquisa incremental no id
            @Override
            public void actionPerformed(ActionEvent ae) {

                List<String> listaAuxiliar = daoTurma.listInOrderNomeStrings("id");

                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, 400, 400).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfidTurma.setText(aux[0]);
                        btnRetrieve.doClick();//aciona o botao buscar
                    } else {
                        tfidTurma.requestFocus();
                        tfidTurma.selectAll();
                    }
                }
            }
        });

        //////////////////////////////////////////////////////////////////////////////
        tfnivelIdNivel.addActionListener(new ActionListener() { //pesquisa nos campos
            @Override
            public void actionPerformed(ActionEvent ae) {
                List<String> listaAuxiliar = daoNivel.listInOrderNomeIdiomaStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, 750, 200).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split(";");
                        tfnivelIdNivel.setText(aux[0]);
                    } else {
                        tfnivelIdNivel.requestFocus();
                        tfnivelIdNivel.selectAll();
                    }
                }

            }
        });

        tfprofessorIdProfessor.addActionListener(new ActionListener() { //pesquisa nos campos
            @Override
            public void actionPerformed(ActionEvent ae) {
                List<String> listaAuxiliar = daoProfessor.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, 750, 200).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split(";");
                        tfprofessorIdProfessor.setText(aux[0]);
                    } else {
                        tfprofessorIdProfessor.requestFocus();
                        tfprofessorIdProfessor.selectAll();
                    }
                }

            }
        });

        tfhorarioinicioTurma.addActionListener(new ActionListener() {//pesquisa incremental
            @Override
            public void actionPerformed(ActionEvent ae) {

                List<String> listaAuxiliar = daoHorario.listInOrderHorarioStrings("id");

                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, 400, 400).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        //String[] aux = selectedItem.split("-");
                        tfhorarioinicioTurma.setText(selectedItem);
                    } else {
                        tfhorarioinicioTurma.requestFocus();
                        tfhorarioinicioTurma.selectAll();
                    }
                }
            }
        });

        tfhorariofimTurma.addActionListener(new ActionListener() {//pesquisa incremental
            @Override
            public void actionPerformed(ActionEvent ae) {

                List<String> listaAuxiliar = daoHorario.listInOrderHorarioStrings("id");

                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, 400, 400).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        //String[] aux = selectedItem.split("-");
                        tfhorariofimTurma.setText(selectedItem);
                    } else {
                        tfhorariofimTurma.requestFocus();
                        tfhorariofimTurma.selectAll();
                    }
                }
            }
        });

        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                turma = new Turma();
                tfidTurma.setText(tfidTurma.getText().trim());//caso tenham sido digitados espaços

                if (tfidTurma.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    tfidTurma.requestFocus();
                    tfidTurma.selectAll();
                } else {
                    turma.setIdTurma(Integer.valueOf(tfidTurma.getText()));
                    turma = daoTurma.obter(turma.getIdTurma());
                    if (turma != null) { //se encontrou na lista
                        tfidTurma.setText(String.valueOf(turma.getIdTurma()));
                        tfnomeTurma.setText(turma.getNomeTurma());
                        comboDiaTurma.setSelectedItem(turma.getDiaTurma());
                        tfhorarioinicioTurma.setText(sdf.format(turma.getHorarioinicioTurma()));
                        tfhorariofimTurma.setText(sdf.format(turma.getHorariofimTurma()));

//                        Idioma idioma = daoIdioma.obter(Integer.valueOf(nivel.getIdiomaIdIdioma().getIdIdioma()));
//                        tfidiomaIdIdioma.setText(String.valueOf(idioma.getIdIdioma()+
//                                "-" + idioma.getNomeIdioma()));
                        Nivel nivel = daoNivel.obter(Integer.valueOf(turma.getNivelIdNivel().getIdNivel()));
                        tfnivelIdNivel.setText(String.valueOf(nivel.getIdNivel()
                                + "-" + nivel.getNomeNivel()));
                        Professor professor = daoProfessor.obter(Integer.valueOf(turma.getProfessorIdProfessor().getIdProfessor()));
                        tfprofessorIdProfessor.setText(String.valueOf(professor.getIdProfessor()
                                + "-" + professor.getNomeProfessor()));

                        habilitarAtributos(true, false, false, false, false, false, false);
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
                        true
                );
                tfnomeTurma.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });
        btnSave.addActionListener(new ActionListener() {//não sei
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (acao.equals("insert")) {
                    turma = new Turma();

                    turma.setIdTurma(Integer.valueOf(tfidTurma.getText()));
                    turma.setNomeTurma(tfnomeTurma.getText());
                    turma.setDiaTurma(String.valueOf(comboDiaTurma.getSelectedItem()));

                    String horarioiniciotf = tfhorarioinicioTurma.getText();
                    String[] horai = horarioiniciotf.split("-");
                    Date hhorasi;
                    try {
                        hhorasi = sdf.parse(horai[2]);
                        turma.setHorarioinicioTurma(hhorasi);
                    } catch (ParseException ex) {
                        Logger.getLogger(GUITurma.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    String horariofimtf = tfhorariofimTurma.getText();
                    String[] horaf = horariofimtf.split("-");
                    Date hhorasf;
                    try {
                        hhorasf = sdf.parse(horaf[2]);
                        turma.setHorariofimTurma(hhorasf);
                    } catch (ParseException ex) {
                        Logger.getLogger(GUITurma.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    int idTurma = Integer.valueOf(tfidTurma.getText());
                    int idHorasInicio = Integer.valueOf(horai[0]);
                    int idHorasFim = Integer.valueOf(horaf[0]);

                    System.out.println(idHorasInicio + "," + idHorasFim);
                    System.out.println("Turma:" + idTurma);

                    String[] auxNivel = tfnivelIdNivel.getText().split("-");
                    Nivel nivel = daoNivel.obter(Integer.valueOf(auxNivel[0]));
                    turma.setNivelIdNivel(nivel);

                    String[] auxProfessor = tfprofessorIdProfessor.getText().split("-");
                    Professor professor = daoProfessor.obter(Integer.valueOf(auxProfessor[0]));
                    turma.setProfessorIdProfessor(professor);

                    String professortf = tfprofessorIdProfessor.getText();
                    String[] prof = professortf.split("-");
                    int idProfessor = Integer.parseInt(prof[0]);
                    Entidades.Professor_Has_Horario professor_Has_HorarioInicio = new Entidades.Professor_Has_Horario(idProfessor, idHorasInicio);

                    daoTurma.inserir(turma);
                    Entidades.Turma_Has_Horario turma_Has_HorarioInicio = new Entidades.Turma_Has_Horario(idTurma, idHorasInicio);
                    Entidades.Turma_Has_Horario turma_Has_HorarioFim = new Entidades.Turma_Has_Horario(idTurma, idHorasFim);

                    habilitarAtributos(true, false, false, false, false, false, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    //Antes de tudo
                    //vou excluir as ligações N:M de Professor e Turma com Horário
                    //:)

                    //recebendo as antigas informações
                    turma = new Turma();
                    tfidTurma.setText(tfidTurma.getText().trim());
                    turma.setIdTurma(Integer.valueOf(tfidTurma.getText()));
                    turma = daoTurma.obter(turma.getIdTurma());
                    int idTurmaAntigo = turma.getIdTurma();
                    Professor professorantigo = daoProfessor.obter(turma.getProfessorIdProfessor().getIdProfessor());
                    int idProfessorAntigo = professorantigo.getIdProfessor();
                    String horarioInicioAntigo = String.valueOf(sdf.format(turma.getHorarioinicioTurma()));
                    String horarioFimAntigo = String.valueOf(sdf.format(turma.getHorariofimTurma()));

                    //aqui eu to tentando achar a droga do ID do horario
                    String x;
                    int idInicioAntigo = 0;
                    int idFimAntigo = 0;
                    List<String> lista = daoHorario.listInOrderHorarioStrings("id");
                    for (int i = 0; i < lista.size(); i++) {
                        x = lista.get(i);
                        String[] infos = x.split("-");
                        if (infos[2].equals(horarioInicioAntigo)) {
                            idInicioAntigo = Integer.valueOf(infos[0]);
                        }
                        if (infos[2].equals(horarioFimAntigo)) {
                            idFimAntigo = Integer.valueOf(infos[0]);
                        }
                    }

                    System.out.println("prof " + idProfessorAntigo);
                    System.out.println("inicio " + idInicioAntigo);
                    System.out.println("fim " + idFimAntigo);
                    
                    //excluindo...
                    horario = daoHorario.obter(idInicioAntigo);
                    List<Professor> lp1 = horario.getProfessorList();
                    lp1.remove(daoProfessor.obter(idProfessorAntigo));
                    daoHorario.atualizar(horario);

                    horario = daoHorario.obter(idInicioAntigo);
                    List<Turma> lp2 = horario.getTurmaList();
                    lp2.remove(daoTurma.obter(idTurmaAntigo));
                    daoHorario.atualizar(horario);

                    horario = daoHorario.obter(idFimAntigo);
                    List<Turma> lp3 = horario.getTurmaList();
                    lp3.remove(daoTurma.obter(idTurmaAntigo));
                    daoHorario.atualizar(horario);

//                    idioma = daoIdioma.obter(Integer.valueOf(idm[0]));
//                        List<Professor> lp = idioma.getProfessorList();
//                        lp.remove(daoProfessor.obter(Integer.valueOf(prof[0])));
//                        daoIdioma.atualizar(idioma);
                    turma.setIdTurma(Integer.valueOf(tfidTurma.getText()));
                    turma.setNomeTurma(tfnomeTurma.getText());
                    turma.setDiaTurma(String.valueOf(comboDiaTurma.getSelectedItem()));

                    int idHorasInicio = 0;
                    int idHorasFim = 0;

                    String horarioiniciotf = tfhorarioinicioTurma.getText();
                    if (horarioiniciotf.contains("-")) {
                        String[] horai = horarioiniciotf.split("-");
                        idHorasInicio = Integer.valueOf(horai[0]);
                        Date hhorasi;
                        try {
                            hhorasi = sdf.parse(horai[2]);
                            turma.setHorarioinicioTurma(hhorasi);
                        } catch (ParseException ex) {
                            Logger.getLogger(GUITurma.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        for (int i = 0; i < lista.size(); i++) {
                            x = lista.get(i);
                            String[] infos = x.split("-");
                            if (infos[2].equals(tfhorarioinicioTurma.getText())) {
                                idHorasInicio = Integer.valueOf(infos[0]);
                            }
                        }
                    }

                    String horariofimtf = tfhorariofimTurma.getText();
                    if (horariofimtf.contains("-")) {
                        String[] horaf = horariofimtf.split("-");
                        idHorasFim = Integer.valueOf(horaf[0]);
                        Date hhorasf;
                        try {
                            hhorasf = sdf.parse(horaf[2]);
                            turma.setHorariofimTurma(hhorasf);
                        } catch (ParseException ex) {
                            Logger.getLogger(GUITurma.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else {
                        for (int i = 0; i < lista.size(); i++) {
                            x = lista.get(i);
                            String[] infos = x.split("-");
                            if (infos[2].equals(tfhorariofimTurma.getText())) {
                                idHorasFim = Integer.valueOf(infos[0]);
                            }
                        }
                    }

                    String[] auxNivel = tfnivelIdNivel.getText().split("-");
                    Nivel nivel = daoNivel.obter(Integer.valueOf(auxNivel[0]));
                    turma.setNivelIdNivel(nivel);

                    String[] auxProfessor = tfprofessorIdProfessor.getText().split("-");
                    Professor professor = daoProfessor.obter(Integer.valueOf(auxProfessor[0]));
                    turma.setProfessorIdProfessor(professor);

                    int idTurma = Integer.valueOf(tfidTurma.getText());
                    System.out.println("Atual: "+idHorasInicio +" "+ idHorasFim);
//                    int idHorasInicio = Integer.valueOf(horai[0]);
//                    int idHorasFim = Integer.valueOf(horaf[0]);

                    String professortf = tfprofessorIdProfessor.getText();
                    String[] prof = professortf.split("-");
                    int idProfessor = Integer.parseInt(prof[0]);

                    Entidades.Professor_Has_Horario professor_Has_HorarioInicio = new Entidades.Professor_Has_Horario(idProfessor, idHorasInicio);

                    Entidades.Turma_Has_Horario turma_Has_HorarioInicio = new Entidades.Turma_Has_Horario(idTurma, idHorasInicio);
                    Entidades.Turma_Has_Horario turma_Has_HorarioFim = new Entidades.Turma_Has_Horario(idTurma, idHorasFim);

                    daoTurma.atualizar(turma);
                    habilitarAtributos(true, false, false, false, false, false, false);
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

                habilitarAtributos(true, false, false, false, false, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                GUIListagemTurma guiListagem = new GUIListagemTurma(daoTurma.listInOrderNome());
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);

                habilitarAtributos(false, true, true, true, true, true, true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + turma.getIdTurma() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    
                    String horarioInicioAntigo = String.valueOf(sdf.format(turma.getHorarioinicioTurma()));
                    String x;
                    int idInicioAntigo = 0;
                    List<String> lista = daoHorario.listInOrderHorarioStrings("id");
                    for (int i = 0; i < lista.size(); i++) {
                        x = lista.get(i);
                        String[] infos = x.split("-");
                        if (infos[2].equals(horarioInicioAntigo)) {
                            idInicioAntigo = Integer.valueOf(infos[0]);
                        }
                    }
                    Professor professorantigo = daoProfessor.obter(turma.getProfessorIdProfessor().getIdProfessor());
                    int idProfessorAntigo = professorantigo.getIdProfessor();
                    
                    horario = daoHorario.obter(idInicioAntigo);
                    List<Professor> lp1 = horario.getProfessorList();
                    lp1.remove(daoProfessor.obter(idProfessorAntigo));
                    daoHorario.atualizar(horario);
                    
                    daoTurma.remover(turma);
                    zerarAtributos();
                    tfidTurma.requestFocus();
                    tfidTurma.selectAll();
                }
            }
        });
        tfidTurma.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                tfidTurma.setBackground(Color.GREEN);
                if (acao != "encontrou") {
                    labelAviso.setText("Digite uma Id e clic [Pesquisar]");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                tfidTurma.setBackground(Color.white);
            }
        });
        tfidTurma.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfidTurma.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfidTurma.setBackground(Color.white);
            }
        });
        tfnomeTurma.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfnomeTurma.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfnomeTurma.setBackground(Color.white);
            }
        });
        comboDiaTurma.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                comboDiaTurma.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                comboDiaTurma.setBackground(Color.white);
            }
        });
        tfhorarioinicioTurma.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfhorarioinicioTurma.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfhorarioinicioTurma.setBackground(Color.white);
            }
        });
        tfhorariofimTurma.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfhorariofimTurma.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfhorariofimTurma.setBackground(Color.white);
            }
        });
        tfnivelIdNivel.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfnivelIdNivel.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfnivelIdNivel.setBackground(Color.white);
            }
        });
        tfprofessorIdProfessor.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                tfprofessorIdProfessor.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                tfprofessorIdProfessor.setBackground(Color.white);
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
        new GUITurma();
    }
}
