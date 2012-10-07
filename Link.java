/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petri1;

import java.util.ArrayList;

/**
 *
 * @author Wittmann
 */
public class Link {
    
    protected  ArrayList connections;
    
    public Link(){
        connections=new ArrayList();
    }
    
    public void addLink(Link el){
        connections.add(el);
    }
    
    public void removeLink(Link el){
        connections.remove(el);
    }
    
    public Link getRoad(){
        return (Link)connections.get(0);
    }
    
    public void addTask(Task t){
        
    }
  
    
}
