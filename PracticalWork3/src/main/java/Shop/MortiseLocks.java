package Shop;

import Shop.LockBase;

public class MortiseLocks extends LockBase {// врезные замки
        private int depth;
        public MortiseLocks(int id, String name, int weight, String color, int guarantee, int numOfKeysInc, int cost, int depth) {
            super(id, name, weight, color, guarantee, numOfKeysInc,cost);
            this.depth = depth;
        }
        @Override
        public String getInfo() {
            String str = "Название: "+ name + ", Цвет: " + color + ", Вес: " + weight+ ", Кол-во ключей в комплекте: "
                    + numOfKeysInc + ", Глубина: " + depth + ", Гарантия: " + guarantee + ", Цена: " + cost +  ".";
            return str;
        }
    public int getDepth(){
        return depth;
    }
}

