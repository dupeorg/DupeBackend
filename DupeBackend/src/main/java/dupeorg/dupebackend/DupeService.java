package dupeorg.dupebackend;

import dupeorg.dupebackend.firebase.FirebaseInitializer;

public class DupeService {
    private final FirebaseInitializer firebaseInitializer;

    public DupeService(){
        this.firebaseInitializer = new FirebaseInitializer();
    }

    void execute(){
       firebaseInitializer.initialize();
    }
}
