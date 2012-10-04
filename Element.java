
package petri;

import java.util.ArrayList;

/**
 * Клас - отес для 
 * позицій і переходів - елементів мережі Петрі
 * @version alpha
 * @author Wittmann
 */
public abstract class Element {
    
    /**
     * Переходи на інші елементи
     */
    private ArrayList<Element> connections;
    /**
     * Ймовірність переходів
     * 
     */
    private ArrayList<Double> probability; 
    

    private static int count;
    
    private int id;
    
    
    
    public Element(){
       
        connections = new ArrayList<Element>();
        probability = new ArrayList<Double>();
        id=count;
        count++;
    }
    
    /**
     * Створюємо перехід 
     * @param el  Призначення
     * @param probab Ймовірність переходу
     * @return true - при додаванні
     */
    public boolean addConnection(Element el,double probab){
        if(connections.contains(el)==false){
            connections.add(el);
            probability.add(probab);
            return true;
        }else{
            return false;
        }
    }
    /**
     * Видалення переходу
     * @param el елемент до якого перехід
     * @return true при виаленні
     */
    public boolean delConnection(Element el){
        int pos=connections.indexOf(el);
        if (pos>-1){
            connections.remove(pos);
            probability.remove(pos);
            return true;
        }else{
            return false;
        }
        
    }
    /**
     * Чи можна пройди елемент в даний момент
     * @return true при можливму переході
     */
    public abstract boolean isPassable();
    
    /**
     * Пройти даний елемент мережі
     * @param t задача
     * @return true - елемент мережі успішно пройдений
     */
    public abstract boolean pass(Task t);
    /**
     * Чи може задача залишитися в елементі
     * виконуватися чи чекати в черзі
     * @return true при можливому виконанні
     */
    public abstract boolean canStayForNight();
    /**
     * Залишитися в черзі чи на виконання
     * @return true якщо є можливість
     */
    public abstract boolean stayForNight(Task t);
    /**
     * Видати задачу
     * @return  Task- задача
     */
    public abstract Task giveTask();
    
    /**
     * Присутність на позиції задачі
     * @return true - є задача, готова до зміни позиції
     */
    public abstract boolean canGiveTask();

    
}
