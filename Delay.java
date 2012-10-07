/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petri1;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Wittmann
 */
public class Delay extends Link{
    
    public ArrayList probability;
    private double tau;
    private Switch ready;
    
    public Delay(Switch ready,double tau){
        this.tau=tau;
        probability=new ArrayList();
        this.ready=ready;
    }
    
    public double getDelay(){
        Random r=new Random();
        return -tau*Math.log(r.nextGaussian());
    }
    
    public void addProb(double prob){
        probability.add(prob);
    }
    
    public void clearProb(){
        probability.clear();
    }
    
    public Link getRoad(){
        

        double p=0;
        double pNext=0;
        Random r= new Random();
        double prob=r.nextGaussian();
        ready.open();
        for (int i=0;i<probability.size();i++){
            pNext+=(double)probability.get(i);
            if (p<=prob && prob<=pNext){
                return (Link)connections.get(i);
            }
            
        }
        
        return (Link)connections.get(0);
    }
    
    
    
}
