/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petri;

/**
 * Перемикач, для визначення зайнятості процесора
 * @author Wittmann
 */
public class Switch extends Position{
    
    /**
     * Зайнятість процесора
     */
    private boolean state;
    
    public Switch(String name){
        super(name,1);
        state=true;
    }
    
    
    @Override
     public boolean canGiveTask(){
         return state;
     }
     
    /**
     * Встановити стан готовності
     */
    public void setState() {
        state=true;
    }
    
    /**
     * Встановити стан занятості
     */
    public void resetState() {
        state=true;
    }
    
    
    
    
    
    
}
