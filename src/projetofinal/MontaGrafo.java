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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MontaGrafo extends JFrame {

    protected static mxGraph graph = new mxGraph();
    protected static HashMap m = new HashMap();
    private mxGraphComponent graphComponent;
    private JButton botaoAdd;
    private JButton botaoDel;
    private JButton botaoSair;
    private Object cell;
    private Object cell2;
    private Mapping mp = new Mapping();
    private boolean var = false;
    private boolean grafo = false;
    private final Map<String, UserStory> vertice;
    private final Serializa vtPersistencia;
    private Set<String> vertices;
    private HashSet<Integer> bvs;
    private EstruturaDados us;

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
        bvs = new HashSet<>();

        vertice = vtPersistencia.lerVertices();
        createUS();
        initGUI();
    }

    private void getBusinessValuesFromFile(Set<String> vertices) {
        for (String n : vertices) {
            bvs.add(vertice.get(n).getBusinessValue());
        }
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

    private void sortBusinessValue(BusinessValue bv) {
        for (int k = bv.getSize() - 1; k >= 1; k--) {
            for (int j = 0; j < k; j++) {
                if (bv.getUserStory(j).getEffort() > bv.getUserStory(j + 1).getEffort()) {
                    sortEffort(bv, j);
                }

                //for (int b = bv.getSize() - 1; b >= 1; b--) {
                for (int a = 0; a < k; a++) {
                    if (bv.getUserStory(a).getPriority() < bv.getUserStory(a + 1).getPriority()) {
                        sortPriority(bv, a);
                    }

                    //}
                }
            }
        }
    }

    private void sortEffort(BusinessValue bv, int j) {
        int auxPriority;
        int auxEffort;
        String auxContent;
        auxPriority = bv.getUserStory(j).getPriority();
        auxEffort = bv.getUserStory(j).getEffort();
        auxContent = bv.getUserStory(j).getContent();
        bv.getUserStory(j).setPriority(bv.getUserStory(j + 1).getPriority());
        bv.getUserStory(j).setContent(bv.getUserStory(j + 1).getContent());
        bv.getUserStory(j).setEffort(bv.getUserStory(j + 1).getEffort());
        bv.getUserStory(j + 1).setPriority(auxPriority);
        bv.getUserStory(j + 1).setEffort(auxEffort);
        bv.getUserStory(j + 1).setContent(auxContent);
    }

    private void sortPriority(BusinessValue bv, int b) {
        int auxPriority;
        int auxEffort;
        String auxContent;

        auxPriority = bv.getUserStory(b).getPriority();
        auxEffort = bv.getUserStory(b).getEffort();
        auxContent = bv.getUserStory(b).getContent();

        bv.getUserStory(b).setPriority(bv.getUserStory(b + 1).getPriority());
        bv.getUserStory(b).setContent(bv.getUserStory(b + 1).getContent());
        bv.getUserStory(b).setEffort(bv.getUserStory(b + 1).getEffort());

        bv.getUserStory(b + 1).setPriority(auxPriority);
        bv.getUserStory(b + 1).setEffort(auxEffort);
        bv.getUserStory(b + 1).setContent(auxContent);

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
                x = x + 230;
            }
            y = y + 115;
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

        botaoDel = new JButton("Edit");
        getContentPane().add(botaoDel);
        botaoDel.setPreferredSize(new Dimension(100, 40));
        botaoDel.setCursor(cursor);
        botaoDel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                Object value = graph.getModel().getValue(cell);

                for (int j = mp.getSize() - 1; j >= 0; j--) {
                    for (int i = 0; i < mp.getBusinessValue(j).getSize(); i++) {
                        if (mp.getBusinessValue(j).getUserStory(i).getContent() == value) {
                            JFrame frame = new JFrame("Edit User Story");
                            frame.setSize(600, 300);
                            frame.setVisible(true);
                            frame.setLocationRelativeTo(null);
                            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            JPanel panel = new JPanel();

                            JLabel content = new JLabel("Content: ");

                            JTextArea insertContent = new JTextArea();
                            insertContent.setAutoscrolls(true);
                            insertContent.setPreferredSize(new Dimension(300, 200));
                            insertContent.setText(mp.getBusinessValue(j).getUserStory(i).getContent());
                            insertContent.setEditable(true);

                            panel.add(content);
                            panel.add(insertContent);

                            frame.add(panel);

                        }
                    }

                }

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

            @Override
            public void mouseReleased(MouseEvent e) {
                cell = graphComponent.getCellAt(e.getX(), e.getY());
            }

        });
    }

}
