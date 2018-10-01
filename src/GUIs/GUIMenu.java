package GUIs;

import Main.CaixaDeFerramentas;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class GUIMenu extends JDialog {

    Container cp;

    JLabel lbImagem = new JLabel();
    JPanel pnTotal = new JPanel();

    JMenuBar menubarMenu = new JMenuBar();
    JMenu menu = new JMenu("Cadastros");
    JMenuItem idioma = new JMenuItem("Idioma");
    JMenuItem nivel = new JMenuItem("Nível");
    JMenuItem aluno = new JMenuItem("Aluno");
    JMenuItem turma = new JMenuItem("Turma");
    JMenuItem professor = new JMenuItem("Professor");
    JMenuItem horario = new JMenuItem("Horário");
    
    JMenu menuNM = new JMenu("N:M");
    JMenuItem professorHasIdioma = new JMenuItem("Idioma-Professor");
    //JMenuItem professorHasHorario = new JMenuItem("Horario-Professor");
    JMenuItem matricula = new JMenuItem("Matrícula");

    CaixaDeFerramentas caixaDeFerramentas = new CaixaDeFerramentas();

    public GUIMenu() {
        setTitle("Escola Wonder");
        setSize(500, 320);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
                
        String caminho = "/icones/idiomascapa.png";
        Image imagemAux;
        ImageIcon icone = new ImageIcon(getClass().getResource(caminho));
        imagemAux = icone.getImage();
        icone.setImage(imagemAux.getScaledInstance(500, 270, Image.SCALE_FAST));

        setJMenuBar(menubarMenu);
        menubarMenu.add(menu);
        menu.add(idioma);
        menu.addSeparator();
        menu.add(nivel);
        menu.addSeparator();
        menu.add(aluno);
        menu.addSeparator();
        menu.add(turma);
        menu.addSeparator();
        menu.add(professor);
        menu.addSeparator();
        menu.add(horario);
        
        
        menubarMenu.add(menuNM);
        menuNM.add(professorHasIdioma);
        //menuNM.add(professorHasHorario);
        menuNM.add(matricula);
        
        lbImagem.setIcon(icone);
        pnTotal.add(lbImagem);

        cp.add(pnTotal);

        idioma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIIdioma guiIdioma = new GUIIdioma();
            }
        });

        nivel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUINivel guiNivel = new GUINivel();
            }
        });

        professor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIProfessor guiProfessor = new GUIProfessor();
            }
        });

        aluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIAluno guiAluno = new GUIAluno();
            }
        });
        
        horario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIHorario guiHorario = new GUIHorario();
            }
        });
        
        turma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUITurma guiTurma = new GUITurma();
            }
        });
        professorHasIdioma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIProfessorHasIdioma guiProfessorHasIdioma = new GUIProfessorHasIdioma();
            }
        });
//        professorHasHorario.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                GUIProfessorHasHorario gUIProfessorHasHorario = new GUIProfessorHasHorario();
//            }
//        });
        matricula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIMatricula guiMatricula = new GUIMatricula();
            }
        });

        setVisible(true);
        setModal(true);
        setLocationRelativeTo(null);
    }
}
