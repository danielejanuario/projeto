package projetofinal;

public class AdicionarGrafo extends MontaGrafo {

    public AdicionarGrafo(String nome, int x, int y) {
        AdicionarGrafo.getGraph().getModel().beginUpdate();
        Object parent = AdicionarGrafo.getGraph().getDefaultParent();
        Object v1 = AdicionarGrafo.getGraph().insertVertex(parent, null, nome, x, y, 400, 200);
        AdicionarGrafo.getM().put(nome, v1);
        AdicionarGrafo.getGraph().getModel().endUpdate();
    }

}
