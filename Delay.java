package petri;

import java.util.Random;
/**
 * Затримка задачі
 * @author Wittmann
 */
public class Delay extends Element {

    protected double time;
    protected Element el;
    
    protected double curTime;
    protected Element curTrans;
    
    protected Switch Switch;
    
    private boolean state;
    /**
     * Створити елемент затримку
     * @param t час затримки
     */
    public Delay(String name,double t){
        super(name);
        time = t;
        state=true;
    }
    
    /**
     * Оновлює наступний перехід
     * і час затримки
     */
    public void update(){
        curTime=genTime();
        curTrans=genNext();
        state = true;
    }
    /**
     * Генерація затримки
     * @return  
     */        
    public double genTime(){
        Random r=new Random();
        return -(time)*Math.log(r.nextGaussian());
    }
    /**
     * Пошук наступного елементу
     */
    public Element genNext(){
        Random r = new Random();
        double prob =r.nextGaussian();
        double start= 0.0;
        for (int i=0;i<probability.size();i++){
            if (prob<=probability.get(i) && probability.get(i)>=start){
                return connections.get(i);
            }
            start+=probability.get(i);
        }
        
        return connections.get(0);
        
    }
    
    @Override
    public boolean isPassable() {
    
        Element p=getNextElemnt();
        
        if(p.isPassable() ){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public boolean pass(Task t) {
        if (isPassable()){
            t.setPosition(this);
            t.setTime(0);
            state=false;
            if (Switch!=null){
                Switch.setState();
            }
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public boolean canStayForNight() {
        return false;
    }

    @Override
    public boolean stayForNight(Task t) {
        return false;
    }

    @Override
    public Task giveTask() {
        return null;
    }

    @Override
    public boolean canGiveTask() {
        return false;
    }

    @Override
    public Element getNextElemnt() {
        return curTrans;
    }

    public double getNextDelay(){
        return curTime;
    }
    
    public void addSwitch(Switch el){
        Switch=el;
    }

   
}
