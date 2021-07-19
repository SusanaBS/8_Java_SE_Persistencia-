package com.platzi.messages_app;

import java.sql.Connection;
import java.util.Scanner;

public class Menu {
    
    public static void main(String[] args){
    
       Scanner sc = new Scanner(System.in);
       
       int opcion=0;
       
       do{
            System.out.println("-----------------");
            System.out.println(" Messages App");
            System.out.println(" 1. Create Messages");
            System.out.println(" 2. Get Messages");
            System.out.println(" 3. Delete Messages");
            System.out.println(" 4. Edit Messages");
            System.out.println(" 5. Exit");

            opcion = sc.nextInt();
            
            switch (opcion){
                case 1:
                    MessageService.createMessage();
                    break;
                case 2:
                    MessageService.getMessages();
                    break;
                case 3:
                    MessageService.deleteMessage();
                    break;
                case 4:
                    MessageService.updateMessage();
                    break;
                default:
                    break;
            }
           
       }while(opcion != 5);
        
       
    }
    
}
