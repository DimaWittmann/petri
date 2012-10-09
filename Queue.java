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
public class Queue extends Link {
    
    private ArrayList<Task> queue;
    public String name;
    public boolean debug;
    
    public Queue(String name){
        queue= new ArrayList();
        this.name=name;
    }
    
    public double addTask(Task t){
        t.position=this;
        queue.add(t);
        if (debug){
            System.out.println(t.name+ " wait at "+name);
        }
       
        return 0;
    }
    
    public Link getRoad(Task t){
    
        Link r =((Immediate)connections.get(0)).getRoad(t);
        if(r!=null){
            queue.remove(t);
            return r;
        }else{
            return null;
        }
    }
    
    public void updateTasks(double time){
        for(int i=0;i<queue.size();i++){
            queue.get(i).time+=time;
        }
    }
    
}
