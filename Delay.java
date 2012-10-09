/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petri;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Wittmann
 */
public class Delay extends Link{
    
    public ArrayList<Double> probability;
    private double tau;
    private Switch ready;
    private boolean poisson;
    
    public Delay(Switch ready,double tau,boolean poisson ){
        this.tau=tau;
        probability=new ArrayList();
        this.ready=ready;
        this.poisson=poisson;
    }
    
    public double getDelay(){
        Random r=new Random();
        if(poisson){
            
            return -tau*Math.log(r.nextDouble());
        }else{
            return tau;
        }
    }
    
    public void addProb(double prob){
        probability.add(prob);
    }
    
    public void clearProb(){
        probability.clear();
    }
    
    public Link getRoad(Task t){
        

        double p=0;
        double pNext=0;
        Random r= new Random();
        double prob=r.nextDouble();
        ready.open();
        for (int i=0;i<probability.size();i++){
            pNext+=(double)probability.get(i);
            if (p<=prob && prob<=pNext){
                return connections.get(i);
            }
            
        }
        
        return connections.get(0);
    }
    
    
    
}
