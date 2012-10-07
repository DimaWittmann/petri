/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petri1;

/**
 *
 * @author Wittmann
 */
public class Processor extends Link {
    
    private Delay delay;
    public String name;
    private double timeOfExec;
    
    public Processor (Delay delay,String name){
        this.delay=delay;
        this.name=name;
        this.addLink(delay);
    }
    
    public void addTask(Task t){
        t.position=this;
        double time = delay.getDelay();
        timeOfExec+=time;
        t.time+=time;
        
        System.out.println(name+"=>"+t.name +" :"+time);
    }
    
    public Link getRoad(){
        return delay.getRoad();
    }
    
}
