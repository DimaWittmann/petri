
package petri;

/**
 * Позиція - процесор або черга
 * @version alfa
 * @author Wittmann
 */
public class Position extends Element{

    protected int capacity;
    protected int curentNumberOfTasks;
    protected Task[] tasks;
    
    public Position(String name,int capacity){
        super(name);
        this.capacity=capacity;
        tasks = new Task [capacity];
    }
    
    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public boolean canStayForNight() {
        if( curentNumberOfTasks<capacity){
            return true;
        }else{
            return false;
        }
    }

   
    @Override
    public boolean stayForNight(Task t) {
       if(canStayForNight()){
           tasks[curentNumberOfTasks]=t;
           curentNumberOfTasks++;
           t.setPosition(this);
           t.setTime(0);
           return true;
       }else{
           return false;
       }
    }

    @Override
    public Task giveTask() {
        Task t=null;
        if(canGiveTask()){
            t=tasks[0];
            curentNumberOfTasks--;
            for (int i=0;i<curentNumberOfTasks;i++){
                tasks[i]=tasks[i+1];
            }
            
        }
        return t;
    }

    @Override
    public boolean canGiveTask() {
        if(curentNumberOfTasks>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean pass(Task t) {
        return false;
    }

    @Override
    public Element getNextElemnt() {
        return connections.get(0);
    }
    
    
    
}
