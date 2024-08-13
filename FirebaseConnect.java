
package Model;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FirebaseConnect {
    public static Firestore db;
    
    public static void conectarFirebase() throws FileNotFoundException, IOException{
        FileInputStream serviceAccount =
        new FileInputStream("C:\\Users\\ESTUDIANTES\\Downloads\\proyecto-98992-firebase-adminsdk-n889n-122e591122.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build();

        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
        System.out.println("Conectado con exito");
    }
}
