package Shop;


public class Padlocks extends LockBase {// навесные замки
    private float loopDi;
    public Padlocks(int id, String name, int weight, String color,int guarantee,int numOfKeysInc,int cost,float loopDi) {
        super(id, name, weight, color, guarantee, numOfKeysInc,cost);
        this.loopDi=loopDi;
    }
    @Override
    public String getInfo() {
        String str = "Название: "+ name + ", Цвет: " + color + ", Вес: " + weight + ", Кол-во ключей в комплекте: " +
                numOfKeysInc + ", Диаметр петли:" + loopDi+", Гарантия: " + guarantee + ", Цена: " + cost + ", Гарантия: " + guarantee  + ".";
        return str;
    }
    public float getLoopDi(){
        return loopDi;
    }

}
