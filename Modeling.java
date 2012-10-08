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
public class Modeling {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Device CPU =new Device("CPU",1, false);
        Device RAM=new Device("RAM",5, true);
        
        CPU.d.addLink(CPU.q);
        CPU.d.addProb(0.5);
        CPU.d.addLink(RAM.q);
        CPU.d.addProb(0.5);
        RAM.d.addLink(CPU.q);
        RAM.d.addProb(1);
        
        Task t1=new Task("T1");
        Task t2=new Task("T2");
        
        CPU.q.addTask(t1);
        CPU.q.addTask(t2);
        

        while (true){
            t1.goFuther(); 
            t2.goFuther();
                    
            
            getKey();
        }
    }
    
    
    public static void getKey(){
            BufferedReader bReader = new BufferedReader (new InputStreamReader(System.in)); 
            try {
                if(0==bReader.readLine().compareTo(" ") ){
                    System.exit(0);
                }
            } catch (IOException ex) {
                Logger.getLogger(Modeling.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
