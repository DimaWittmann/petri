/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petri;

/**
 *
 * @author Wittmann
 */
public class Task {
        
    public Link position; 
    public String name;
    public double time;
    
    public Task(String name){
        this.name=name;
        time=0;
    }
    
    public double goFuther(){
        Link r= position.getRoad(this);
        if (r!=null){
            return r.addTask(this);
        }
	return 0;
    }
    
}
