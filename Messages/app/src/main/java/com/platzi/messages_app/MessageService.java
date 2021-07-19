
package com.platzi.messages_app;

import java.util.Scanner;

public class MessageService {
	
    public static void createMessage(){
        Scanner sc = new Scanner(System.in);
        
		System.out.println("Write message:");
        String message = sc.nextLine();
        
        System.out.println("Author:");
        String author = sc.nextLine();
        
        Message newMessage = new Message();
        newMessage.setMessage(message);
        newMessage.setAuthorMessage(author);
        MessageDAO.createMessageDB(newMessage);
    }
    
    public static void getMessages(){
        MessageDAO.getMessagesDB();
    }
	
    public static void deleteMessage(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Write ID:");
        int id_message= sc.nextInt();
        MessageDAO.deleteMessageDB(id_message);
    }
    
    public static void updateMessage(){
        Scanner sc = new Scanner(System.in);
        
		System.out.println("Write new message:");
        String message = sc.nextLine();
        
        System.out.println("Write ID:");
        int id_message= sc.nextInt();
        
		Message updateMessage = new Message();
        updateMessage.setIdMessage(id_message);
        updateMessage.setMessage(message);
        MessageDAO.updateMessageDB(updateMessage);
        
    }
    
}
