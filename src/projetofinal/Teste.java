/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal;

/**
 *
 * @author Isabela
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        BusinessValue bv = new BusinessValue();
        BusinessValue bv2 = new BusinessValue();
        Mapping mp = new Mapping();

        bv.addUserStory("User Story teste 01", 1, 1);
        bv.addUserStory("User Story teste 02", 2, 2);
        mp.addBusinessValue(bv);

        bv2.addUserStory("User Story teste 03", 3, 3);
        bv2.addUserStory("User Story teste 04", 4, 4);
        mp.addBusinessValue(bv2);

        System.out.println(mp.getBusinessValue(1).getUserStory(0).getContent());
              
    }

}
