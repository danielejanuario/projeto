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
        setSize(900, 700);
        setLocationRelativeTo(null);
        setUndecorated(true);

        graphComponent = new mxGraphComponent(graph);
        graphComponent.setPreferredSize(new Dimension(895, 650));
        getContentPane().add(graphComponent);

        setLayout(new FlowLayout(FlowLayout.RIGHT));

        botaoAdd = new JButton("Adicionar");
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

                AdicionarGrafo add = new AdicionarGrafo(mp.getBusinessValue(1).getUserStory(0).getContent());

            }
        });

        botaoDel = new JButton("Deletar");
        getContentPane().add(botaoDel);
        botaoDel.setPreferredSize(new Dimension(100, 40));
        botaoDel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                graph.getModel().remove(cell);

            }
        });

        botaoSair = new JButton("Sair");
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
