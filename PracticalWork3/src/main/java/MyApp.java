import Inteface.FrameApp;
import Shop.ShopManagement;

import javax.swing.*;
public class MyApp {
    public static void main(String[] args){
        ShopManagement shopManagement = new ShopManagement();
        FrameApp frameApp = new FrameApp(shopManagement);
        frameApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameApp.setSize(700, 500);
        frameApp.setVisible(true);
    }
}