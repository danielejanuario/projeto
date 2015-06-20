package projetofinal;

import java.util.HashMap;

import javax.swing.JOptionPane;

import com.mxgraph.view.mxGraph;

public class AdicionarLinha extends MontaGrafo {

    public AdicionarLinha(Mapping mp) {

        Object parent = this.getGraph().getDefaultParent();

        for (int j = 0; j < mp.getSize(); j++) {

            for (int i = 0, prox = 1; i < mp.getBusinessValue(j).getSize(); i++, prox++) {
                if (prox < mp.getBusinessValue(j).getSize()) {
                    Object v1 = this.getM().get(mp.getBusinessValue(j).getUserStory(i).getContent());
                    Object v2 = this.getM().get(mp.getBusinessValue(j).getUserStory(prox).getContent());
                    this.getGraph().insertEdge(parent, null, null, v1, v2);
                }
            }

        }

    }

}
