package projetofinal;

public class AdicionarGrafo extends Acoes {

    public AdicionarGrafo(String nome) {
        AdicionarGrafo.getGraph().getModel().beginUpdate();
        Object parent = AdicionarGrafo.getGraph().getDefaultParent();
        Object v1 = AdicionarGrafo.getGraph().insertVertex(parent, null, nome, 330, 30, 100, 50);
        AdicionarGrafo.getM().put(nome, v1);
        AdicionarGrafo.getGraph().getModel().endUpdate();
    }

}
