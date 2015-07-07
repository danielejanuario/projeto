package projetofinal;

public class Principal {
    
   private void insertUsInFile() {
       EstruturaDados us = new EstruturaDados();
        us.addDados("User Story 09", 3, 8, 4);
        us.addDados("User Story 01", 2, 1, 2);
        us.addDados("User Story 02", 4, 8, 3);
        us.addDados("User Story 12", 4, 10, 3);
        us.addDados("User Story 03", 6, 2, 1);
                us.addDados("User Story 13", 3, 2, 1);
                us.addDados("User Story 14", 1, 2, 1);
        us.addDados("User Story 04", 1, 3, 1);
        us.addDados("User Story 10", 3, 3, 1);
        us.addDados("User Story 05", 4, 5, 2);
        us.addDados("User Story 06", 5, 5, 4);
        us.addDados("User Story 07", 2, 7, 4);
        us.addDados("User Story 08", 3, 8, 4);
        us.addDados("User Story 11", 1, 8, 4);
    }

    public Principal() {

       insertUsInFile();

        MontaGrafo a = new MontaGrafo();
        a.setVisible(true);
    }

    public static void main(String args[]) {
        new Principal();
    }
}
