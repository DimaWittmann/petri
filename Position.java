
package petri;

/**
 * Позиція - процесор або черга
 * @version alfa
 * @author Wittmann
 */
public class Position extends Element{

    private int capacity;
    private int curentNumberOfTasks;
    private Task[] tasks;
    
    public Position(int capacity){
        super();
        this.capacity=capacity;
        tasks = new Task [capacity];
    }
    
    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public boolean canStayForNight() {
        if(curentNumberOfTasks<capacity){
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean pass(Task t) {
        return false;
    }
    
}
