/**
 * @author Jiayee
 *
 */
public class Clothing implements IClothing, IEquipment, Comparable<Clothing> {

    private String type;
    private String name;
    private int defence;
    private int enhancementLevel;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDefence() {
        return defence;
    }

    @Override
    public int getEnhancementLevel() {
        return enhancementLevel;
    }

    @Override
    public String[] getColours() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public char getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int compareTo(Clothing otherClothing) {
        return this.getName().compareTo(otherClothing.getName());
    }

}
