package Controller;

import Model.FirebaseConnect;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Precondition;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProviderMenu{
    CollectionReference referencia;
    static Firestore db;
    
    public static boolean guardarMenu(String coleccion, String documento, 
    Map<String, Object> data){
        
        db = FirestoreClient.getFirestore();
        try{
            DocumentReference docRef = db.collection(coleccion).document(documento);
            ApiFuture<WriteResult> result = docRef.set(data);
            System.out.println("Guardado correctamente");
            return true;
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
            
    }
    
    public static boolean EliminarPedido(String coleccion, String documento){
        
        db = FirestoreClient.getFirestore();
        try{
            DocumentReference docRef = db.collection(coleccion).document(documento);
            ApiFuture<WriteResult> result = docRef.delete(Precondition.NONE);
            System.out.println("Eliminado correctamente");
            return true;
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
            
    }
    
    public static void cargarTabla(JTable tabla){
        
        DefaultTableModel tablemodel = new DefaultTableModel();
        tablemodel.addColumn("Id");
        tablemodel.addColumn("Nombre");
        tablemodel.addColumn("Mesa");
        tablemodel.addColumn("Menu");
        
        
        try {
            CollectionReference personas = FirebaseConnect.db.collection("Pedido");
            ApiFuture<QuerySnapshot> querySnap = personas.get();
            for (DocumentSnapshot document: querySnap.get().getDocuments()){
                tablemodel.addRow(new Object[]{
                    document.getId(),
                    document.getString("Nombre"),
                    document.getString("Mesa"),
                    document.getString("Comida")
                });
            }
        } catch (Exception e) {
        }
        tabla.setModel(tablemodel);
    }
}
