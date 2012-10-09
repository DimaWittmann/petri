/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package petri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.Console;


/**
 *
 * @author Wittmann
 */
public class Modeling {


	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       
        Device CPU =new Device("CPU",1, false);
        Device RAM=new Device("RAM",1, true);
        
        CPU.addConnection(CPU,0.5);
        CPU.addConnection(RAM,0.5);
        RAM.addConnection(CPU,1);
        
        ArrayList<Task> t=new ArrayList<>();
        
        t.add(new Task("T1"));
        t.add(new Task("T2"));
        
        for (int i=0;i<t.size();i++){
            CPU.addTask(t.get(i));
        }

        double curTime=0;
        double dTime=0.1;

        
        
		
        while (true){
            
            for (int i=0;i<t.size()-1;i++){
                if(curTime>=t.get(i).time){
                    t.get(i).goFuther();
                }
            }
            
            curTime+=dTime;
            


            double perf =  Math.round((CPU.p.timeOfExec/curTime<1)? CPU.p.timeOfExec/curTime * 100 : 100.0);
            System.out.print("CPU:"+ perf + " %         ");
            perf =  Math.round((RAM.p.timeOfExec/curTime<1)? RAM.p.timeOfExec/curTime * 100 : 100.0);
            System.out.println("RAM:"+perf+"%");
            //getKey();
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
