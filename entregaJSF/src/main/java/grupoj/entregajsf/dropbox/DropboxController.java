/*
 * Grupo Jota
 * juanpedrodb.96@uma.es
 * grupoJGanadores
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.dropbox;

// Include the Dropbox SDK.
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.UploadUploader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanp
 */
public class DropboxController {
    private static final String ACCES_TOKEN = "wTke8zhajSAAAAAAAAAAB8mSOg7lS8jNiqVv2mL9ei0YQtjMzUfwgr5-ezK_6Ol2";
    private static DbxClientV2 client = new DbxClientV2(new DbxRequestConfig("aplicacionEmpresarial"), DropboxController.ACCES_TOKEN);
    
    public static boolean uploadFile(String nombre, byte[] content) {
        
        try (InputStream in = new ByteArrayInputStream(content)) {
            
            UploadUploader up = client.files().upload(nombre);
            up.uploadAndFinish(in);
            
        } catch (IOException ex) {
            Logger.getLogger(DropboxController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (DbxException ex) {
            Logger.getLogger(DropboxController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public static byte[] downloadFile(String nombre) {
        byte[] res = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            
            client.files().download(nombre).download(out);
            res = out.toByteArray();
            
        } catch (IOException ex) {
            Logger.getLogger(DropboxController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (DbxException ex) {
            Logger.getLogger(DropboxController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return res;
    }
}
