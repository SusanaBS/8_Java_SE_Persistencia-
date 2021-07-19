
package com.platzi.messages_app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageDAO {
    
    public static void createMessageDB(Message message){
        Conexion db_connect = new Conexion();
        
        try(Connection conexion = db_connect.get_connection())  {
            PreparedStatement  ps=null;
            try{
                String query="INSERT INTO messages ( message, author_message) VALUES (?,?)";
                ps=conexion.prepareStatement(query);
                ps.setString(1, message.getMessage());
                ps.setString(2, message.getAuthorMessage());
                ps.executeUpdate();
                System.out.println("Created message OK");
                
            }catch(SQLException ex){
                System.out.println(ex);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public static void getMessagesDB(){
        Conexion db_connect = new Conexion();
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try(Connection conexion = db_connect.get_connection())  {        
            String query="SELECT * FROM messages";
            ps=conexion.prepareStatement(query);
            rs=ps.executeQuery();
            
            while(rs.next()){
                System.out.println("ID: "+rs.getInt("id_message"));
                System.out.println("Message: "+rs.getString("message"));
                System.out.println("Author: "+rs.getString("author_message"));
                System.out.println("Date: "+rs.getString("date_message"));
                System.out.println("");
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public static void deleteMessageDB(int id_message){
        Conexion db_connect = new Conexion();
        
        try(Connection conexion = db_connect.get_connection())  {
        PreparedStatement ps=null;
        
            try {
                String query="DELETE FROM messages WHERE id_message = ?";
                ps=conexion.prepareStatement(query);
                ps.setInt(1, id_message);
                ps.executeUpdate();
                System.out.println("Deleted message OK");
            }catch(SQLException e) {
                System.out.println(e);
            }
        
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
    }
    
    public static void updateMessageDB(Message message){
        Conexion db_connect = new Conexion();
        
        try(Connection conexion = db_connect.get_connection())  { 
        PreparedStatement ps=null;
        
            try{ 
                String query="UPDATE messages SET message = ? WHERE id_message = ?";
                ps=conexion.prepareStatement(query);
                ps.setString(1, message.getMessage());
                ps.setInt(2, message.getIdMessage());
                ps.executeUpdate();
                System.out.println("Updated message OK");
            }catch(SQLException ex){ 
                System.out.println(ex);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
}
