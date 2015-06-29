/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal;

import java.util.Map;

/**
 *
 * @author Isabela
 */
public class EstruturaDados {

    private final Serializa vtPersistencia;
    private final Map<String, UserStory> vertice;

    public EstruturaDados() {
        vtPersistencia = new Serializa();
        vertice = vtPersistencia.lerVertices();
    }

    private void salvar() {
        vtPersistencia.escreverVertices(vertice);
    }

    public void addDados(String content, int effort, int priority, int bv) {
        UserStory dados = new UserStory(content, effort, priority, bv);
        UserStory put = vertice.put(content, dados);
        this.salvar();
    }
}
