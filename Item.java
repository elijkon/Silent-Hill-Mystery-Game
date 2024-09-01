
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    private String description;
    private int weight;
    private boolean haveB;
    /**
     * Constructor for objects of class Item
     */
    public Item(String description, int weight)
    {
        this.description = description;
        this.weight = weight;
        haveB = false;
    }
    /**
     * @return weight of item.
     */
    public int getWeight() {
        return weight;
    }
    /**
     * @return a string of the description.
     */
    public String toString(){
        return description;
    }
    /**
     * return a boolean if the flashlight has battereis.
     */
    public boolean haveBatteries(){
        return haveB;
    }
    /**
     * Sets the haveB boolean to true.
     */
    public void insert(){
        haveB = true;
    }
}
