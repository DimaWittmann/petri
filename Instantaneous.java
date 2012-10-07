/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petri;

import java.util.ArrayList;

/**
 * Миттєвий перехід
 * @author Wittmann
 */
public class Instantaneous extends Element {
    
    private ArrayList<Element> connectedTo;

    protected Switch Switch;
    
    public Instantaneous(String name){
        super(name);
        connectedTo = new ArrayList<Element>();
    }

    @Override
    public boolean isPassable() {
        Element p=getNextElemnt();
        
        if((p.isPassable() || p.canStayForNight())){
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
            if (Switch!=null){
                Switch.resetState();
            }
            return true;
        }else{
            return false;
        }
    }

    @SuppressWarnings("not supported")
    @Override
    public boolean canStayForNight() {
        return false;
    }

    @SuppressWarnings("not supported")
    @Override
    public boolean stayForNight(Task t) {
        return false;
    }

    @SuppressWarnings("not supported")
    @Override
    public Task giveTask() {
        return null;
    }

    @SuppressWarnings("not supported")
    @Override
    public boolean canGiveTask() {
        return false;
    }

    @Override
    public Element getNextElemnt() {
        return connections.get(0);
    }
   
    /**
     * Додати перехід до цього елементу
     * @param el Елемент, що прокладає перехів
     */
    protected void addConnectedTo(Element el){
        connectedTo.add(el);
    }
    /**
     * Знищити перехід до цього елементу
     * @param el Елемент, що прокладав перехів
     */
    protected void delConnectedTo(Element el){
        connectedTo.remove(el);
    }
    
    public void addSwitch(Switch el){
        Switch=el;
    }
}
