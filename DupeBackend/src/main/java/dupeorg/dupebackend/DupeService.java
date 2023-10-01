package dupeorg.dupebackend;

import com.sun.net.httpserver.HttpServer;
import dupeorg.dupebackend.firebase.FirebaseInitializer;
import dupeorg.dupebackend.items.ItemAPI;
import dupeorg.dupebackend.items.ItemService;
import dupeorg.dupebackend.rest.MainServer;

import java.io.OutputStream;
import java.net.InetSocketAddress;

public class DupeService {
    private final FirebaseInitializer firebaseInitializer;
    private final ItemService itemService;
    private final MainServer mainServer;



    public DupeService(){
        this.firebaseInitializer = new FirebaseInitializer();
        firebaseInitializer.initialize();
        this.itemService = new ItemService();
        mainServer = new MainServer(itemService);
    }

    void execute(){
       try {
           String res = itemService.createItem("user-1","sofa", "sample_sofa_url", "This is an old sofa");
           mainServer.createAndStartServer();
       }
       catch(Exception e){
           System.out.println(e.toString());
       }


    }
}
