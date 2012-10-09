/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petri;

import java.util.ArrayList;

/**
 *
 * @author Wittmann
 */
public class Link {
    
    protected  ArrayList<Link> connections;
    
    public Link(){
        connections=new ArrayList();
    }
    
    public void addLink(Link el){
        connections.add(el);
    }
    
    public void removeLink(Link el){
        connections.remove(el);
    }
    
    public Link getRoad(Task t){
        return connections.get(0);
    }
    
    public double addTask(Task t){
        return 0;
    }
  
    
}
