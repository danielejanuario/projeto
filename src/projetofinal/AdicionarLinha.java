package projetofinal;

public class AdicionarLinha extends MontaGrafo {

    public AdicionarLinha(Mapping mp) {

        Object parent = AdicionarLinha.getGraph().getDefaultParent();

        for (int j = 0; j < mp.getSize(); j++) {

            for (int i = 0, prox = 1; i < mp.getBusinessValue(j).getSize(); i++, prox++) {
                if (prox < mp.getBusinessValue(j).getSize()) {
                    Object v1 = AdicionarLinha.getM().get(mp.getBusinessValue(j).getUserStory(i).getContent());
                    Object v2 = AdicionarLinha.getM().get(mp.getBusinessValue(j).getUserStory(prox).getContent());
                    AdicionarLinha.getGraph().insertEdge(parent, null, null, v1, v2);
                }
            }

        }

    }

}
