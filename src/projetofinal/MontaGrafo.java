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
import java.util.Collections;
import java.util.Comparator;
import static java.util.Comparator.comparing;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static javafx.scene.input.KeyCode.T;

public class MontaGrafo extends JFrame {

    protected static mxGraph graph = new mxGraph();
    protected static HashMap m = new HashMap();
    private mxGraphComponent graphComponent;
    private JButton botaoAdd;
    private JButton botaoDel;
    private JButton botaoSair;
    private Object cell;
    private Mapping mp = new Mapping();
    private boolean var = false;
    private boolean grafo = false;
    private final Map<String, UserStory> vertice;
    private final Serializa vtPersistencia;
    private Set<String> vertices;
    private HashSet<Integer> bvs;
    private EstruturaDados us;
    private HashSet<Integer> _effort;
    private HashSet<UserStory> _teste;

    public static HashMap getM() {
        return m;
    }

    public static mxGraph getGraph() {
        return graph;
    }

    private Set<String> todosVertices() {
        return vertice.keySet();
    }

    public MontaGrafo() {

        vtPersistencia = new Serializa();
        vertice = vtPersistencia.lerVertices();
        bvs = new HashSet<>();
        _effort = new HashSet<>();
        us = new EstruturaDados();
        insertUsInFile();
        createUS();
        initGUI();
    }

    private void insertUsInFile() {
        us.addDados("User Story 01", 2, 1, 2);
        us.addDados("User Story 02", 4, 8, 3);
        us.addDados("User Story 03", 6, 2, 1);
        us.addDados("User Story 04", 1, 3, 1);
        us.addDados("User Story 05", 4, 5, 2);
        us.addDados("User Story 06", 5, 5, 4);
        us.addDados("User Story 07", 2, 7, 4);
        us.addDados("User Story 08", 3, 8, 4);
    }

    private void getBusinessValuesFromFile(Set<String> vertices) {
        for (String n : vertices) {
            bvs.add(vertice.get(n).getBusinessValue());
        }
    }

    private void sortBusinessValueByPriority(BusinessValue bv) {
        Collections.sort(bv.getList(), comparing((userStory) -> userStory.getPriority(), Collections.reverseOrder()));
    }

    private void sortBusinessValueByEffort(BusinessValue bv) {
        Collections.sort(bv.getList(), comparing((userStory) -> userStory.getEffort()));
    }

    private void sortBusinessValue(BusinessValue bv) {
        sortBusinessValueByPriority(bv);
        sortBusinessValueByEffort(bv);
    }

    private void createUS() {
        if (!var) {
            vertices = this.todosVertices();
            getBusinessValuesFromFile(vertices);

            for (int i : bvs) {
                BusinessValue bv = new BusinessValue();
                for (String n : vertices) {
                    if (vertice.get(n).getBusinessValue() == i) {
                        String uS = vertice.get(n).getContent()
                                + "\n Effort: " + vertice.get(n).getEffort()
                                + "\n Priority: " + vertice.get(n).getPriority();
                        bv.addUserStory(uS, vertice.get(n).getEffort(), vertice.get(n).getPriority(), vertice.get(n).getBusinessValue());

                    }
                }

                sortBusinessValue(bv);

                mp.addBusinessValue(bv);
            }
        }
    }

    private void mostrarGrafo(Mapping mp) {
        int x = 0;
        int y = 10;

        for (int j = mp.getSize() - 1; j >= 0; j--) {
            x = 10;
            AdicionarGrafo addBV = new AdicionarGrafo();
            addBV.AdicionarBV("Business Value: " + mp.getBusinessValue(j).getUserStory(0).getBusinessValue(), x, y);
            y = y + 40;
            for (int i = 0; i < mp.getBusinessValue(j).getSize(); i++) {
                AdicionarGrafo add = new AdicionarGrafo();
                add.AddGrafo(mp.getBusinessValue(j).getUserStory(i).getContent(), x, y);
                x = x + 330;
            }
            y = y + 215;
        }
    }

    private void initGUI() {

        setSize(1200, 700);
        setLocationRelativeTo(null);
        setUndecorated(true);
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        graphComponent = new mxGraphComponent(graph);
        graphComponent.setPreferredSize(new Dimension(1190, 645));
        getContentPane().add(graphComponent);

        setLayout(new FlowLayout(FlowLayout.RIGHT));

        botaoAdd = new JButton("Add");
        getContentPane().add(botaoAdd);
        botaoAdd.setPreferredSize(new Dimension(100, 40));
        botaoAdd.setCursor(cursor);
        botaoAdd.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!grafo) {
                    //createUS();
                    mostrarGrafo(mp);
                    AdicionarLinha linha = new AdicionarLinha(mp);
                    var = true;
                }
                grafo = true;

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
