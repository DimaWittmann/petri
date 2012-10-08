/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package petri;

/**
 *
 * @author Wittmann
 */
public class Device {
    
    public Queue q;
    public Switch s;
    public Immediate i;
    public Delay d;
    public Processor p;
   
    
    public Device(String name,double tau, boolean poisson ){
        q=new Queue(name);
        s=new Switch(name);
        i=new Immediate(s);
        d=new Delay(s,tau,poisson);
        p=new Processor(d, name);
        p.queue=q;
        
        q.addLink(i);
        i.addLink(p);
        
    }
    
}
