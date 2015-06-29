package projetofinal;

public class AdicionarGrafo extends MontaGrafo {

    public void AddGrafo(String nome, int x, int y) {
        AdicionarGrafo.getGraph().getModel().beginUpdate();
        Object parent = AdicionarGrafo.getGraph().getDefaultParent();
       
        graph.setCellsEditable(false);
       

        Object v1 = AdicionarGrafo.getGraph().insertVertex(parent, null, nome, x, y, 300, 200, "fontFamily=Cordia New;fontSize=15;fontColor=black;");
        AdicionarGrafo.getM().put(nome, v1);
        AdicionarGrafo.getGraph().getModel().endUpdate();
    }

    public void AdicionarBV(String nome, int x, int y) {
        AdicionarGrafo.getGraph().getModel().beginUpdate();
        Object parent = AdicionarGrafo.getGraph().getDefaultParent();
        Object v1 = AdicionarGrafo.getGraph().insertVertex(parent, null, nome, x, y, 100, 25, "fontFamily=Cordia New;fontSize=17;fontColor=black;fillColor=white;strokeColor=#E8E8E8");
        AdicionarGrafo.getM().put(nome, v1);
        AdicionarGrafo.getGraph().getModel().endUpdate();
    }

}
