package com.platzi.cats_app;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class CatService {
    
    public static void getCats() throws IOException{

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("https://api.thecatapi.com/v1/images/search").get().build();

        Response response = client.newCall(request).execute();
        
        String elJson = response.body().string();
 
        elJson = elJson.substring(1, elJson.length());
        elJson = elJson.substring(0, elJson.length()-1);
              
        Gson gson = new Gson();
        Cat cat = gson.fromJson(elJson, Cat.class);
        
        Image image = null;
        try{
            URL url = new URL(cat.getUrl());
            image = ImageIO.read(url);
            
            ImageIcon wallpaper = new ImageIcon(image);
            
            if(wallpaper.getIconWidth() > 800){
                Image currentWallpaper = wallpaper.getImage();
                Image newWallpaper = currentWallpaper.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                wallpaper = new ImageIcon(newWallpaper);
            }
            
            String menu = "Options: \n"
                    + " 1. Next \n"
                    + " 2. Favorite \n"
                    + " 3. Back \n";
            
            String[] buttons = { "Next", "Favorite", "Back" };
            String id_cat = cat.getId();
            String option = (String) JOptionPane.showInputDialog(null,menu,id_cat, JOptionPane.INFORMATION_MESSAGE, wallpaper, buttons,buttons[0]);
            
            int selected = -1;
            for(int i=0;i<buttons.length;i++){
                if(option.equals(buttons[i])){
                    selected = i;
                }
            }
            
            switch (selected){
                case 0:
                    getCats();
                    break;
                case 1:
                    favoriteCat(cat);
                    break;
                default:
                    break;
            }
            
        }catch(IOException e){
               System.out.println(e);
        }
        

    }
    
    public static void favoriteCat(Cat cat) {
        try{
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n\t\"image_id\":\""+cat.getId()+"\"\n}");
            Request request = new Request.Builder()
              .url("https://api.thecatapi.com/v1/favourites")
              .post(body)
              .addHeader("Content-Type", "application/json")
              .addHeader("x-api-key", cat.getApikey())
              .build();
            Response response = client.newCall(request).execute();            
                  
        }catch(IOException e){
            System.out.println(e);
        }
  
    }

    public static void getFavoriteCat(String apikey) throws IOException{
    
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
          .url("https://api.thecatapi.com/v1/favourites")
          .get()
          .addHeader("Content-Type", "application/json")
          .addHeader("x-api-key", apikey)
          .build();

        Response response = client.newCall(request).execute();
        
        String elJson = response.body().string();
        
        Gson gson = new Gson();
        
        FavoriteCat[] favoriteCats = gson.fromJson(elJson,FavoriteCat[].class);
        
        if(favoriteCats.length > 0){
            int min = 1;
            int max  = favoriteCats.length;
            int random = (int) (Math.random() * ((max-min)+1)) + min;
            int index = random-1;
            
            FavoriteCat favoriteCat = favoriteCats[index];
            
                Image image = null;
                try{
                    URL url = new URL(favoriteCat.image.getUrl());
                    image = ImageIO.read(url);

                    ImageIcon wallpaper = new ImageIcon(image);

                    if(wallpaper.getIconWidth() > 800){
                        Image currentWallpaper = wallpaper.getImage();
                        Image newWallpaper = currentWallpaper.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                        wallpaper = new ImageIcon(newWallpaper);
                    }

                    String menu = "Options: \n"
							+ " 1. Next \n"
							+ " 2. Favorite \n"
							+ " 3. Back \n";

                    String[] buttons = { "Next", "Favorite", "Back" };
                    String id_cat = favoriteCat.getId();
                    String option = (String) JOptionPane.showInputDialog(null,menu,id_cat, JOptionPane.INFORMATION_MESSAGE, wallpaper, buttons,buttons[0]);
                    
					int selected = -1;
                    for(int i=0;i<buttons.length;i++){
                        if(option.equals(buttons[i])){
                            selected = i;
                        }
                    }

                    switch (selected){
                        case 0:
                            getFavoriteCat(apikey);
                            break;
                        case 1:
                            deleteFavoriteCat(favoriteCat);
                            break;
                        default:
                            break;
                    }

                }catch(IOException e){
                       System.out.println(e);
                }
            
        }
        
    }

    public static void deleteFavoriteCat(FavoriteCat favoriteCat){
        try{
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
              .url("https://api.thecatapi.com/v1/favourites/"+favoriteCat.getId()+"")
              .delete(null)
              .addHeader("Content-Type", "application/json")
              .addHeader("x-api-key", favoriteCat.getApikey())
              .build();

            Response response = client.newCall(request).execute();
        }catch(IOException e){
            System.out.println(e);
        }
        
    }
}
