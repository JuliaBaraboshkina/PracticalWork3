package Shop;

public class LockBase {
    protected final int id;
    protected final String name;
    protected final int weight;
    protected final String color;
    protected final int guarantee;
    protected final int numOfKeysInc;
    protected final int cost;

    public LockBase(int id, String name, int weight, String color,int guarantee,int numOfKeysInc, int cost){
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.color = color;
        this.guarantee = guarantee;
        this.numOfKeysInc = numOfKeysInc;
        this.cost = cost;

    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getWeight(){
        return weight;
    }
    public String getColor(){
        return color;
    }
    public int getGuarantee(){
        return guarantee;
    }
    public int getNumberOfKeysIncluded(){
        return numOfKeysInc;
    }
    public int getCost(){
        return cost;
    }
    public String getInfo(){
        String str = name + ", Цвет: " + color + ", Вес: " + weight + ", Кол-во ключей в комплекте: "
                + numOfKeysInc + ", Цена: " + cost + ", Гарантия: " + guarantee + ".";
        return str;
    }
}
