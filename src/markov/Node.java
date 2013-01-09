
package markov;

import java.util.ArrayList;

public class Node implements Comparable<Node> {

    public int [] state;
    public ArrayList<Node> in;
    public ArrayList<Node> out;
    public double processor;
    public int index;
    
    public Node(){
        in = new ArrayList<>();
        out = new ArrayList<>();
    }

    @Override
    public int compareTo(Node o) {
        int [] state1 = o.state;
        for (int i=0;i<state.length;i++){
            if (state1[i] != state[i]){
                return 1;
            }
        }
        return 0;
    }
    
    @Override
    public boolean equals(Object  o){
        if(compareTo((Node)o) != 0){
            return false;
        }else{
            return true;
        }
    }
    @Override
    public String toString(){
        String s="";
        for (int i=0;i<state.length;i++){
            s += state[i];
        }
        return s;
    }
    public void print(){
        System.out.println(this);
        System.out.print("in ");
        for (int i=0;i<in.size();i++){
            System.out.print(in.get(i) +" ");
        }
        System.out.println("out ");
        for (int i=0;i<out.size();i++){
            System.out.print(out.get(i) +" ");
        }
        System.out.println("proc:" +processor);
    }
}
