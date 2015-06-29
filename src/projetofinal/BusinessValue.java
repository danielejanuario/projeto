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
 *
 *
 * Classe responsavel pela associação de cada user story com o seu devido
 * business value
 *
 */
public class BusinessValue {

    private final List<UserStory> _userStories = new ArrayList<>();

    public void addUserStory(String content, int effort, int priority, int bv) {
        UserStory us = new UserStory(content, effort, priority, bv);
        _userStories.add(us);
    }

    public UserStory getUserStory(int id) {
        return _userStories.get(id);
    }
    
    public List<UserStory> getList(){
        return _userStories.subList(0, _userStories.size());
    }
   
    public int getSize(){
        return _userStories.size();
    }

}
