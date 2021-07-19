
package com.platzi.cats_app;

public class FavoriteCat {
    String id;
    String image_id;
    String apikey= "74668d02-a299-40ae-bb05-130d2e65c227"; 
    Imagex image;        

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageId() {
        return image_id;
    }

    public void setImageId(String image_id) {
        this.image_id = image_id;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public Imagex getImage() {
        return image;
    }

    public void setImage(Imagex image) {
        this.image = image;
    }
    
    
    
}
