/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petri;

/**
 *
 * @author Wittmann
 */
public class Switch {
    private boolean ready;
    public String name;
    public boolean debug;
    
    
    public Switch(String name){
        ready=true;
        this.name=name;
    }
    
    public boolean getState(){
        return ready;
    }
    
    public void lock(){
        ready=false;
        if(debug){
            System.out.println(name+" locked");
        }
    }
    
    public void open(){
        ready = true;
        if(debug){
            System.out.println(name+" open");
        }
    }
            
            
}
