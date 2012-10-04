
package petri;

/**
 * Задача - що виконуватиметься
 * в мережі Петрі
 * @version alfa
 * @author Wittmann
 */
public class Task {
    
    /**
     * Місце знаходження
     * задачі
     */
    private Element position;
    
    /**
     * Кількість створених елементів
     */
    private static int count;
    
    private int id;
    
    
    
    public Task(){
        id=count;
        count++;
    }
    
    /**
     * Перехід на наступний елемент мережі
     * @return 
     */
    public boolean goToNext(Element p){
        position = p;
        //TODO зробити параметри переходу
        return false;
    }
}
