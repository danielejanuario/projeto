/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal;

import java.io.Serializable;

/**
 *
 * @author Isabela
 */
public final class UserStory implements Serializable{

    private String content;
    private int effort;
    private int priority;

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the effort
     */
    public int getEffort() {
        return effort;
    }

    /**
     * @param effort the effort to set
     */
    public void setEffort(int effort) {
        this.effort = effort;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public UserStory(String content, int effort, int priority){
        setContent(content);
        setEffort(effort);
        setPriority(priority);
    }

    

}
