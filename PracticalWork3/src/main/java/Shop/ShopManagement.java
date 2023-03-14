package Shop;

import Shop.LockBase;
import Shop.MortiseLocks;
import Shop.Padlocks;

import java.util.ArrayList;
public class ShopManagement {
    private ArrayList<LockBase> listCards = new ArrayList();
    // Добавить описание врезных замков
    public void addLock(String name, int weight, String color,int guarantee,int cost, int numOfKeysInc,int depth){
        MortiseLocks cardMortiseLocks = new MortiseLocks(getNextID(),name, weight, color, guarantee, cost,
                numOfKeysInc,depth);
        listCards.add(cardMortiseLocks);
    }
    // Добавить описание навесных замков
    public void addLock(String name, int weight, String color,int guarantee,int cost,int numOfKeysInc,float loopDi){
        Padlocks cardPadlocks = new Padlocks(getNextID(),name, weight, color, guarantee, cost, numOfKeysInc, loopDi);
        listCards.add(cardPadlocks);
    }
    private int getNextID(){
        int nextID;
        boolean freeID = true;
        do {
            freeID = true;
            nextID = (int)(Math.random() * 100000);
            for (int i = 0; i < listCards.size(); i++) {
                if (listCards.get(i).getId() == nextID)
                    freeID = false;
            }
        }while (freeID == false);
        return nextID;
    }
    public void removeCard(int index){
        if (index >= listCards.size())
            return;
        listCards.remove(index);
    }
    public LockBase getCard(int index){
        if (index >= listCards.size())
            return null;
        return listCards.get(index);
    }
    public LockBase getOf(int index){
        ArrayList<LockBase> listReturn = new ArrayList<LockBase>();
        for (int i = 0; i < listCards.size(); i++){
            LockBase card = listCards.get(i);
            if (card.getId()==index)
                return card;
        }
        return null;
    }
    public LockBase getAll(int row){
        if (listCards.size()>row){
            return listCards.get(row);
        }
        return null;
    }
    public ArrayList<LockBase> findCard(String name){
        ArrayList<LockBase> listReturn = new ArrayList<LockBase>();
        for (int i = 0; i < listCards.size(); i++){
            LockBase card = listCards.get(i);
            if (card.getName().contains(name))
                listReturn.add(card);
        }
        return listReturn;
    }
    public int getCount(){
        return listCards.size();

    }

}