package dupeorg.dupebackend.items;

import com.google.cloud.firestore.annotation.DocumentId;

public class Item {

    private String userId;
    private String name;
    private String imageUrl;
    private String description;
    @DocumentId
    private String itemId;

    public Item(){

    }
    public Item(String userId, String name, String imageUrl, String description) {
        this.userId = userId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
    }
    public Item(String userId, String name, String imageUrl, String description, String itemId) {
        this.userId = userId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }


}
