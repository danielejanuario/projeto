/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetofinal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Isabela
 */
public class Serializa {
    private final File arquivoVertices;
   // private final String diretorio = "C:\\Projeto_v09";
    final private String nomeArquivo = "Vertices.dat";

    public Serializa() {
       /* if (!new File(diretorio).exists()) {
            (new File(diretorio)).mkdir();
        }
              */ 
        arquivoVertices = new File(nomeArquivo);
    }

    public Map lerVertices() {
        Map vertices = null;
        if (arquivoVertices.exists()) {
            try {
                vertices = (Map) new ObjectInputStream(new FileInputStream(arquivoVertices)).readObject();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Serializa.class.getName()).log(Level.SEVERE, "Erro ao ler vertices", ex);
            }
        } else {
            vertices = new TreeMap<>();
        }
        return vertices;
    }

    public void escreverVertices(Map m) {
        try {
            new ObjectOutputStream(new FileOutputStream(arquivoVertices)).writeObject(m);
        } catch (IOException ex) {
            Logger.getLogger(Serializa.class.getName()).log(Level.SEVERE, "Erro ao escrever vertices", ex);
        }
    }
}
