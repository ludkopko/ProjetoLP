package GUIs;

import Main.CaixaDeFerramentas;
import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class GUIMenu extends JDialog {

    Container cp;

    JPanel pnTotal = new JPanel();

    JMenuBar menubarMenu = new JMenuBar();
    JMenu menu = new JMenu("Cadastros");
    JMenuItem idioma = new JMenuItem("Idioma");
    JMenuItem nivel = new JMenuItem("Nível");
    JMenuItem aluno = new JMenuItem("Aluno");
    JMenuItem turma = new JMenuItem("Turma");
    JMenuItem professor = new JMenuItem("Professor");
    JMenuItem horario = new JMenuItem("Horário");

    CaixaDeFerramentas caixaDeFerramentas = new CaixaDeFerramentas();

    public GUIMenu() {
        setTitle("Escola Wonder");
        setSize(500, 320);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();

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

        setVisible(true);
        setModal(true);
        setLocationRelativeTo(null);
    }
}
