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
    
    public Queue(String name){
        queue= new ArrayList();
        this.name=name;
    }
    
    public void addTask(Task t){
        t.position=this;
        queue.add(t);
        System.out.println(t.name+ " wait at "+name);
    }
    
    public Link getRoad(){
    
        Link r =((Immediate)connections.get(0)).getRoad();
        if(r!=null){
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
