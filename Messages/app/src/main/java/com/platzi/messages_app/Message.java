package com.platzi.messages_app;

public class Message {
    int id_message;
    String message;
    String author_message;
    String date_message;

    public Message(){     
    }

    public Message(String message, String author_message, String date_message) {
        this.message = message;
        this.author_message = author_message;
        this.date_message = date_message;
    }
    
    public int getIdMessage() {
        return id_message;
    }

    public void setIdMessage(int id_message) {
        this.id_message = id_message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthorMessage() {
        return author_message;
    }

    public void setAuthorMessage(String author_message) {
        this.author_message = author_message;
    }

    public String getDateMessage() {
        return date_message;
    }

    public void setDateMessage(String date_message) {
        this.date_message = date_message;
    }

}
