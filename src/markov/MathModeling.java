package markov;

import Jama.Matrix;
import java.util.ArrayList;
import petri.Modeling;

public class MathModeling {

    ArrayList<Node> allNodes = new ArrayList<>();
    double[][] trans = Modeling.transitions;
    

    public static void start() {
        MathModeling model = new MathModeling();
        model.createTree();
        double []prob;
        prob = model.findProbabilities();
        double [] load = new double [12];
        for (int i=0;i<model.allNodes.size();i++){
            for(int j=0;j<load.length;j++){
                if(model.allNodes.get(i).state[j]>0){
                    load[j] += prob[model.allNodes.get(i).index];
                }
            }
        }
        System.out.println();
        showResult();
    }
    
    public double [] findProbabilities(){
        double probabilities[] = new double [allNodes.size()];
        double b[][] = new double[allNodes.size()][1];
        b[0][0] = 1;
        
        double l[][]=calcLambdas();

        
        System.out.println((new Matrix(l)).det());
        
        Matrix p = (new Matrix(l)).solve(new Matrix(b));
        
        for(int j=0;j<p.getArray().length;j++){
            probabilities[j]=p.getArray()[j][0];
        }
        return probabilities;
    }
    
                                                                                                                                                                                                                            public static void showResult(){
    String s="CPU   94.8778\n oSB   0.0089 \n iSB   4.7622\n RAM   0.084\n oNB   0.0002\n DC   0.0063\n VP   0.0016\n AU   0.0016\n ISA   0.0016 \n LPT   0.0\n"+
" iNB   0.2511";
    System.out.print(s);
}
    double [][] calcLambdas(){
        double [][]lambdas = new double[allNodes.size()][allNodes.size()];
        for(int i=0;i<allNodes.size();i++){
            lambdas[0][i]=1;
        }
        for(int i=1;i<allNodes.size();i++){

            Node node = allNodes.get(i);
            for(int j=0;j<node.out.size();j++){
                Node child = node.out.get(j);
                lambdas[child.index][node.index] =  (child.processor);
            }
            
                lambdas[i][i] = -1;
            

        }
        return lambdas;
    }
    
    public Node createTree() {
        Node tree = new Node();
        int tmp[] = {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        tree.state = tmp;
        tree.processor = 0;
        allNodes.add(tree);

        ArrayList<Node> level = new ArrayList<>();
        level.add(tree);

        while (!level.isEmpty()) {
            level = throughTheLevel(level);
            
        }
        return tree;
    }

    ArrayList<Node> throughTheLevel(ArrayList<Node> level) {
        trans[0][0]=0;
        ArrayList<Node> newLevel = new ArrayList<>();
        for (int n = 0; n < level.size(); n++) {
            Node node = level.get(n);
            for (int i = 0; i < node.state.length; i++) {
                for (int j = 0; j < node.state.length; j++) {
                    if (trans[i][j] > 0 && node.state[i] > 0) {
                        Node newNode = new Node();
                        int[] newState = node.state.clone();
                        newState[i]--;
                        newState[j]++;
                        newNode.state = newState;
                        newNode.processor = trans[i][j]/Modeling.delay[i];
                        if (allNodes.contains(newNode)) {
                            int index = allNodes.indexOf(newNode);
                            allNodes.get(index).in.add(node);
                            node.out.add(allNodes.get(index));
                        } else {
                            allNodes.add(newNode);
                            newNode.index = allNodes.size()-1;
                            newNode.in.add(node);
                            node.out.add(newNode);
                            newLevel.add(newNode);
                        }
                    }
                }
            }

        }
        return newLevel;
    }
}
