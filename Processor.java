/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petri;

/**
 *
 * @author Wittmann
 */
public class Processor extends Link {
    
    public Queue queue;
    private Delay delay;
    public String name;
    public double timeOfExec;
    public boolean debug;
    
    public Processor (Delay delay,String name){
        this.delay=delay;
        this.name=name;
        this.addLink(delay);
    }
    
    public double addTask(Task t){
        t.position=this;
        double time = delay.getDelay();
        timeOfExec+=time;
        t.time+=time;
        
        if (queue!=null){
            queue.updateTasks(time);
        }
        if (debug){
            System.out.println(name+"=>"+t.name +" :"+time);
        }
	return time;
    }
    
    public Link getRoad(Task t){
        return delay.getRoad(t);
    }
    
	
}