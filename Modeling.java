/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petri1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import petri.Petri;

/**
 *
 * @author Wittmann
 */
public class Modeling {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Queue q=new Queue("1");
        Switch s=new Switch("CPU");
        Immediate i=new Immediate(s);
        Delay d=new Delay(s,25);
        Processor p=new Processor(d, "CPU");
        
        q.addLink(i);
        i.addLink(p);
        d.addLink(q);
        d.addProb(1.0);
        
        Task t1=new Task("T1");
        Task t2=new Task("T2");
        
        q.addTask(t1);
        q.addTask(t2);
        

        while (true){
            t1.goFuther(); 
            t2.goFuther();
                    
            
            BufferedReader bReader = new BufferedReader (new InputStreamReader(System.in)); 
            try {
                bReader.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Petri.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
