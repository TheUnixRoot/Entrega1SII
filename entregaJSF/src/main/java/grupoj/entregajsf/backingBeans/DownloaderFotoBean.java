/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entregajsf.dropbox.DropboxController;
import grupoj.entregajsf.dropbox.DropboxControllerException;
import grupoj.prentrega1.Usuario;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author juanp
 */
@Named(value = "downloaderFotoBean")
@ViewScoped
public class DownloaderFotoBean implements Serializable{

    @Inject
    PersistenceMock persistencia;
    private Iterator<Usuario> it;
    
    public DownloaderFotoBean()  {
    }
    
    public StreamedContent generar() {
        StreamedContent con = null;
        try {
            if(it == null) {
                it = persistencia.getListaUsuarios().iterator();
            } else if (it.hasNext()) {
                String mul = it.next().getMultimedia();
                if (mul == null) mul = "/default.jpg";
                con = new DefaultStreamedContent(new ByteArrayInputStream(DropboxController.downloadFile(mul))); 
            }
        } catch (DropboxControllerException dbex) {
            try {
                con = new DefaultStreamedContent(new ByteArrayInputStream(DropboxController.downloadFile("/default.jpg")));
            } catch (DropboxControllerException ex) {
                Logger.getLogger(Crud_usuariosBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IndexOutOfBoundsException ie) {
            Logger.getLogger(Crud_usuariosBean.class.getName()).log(Level.SEVERE, null, ie);
        }
        return con;
    }
    
}
