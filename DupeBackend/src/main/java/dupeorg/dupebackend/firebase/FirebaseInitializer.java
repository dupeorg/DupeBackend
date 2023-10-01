package dupeorg.dupebackend.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.IOException;

import static dupeorg.dupebackend.constants.StringConstants.*;


public class FirebaseInitializer {

    public void initialize() {
        try {
            FileInputStream serviceAccount = new FileInputStream(FIREBASE_PATH_KEY);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(DATABASE_PATH) // Replace with your Firebase project's database URL
                    .build();

            FirebaseApp.initializeApp(options);
            System.out.println("Firebase Initialized");
        } catch (IOException e) {
            e.printStackTrace();;
        }
    }
}

