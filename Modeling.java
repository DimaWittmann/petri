
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


/**
 *
 * @author Wittmann
 */
public class Modeling {
    static double curTime=0;
    static double dTime=0.1;
    static View view;
    
    public Modeling(){
        JLabel lCPU=new JLabel("CPU ", SwingConstants.LEFT);
        
    }
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
       
        Device CPU =new Device("CPU",1, false);
        Device RAM=new Device("RAM",1, true);
        Device outSBridge = new Device("outSBridge",2,true);
        Device inSBridge = new Device("inSBridge",2,true);
        Device outNBridge = new Device("outDBridge",4,true);
        Device inNBridge = new Device("inDBridge",4,true);
        Device DC = new Device("DC", 43, true); 
        Device USB = new Device("USB",20,true);
        Device ISA = new Device("ISA",25,true);
        Device LPT = new Device("LPT",50,true);
        Device AU = new Device("AU",53,true);
        Device VP = new Device("VP",32,true);
        
        view =new View();
        
        Device [] devices = {CPU, RAM,outSBridge,inSBridge, outNBridge,
            inNBridge,DC,USB,ISA,LPT,AU,VP};
        
        CPU.addConnection(CPU,0.8);
        CPU.addConnection(inSBridge,0.2);
        
        inSBridge.addConnection(inNBridge, 0.25);
        inSBridge.addConnection(RAM, 0.75);
        
        RAM.addConnection(outSBridge,1);
        outSBridge.addConnection(CPU, 1);
        
        inNBridge.addConnection(USB, 0.25);
        inNBridge.addConnection(DC, 0.35);
        inNBridge.addConnection(ISA, 0.1);
        inNBridge.addConnection(AU, 0.1);
        inNBridge.addConnection(VP, 0.1);
        
        outNBridge.addConnection(outSBridge, 1);
        
        USB.addConnection(outNBridge, 0.6);
        USB.addConnection(ISA, 0.4);
        
        DC.addConnection(outNBridge, 1);
        
        ISA.addConnection(LPT, 0.5);
        ISA.addConnection(outNBridge, 0.5);
        
        LPT.addConnection(CPU, 1);
        
        AU.addConnection(CPU, 1);
        
        VP.addConnection(CPU, 1);
        
        ArrayList<Task> t=new ArrayList<>();
        
        t.add(new Task("T1"));
        t.add(new Task("T2"));
        t.add(new Task("T2"));
        
        for (int i=0;i<t.size();i++){
            CPU.addTask(t.get(i));
        }
        
        view.setVisible(true);


        while(true){
            int j=30000000;

            while (j>0){
                j--;
                for (int i=0;i<t.size();i++){
                if(curTime>=t.get(0).time){
                    t.get(0).goFuther();
                }
                

                curTime+=dTime;
                for (i=0;i<devices.length;i++) {
                    view.labels[i].setText(showPerf(devices[i]));
                } 
                Thread.sleep(1);

            }           
                
            }        
                

            System.out.println(curTime);

        }
        
        
        
    }
    
    
    
  
    public static String showPerf(Device device){
            double perf =  ((device.p.timeOfExec/curTime<1)? device.p.timeOfExec/curTime * 100 : 100.0);
            return ((int)(perf)+"%");
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
