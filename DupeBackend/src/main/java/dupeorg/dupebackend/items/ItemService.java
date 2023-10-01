package dupeorg.dupebackend.items;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class ItemService {
    private final static String collectionName = "items";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public ItemService() {
    }

    // Create a new item and return its unique ID
    public String createItem(String userId, String name, String imageUrl, String description) throws ExecutionException, InterruptedException, IOException {
        Firestore firestore = FirestoreClient.getFirestore();
        // Replace with your collection name
        Item item = new Item(userId, name, imageUrl, description);
        DocumentReference docRef = firestore.collection(collectionName).add(item).get();
        return docRef.getId();
    }

    // Retrieve an item by its unique ID
    public List<Item> getItemByUserId(String userId) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference itemsCollection = firestore.collection(collectionName);

        // Build a query to filter items by userId
        Query query = itemsCollection.whereEqualTo("userId", userId);

        // Execute the query
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        QuerySnapshot queryResult = querySnapshot.get();

        // Convert the query result to a list of Item objects
        List<Item> items = new ArrayList<>();
        for (QueryDocumentSnapshot document : queryResult) {
            items.add(document.toObject(Item.class));
        }

        return items;
    }


    // Update an item's data
    public void updateItem(String itemId, String userId, String name, String imageUrl, String description) {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference itemRef = firestore.collection(collectionName).document(itemId);
        Item updatedItem = new Item(userId, name, imageUrl, description, itemId);

        // Update the item's data in Firestore
        itemRef.set(updatedItem);
    }

    // Delete an item by its unique ID
    public void deleteItem(String itemId) {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference itemRef = firestore.collection(collectionName).document(itemId);

        // Delete the item from Firestore
        itemRef.delete();
    }

    // Retrieve a list of all items
    public List<Item> getAllItems() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestore.collection(collectionName).get();
        QuerySnapshot items = future.get();
        return items.toObjects(Item.class);
    }

    // Helper method to convert a list of items to JSON
    public String convertItemsToJson(List<Item> items) {
        return gson.toJson(items);
    }
}

