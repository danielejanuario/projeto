/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isabela
 */
public class Mapping {
    
    
    private List<BusinessValue> _businessValue = new ArrayList<>();

    public void addBusinessValue(BusinessValue bv) {
        _businessValue.add(bv);
    }

    public BusinessValue getBusinessValue(int id) {
        return _businessValue.get(id);
    }

}
