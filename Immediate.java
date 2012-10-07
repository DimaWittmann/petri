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
public class Immediate extends Link{
    
    private Switch ready;
    
      
    public Immediate(Switch ready){
       this.ready=ready;
    }
    
    public Link  getRoad(){
        if(ready.getState()){
            ready.lock();
            return (Link)connections.get(0);
        }else{
            return null;
        }
    }
    
}
