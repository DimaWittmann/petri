/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petri;

/**
 * Клас - оброблювача
 * @author Wittmann
 */
public class Processor extends Position{
    
    protected double timeOfWork;
    
    public Processor(String name,int capacity){
        super(name,capacity);   
    }
    
    
    @Override
    public  boolean stayForNight(Task t){
        if(canStayForNight()){
           tasks[curentNumberOfTasks]=t;
           curentNumberOfTasks++;
           t.setPosition(this);
           double time=((Delay)getNextElemnt()).getNextDelay();
           timeOfWork+=time;
           t.setTime(time);
           return true;
        }else{
           return false;
        }
    }
}
