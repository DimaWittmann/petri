
package petri;

import java.util.ArrayList;

/**
 * Задача - що виконуватиметься
 * в мережі Петрі
 * @version alfa
 * @author Wittmann
 */
public class Task {
    
    /**
     * Місце знаходження
     * задачі
     */
    protected Element position;
    
    /**
     * Два поля -точкі розміщення
     * і час цього переміщення
     */
    protected ArrayList<Element> transitions;
    protected ArrayList<Double> timeofTrans;
    
    /**
     * Кількість створених елементів
     */
    protected static int count;
    
    protected int id;
    protected double lifeTime;
    
    
    public Task(){
        transitions=new ArrayList<Element>();
        timeofTrans=new ArrayList<Double>();
        id=count;
        count++;
        lifeTime=0;
    }
    
    /**
     * Воставити фішку на нову позиці
     * @param p позиція
     */
    public void setPosition(Element p){
        transitions.add(p);
        position=p;
    }
    
    /**
     * Встановити затримку
     * @param t час
     */
    public void setTime(double t){
        timeofTrans.add(t);
        lifeTime+=t;
    }
    public double getLifeTime(){
        return lifeTime;
    }
    
    
    public ArrayList<Element> getTrans(){
        return transitions;
    }
    
    public ArrayList<Double> getTimeOfTrans(){
        return timeofTrans;
    }
    
    /**
     * Перехід на наступний елемент мережі
     * @return 
     */
    public boolean goToNext(Element p){
        position = p;
        //TODO зробити параметри переходу
        return false;
    }
    
    
    public boolean goFuther(){
        Element p=position.getNextElemnt();
        if (p.isPassable()){
            
            return p.pass(this);
        }
        
        if(p.canStayForNight()){
            position.giveTask();
            return p.stayForNight(this);
        }
        
        return false;
        
    }
    
    public String toString(){
        String res=new String();
        for (int i=0;i<transitions.size();i++){
            res=res.concat(transitions.get(i)+" "+timeofTrans.get(i)+"\n");
        }
        
        return res;
    }
}
