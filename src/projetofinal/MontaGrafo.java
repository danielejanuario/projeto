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

public class MontaGrafo extends JFrame {

    protected static mxGraph graph = new mxGraph();
    protected static HashMap m = new HashMap();
    private mxGraphComponent graphComponent;
    private JButton botaoAdd;
    private JButton botaoDel;
    private JButton botaoSair;
    private Object cell;

    public static HashMap getM() {
        return m;
    }

    public static mxGraph getGraph() {
        return graph;
    }

    public MontaGrafo() {
        initGUI();
    }

    private void initGUI() {

        setSize(1900, 1000);
        setLocationRelativeTo(null);
        setUndecorated(true);

        graphComponent = new mxGraphComponent(graph);
        graphComponent.setPreferredSize(new Dimension(1890, 930));
        getContentPane().add(graphComponent);

        setLayout(new FlowLayout(FlowLayout.RIGHT));

        botaoAdd = new JButton("Add");
        getContentPane().add(botaoAdd);
        botaoAdd.setPreferredSize(new Dimension(100, 40));
        botaoAdd.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                BusinessValue bv = new BusinessValue();
                BusinessValue bv2 = new BusinessValue();
                Mapping mp = new Mapping();

                bv.addUserStory("User Story teste 01", 1, 1);
                bv.addUserStory("User Story teste 02", 2, 2);
                mp.addBusinessValue(bv);

                bv2.addUserStory("User Story teste 03", 3, 3);
                bv2.addUserStory("User Story teste 04", 4, 4);
                mp.addBusinessValue(bv2);

                mostrarGrafo(mp);
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
        });

        botaoDel = new JButton("Delete");
        getContentPane().add(botaoDel);
        botaoDel.setPreferredSize(new Dimension(100, 40));
        botaoDel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                graph.getModel().remove(cell);

            }
        });

        botaoSair = new JButton("Close");
        getContentPane().add(botaoSair);
        botaoSair.setPreferredSize(new Dimension(100, 40));
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
