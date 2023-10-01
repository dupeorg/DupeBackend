package dupeorg.dupebackend.tradeRequest;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TradeRequestService {
    private final static String collectionName = "trade_requests";

    public TradeRequestService() {
    }

    // Create a new trade request and return its unique ID
    public String createTradeRequest(String sender_id, String sender_item, String receiver_id, String receiver_item)
            throws ExecutionException, InterruptedException, IOException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference tradeRequestCollection = firestore.collection(collectionName);

        // Create a new trade request object
        TradeRequest tradeRequest = new TradeRequest(sender_id, sender_item, receiver_id, receiver_item);

        // Add the trade request to Firestore and get its ID
        DocumentReference docRef = tradeRequestCollection.add(tradeRequest).get();
        return docRef.getId();
    }

    // Retrieve all trade requests
    public List<TradeRequest> getAllTradeRequests() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference tradeRequestCollection = firestore.collection(collectionName);

        ApiFuture<QuerySnapshot> future = tradeRequestCollection.get();
        QuerySnapshot tradeRequests = future.get();

        return tradeRequests.toObjects(TradeRequest.class);
    }

    // You can implement methods for updating and deleting trade requests if needed
}
