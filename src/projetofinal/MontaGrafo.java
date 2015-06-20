package projetofinal;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.Cursor;

public class MontaGrafo extends JFrame {

    protected static mxGraph graph = new mxGraph();
    protected static HashMap m = new HashMap();
    private mxGraphComponent graphComponent;
    private JButton botaoAdd;
    private JButton botaoDel;
    private JButton botaoSair;
    private Object cell;
    private BusinessValue bv = new BusinessValue();
    private BusinessValue bv2 = new BusinessValue();
    private BusinessValue bv3 = new BusinessValue();
    private Mapping mp = new Mapping();
   private boolean var = false;

    public static HashMap getM() {
        return m;
    }

    public static mxGraph getGraph() {
        return graph;
    }

    public MontaGrafo() {
        initGUI();
    }

    private void createUS() {
        if (!var) {
            bv.addUserStory("As a depositor I want to Deposit and maintain datasets\nthrough a simple web interface so that I don’t need to\ninstall and learn new software to deposit", 1, 1);
            bv.addUserStory("As a depositor I want to Have a user interface that is \nfamiliar to me so that I feel like all the University \nsystems are joined up", 2, 2);
            mp.addBusinessValue(bv);

            bv2.addUserStory("As a depositor I want to Deposit and maintain datasets\n through Pure so that I have a single one-stop shop for \nmanaging my research outputs", 3, 3);
            bv2.addUserStory("As a depositor I want to Deposit and maintain datasets \nthrough Virtual Research Environments and other workflow \ntools so that I can continue to work with tools with which I’m familiar", 4, 4);
            bv2.addUserStory("As a depositor I want to Deposit the files that I have \nso that I don’t have to spend a lot of time finding the right \nversion and converting to the right format", 5, 5);
            bv2.addUserStory("As a depositor I want to Place data under an embargo so \nthat My right of first-use is protected. I can fulfil my \nconfidentiality responsibilities", 6, 6);
            mp.addBusinessValue(bv2);

            bv3.addUserStory("As a depositor I want to Apply licenses to datasets so \nthat My IP rights are protected appropriately", 7, 7);
            bv3.addUserStory("As a depositor I want to Allow my collaborators privileged\n access to datasets so that We continue to have a \nproductive relationship", 8, 8);
            bv3.addUserStory("As a depositor I want to Deposit arbitrarily large files \nso that I am not limited in what files I can and cannot deposit", 9, 9);
            mp.addBusinessValue(bv3);
        }
    }

    private void mostrarGrafo(Mapping mp) {
        int x = 0;
        int y = 10;
        for (int j = 0; j < mp.getSize(); j++) {
            x = 10;
            for (int i = 0; i < mp.getBusinessValue(j).getSize(); i++) {
                AdicionarGrafo add = new AdicionarGrafo(mp.getBusinessValue(j).getUserStory(i).getContent(), x, y);
                x = x + 430;
            }
            y = y + 230;
        }
    }

    private void initGUI() {

        setSize(1900, 1000);
        setLocationRelativeTo(null);
        setUndecorated(true);
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        graphComponent = new mxGraphComponent(graph);
        graphComponent.setPreferredSize(new Dimension(1890, 930));
        getContentPane().add(graphComponent);

        setLayout(new FlowLayout(FlowLayout.RIGHT));

        botaoAdd = new JButton("Add");
        getContentPane().add(botaoAdd);
        botaoAdd.setPreferredSize(new Dimension(100, 40));
        botaoAdd.setCursor(cursor);
        botaoAdd.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                createUS();
                mostrarGrafo(mp);
                AdicionarLinha linha = new AdicionarLinha(mp);
                var = true;
            }

        });

        botaoDel = new JButton("Delete");
        getContentPane().add(botaoDel);
        botaoDel.setPreferredSize(new Dimension(100, 40));
        botaoDel.setCursor(cursor);
        botaoDel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                graph.getModel().remove(cell);
            }
        });

        botaoSair = new JButton("Close");
        getContentPane().add(botaoSair);
        botaoSair.setPreferredSize(new Dimension(100, 40));
        botaoSair.setCursor(cursor);
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);

            }
        });

        graphComponent.getGraphControl().addMouseListener(new MouseAdapter() {

            public void mouseReleased(MouseEvent e) {
                cell = graphComponent.getCellAt(e.getX(), e.getY());
            }
        });
    }

}
