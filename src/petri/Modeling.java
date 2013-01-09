package petri;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import markov.MathModeling;


public class Modeling {
    static double curTime=0;
    static double dTime=0.1;
    static View view;
    public static double [] delay = {1,3,4,6,7,7,78,34,43,34,23,16};
    public static double [][] transitions = initTrans();
    
    public static double[][] initTrans() {
        double transitions[][] = new double[12][12];
        transitions[0][0] = 0.98;
        transitions[0][1] = 0.02;
        transitions[1][3] = 0.75;
        transitions[1][5] = 0.25;
        transitions[3][2] = 1;
        transitions[2][0] = 1;
        transitions[5][6] = 0.4;
        transitions[5][7] = 0.3;
        transitions[5][8] = 0.1;
        transitions[5][10] = 0.1;
        transitions[5][11] = 0.1;
        transitions[4][2] = 1;
        transitions[7][4] = 0.7;
        transitions[7][8] = 0.3;
        transitions[6][4] = 1;
        transitions[8][9] = 1;
        transitions[9][0] = 1;
        transitions[10][0] = 1;
        transitions[11][0] = 1;
        return transitions;
    }

        
    public Modeling(){
        JLabel lCPU=new JLabel("CPU ", SwingConstants.LEFT);
        
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        
        MathModeling.start();
        
        Device CPU =new Device("CPU",delay[0], false);
        Device RAM=new Device("RAM",delay[1], true);
        Device outSBridge = new Device("outSBridge",delay[2],true);
        Device inSBridge = new Device("inSBridge",delay[3],true);
        Device outNBridge = new Device("outDBridge",delay[4],true);
        Device inNBridge = new Device("inDBridge",delay[5],true);
        Device DC = new Device("DC", delay[6], true); 
        Device USB = new Device("USB",delay[7],true);
        Device ISA = new Device("ISA",delay[8],true);
        Device LPT = new Device("LPT",delay[9],true);
        Device AU = new Device("AU",delay[10],true);
        Device VP = new Device("VP",delay[11],true);
        
        view =new View();
        
        Device [] devices = {CPU, RAM,outSBridge,inSBridge, outNBridge,
            inNBridge,DC,USB,ISA,LPT,AU,VP};
        
        CPU.addConnection(CPU,transitions[0][0]);
        CPU.addConnection(inSBridge,transitions[0][1]);
        
        inSBridge.addConnection(inNBridge, transitions[1][5]);
        inSBridge.addConnection(RAM, transitions[1][3]);
        
        RAM.addConnection(outSBridge,transitions[3][2]);
        
        outSBridge.addConnection(CPU, transitions[2][0]);
        
        inNBridge.addConnection(DC, transitions[5][6]);
        inNBridge.addConnection(USB, transitions[5][7]);
        inNBridge.addConnection(ISA, transitions[5][8]);
        inNBridge.addConnection(AU, transitions[5][10]);
        inNBridge.addConnection(VP, transitions[5][11]);
        
        outNBridge.addConnection(outSBridge, transitions[4][2]);
        
        USB.addConnection(outNBridge, transitions[7][4]);
        USB.addConnection(ISA, transitions[7][8]);
        
        DC.addConnection(outNBridge, transitions[6][4]);
        
        ISA.addConnection(LPT, transitions[8][9]);
 
        LPT.addConnection(CPU, transitions[9][0]);
        
        AU.addConnection(CPU, transitions[10][0]);
        
        VP.addConnection(CPU, transitions[11][0]);
        
        ArrayList<Task> t=new ArrayList<>();
        
        t.add(new Task("T1"));
        t.add(new Task("T2"));
        t.add(new Task("T3"));
        
        for (int i=0;i<t.size();i++){
            CPU.addTask(t.get(i));
        }
        
        view.setVisible(true);
        while (true){

            for (int i=0;i<t.size();i++){
                if(curTime>=t.get(i).time){
                    t.get(i).goFuther();
                }
            }
            curTime+=dTime;
            for (int i=0;i<devices.length;i++) {
                view.labels[i].setText(showPerf(devices[i]));
                
                String str= ((Integer)devices[i].p.taskExec).toString();
                view.counters[i].setText(str);
               
                view.t1Position.setText(t.get(0).position.toString());
                view.t2Position.setText(t.get(1).position.toString());
                view.t3Position.setText(t.get(2).position.toString());
            }
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
