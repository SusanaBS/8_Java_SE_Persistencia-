
package com.platzi.cats_app;

import java.io.IOException;
import javax.swing.JOptionPane;


public class Inicio {
    
    public static void main(String[] args) throws IOException{
        int option_menu = -1;
        String[] buttons = {"1. Show cats", "2. Get favorites", "3. Exit"};
        
        do{
            
            String option = (String) JOptionPane.showInputDialog(null, "Cats java", "Menu", JOptionPane.INFORMATION_MESSAGE,
                    null, buttons,buttons[0]);
            
            for(int i=0;i<buttons.length;i++){
                if(option.equals(buttons[i])){
                    option_menu = i;
                }
            }
            
            switch(option_menu){
                case 0:
                    CatService.getCats();
                    break;
                case 1:
                    Cat cat = new Cat();
                    CatService.getFavoriteCat(cat.getApikey());
                default:
                    break;
            }     
        }while(option_menu != 1);
        
    }
    
}
