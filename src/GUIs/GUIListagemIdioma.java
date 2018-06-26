package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class GUIListagemIdioma extends JDialog {

    JPanel painelTa = new JPanel();
    static DefaultTableModel modelo = new DefaultTableModel(new String[]{"Id", "Nome"}, 0);
    static JTable tabela = new JTable(modelo);
    JScrollPane scroll = new JScrollPane(tabela);
    List<String> lista = new ArrayList<>();

    public GUIListagemIdioma(List<String> texto, Container cp2) {
        setTitle("Listagem de Idioma");
        setSize(840, 315);//tamanho da janela
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//libera ao sair (tira da memória a classe
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        setModal(true);
        lista = texto;
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        painelTa.add(scroll);
        atualizaTabela();

        cp.add(scroll, BorderLayout.CENTER);

        setLocationRelativeTo(cp2);
        setVisible(true);
    }

    public void atualizaTabela() {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"Id", "Nome"}, 0);
        int aux = 0;

        for (int i = 0; i < lista.size(); i++) {
            String[] linha = lista.get(i).toString().split("-");
            modelo.addRow(linha);
            aux++;
        }
//        while (aux < 10) {
//            aux++;
//            modelo.addRow(new Object[]{"", "", "", "", ""});
//        }

        tabela.setModel(modelo);
    }
}
