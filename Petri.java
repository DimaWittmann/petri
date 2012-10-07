/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wittmann
 */
public class Petri {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int numOfTask=3;
        
        Element queue=new Position("черга1",numOfTask);
        Element instant=new Instantaneous("миттєвий");
        Element proc = new Processor("процик",1);
        Element swithcer = new Switch("перемикач");
        Element delay = new Delay("затримка",2.4);
        Task task1=new Task();
        Task task2=new Task();
       
        queue.addConnection(instant, 1.0);
        instant.addConnection(proc, 1.0);
        proc.addConnection(delay, 1.0);
        ((Delay)delay).addSwitch((Switch)swithcer);
        delay.addConnection(queue, 1.0);
        swithcer.addConnection(instant, 1.0);
        queue.stayForNight(task1);
        queue.stayForNight(task2);
        
        while(true){
            
            ((Delay)delay).update();
            System.out.println(task1);
            System.out.println(task2);
            
            BufferedReader bReader = new BufferedReader (new InputStreamReader(System.in)); 
            try {
                bReader.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Petri.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            task1.goFuther();
            task2.goFuther();
        }
    }
}
