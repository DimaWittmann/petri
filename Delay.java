package petri;

/**
 *
 * @author Wittmann
 */
public class Delay extends Element {

    private double time;
    

    
    @Override
    public boolean isPassable() {
        return true;
    }
    @Override
    public boolean pass(Task t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean canStayForNight() {
        return false;
    }

    @Override
    public boolean stayForNight(Task t) {
        return false;
    }

    @Override
    public Task giveTask() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean canGiveTask() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
