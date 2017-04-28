/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.TipoNotificacion;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;

/**
 *
 * @author David
 * Ahora mismo falta de todo.... 
 * Mirar la configuración que tiene el usuario, cargarla y despues
 * enviar en el caso de que haya cambios
 */
@Named(value = "configurarNotificaciones")
@Dependent
public class configurarNotificaciones {
    @Inject
    private PersistenceMock persistencia;
    private boolean notificacionesActivas;
    private TipoNotificacion tipoNotUsuario;
    private List<String> ListaNotif;

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public boolean isNotificacionesActivas() {
        return notificacionesActivas;
    }

    public void setNotificacionesActivas(boolean notificacionesActivas) {
        this.notificacionesActivas = notificacionesActivas;
    }

    public TipoNotificacion getTipoNotUsuario() {
        return tipoNotUsuario;
    }

    public void setTipoNotUsuario(TipoNotificacion tipoNotUsuario) {
        this.tipoNotUsuario = tipoNotUsuario;
    }

    public List<String> getListaNotif() {
        return ListaNotif;
    }

    public void setListaNotif(List<String> ListaNotif) {
        this.ListaNotif = ListaNotif;
    }
    
    @PostConstruct
    public void init() {
        tipoNotUsuario = persistencia.getTipoNotUsuario();
        ListaNotif = new ArrayList<>();
        rellenaLista();
    }

    private void insertaLista(TipoNotificacion n) {
        switch(n) {
            case Email: 
                ListaNotif.add("Email");
                break;
                
            case Cuenta: 
                ListaNotif.add("Cuenta");
                break; 
                
            case Ambos: 
                ListaNotif.add("Ambos");
                break;
            
                
        }
    }
    
    private void rellenaLista () {
        if (tipoNotUsuario == TipoNotificacion.Desactivado) {
            notificacionesActivas = false;
        } else {
            notificacionesActivas = true;
        }
        
        insertaLista(tipoNotUsuario);
        if (tipoNotUsuario != TipoNotificacion.Email) {
            insertaLista(TipoNotificacion.Email);
        }
        
        if (tipoNotUsuario != TipoNotificacion.Cuenta) {
            insertaLista(TipoNotificacion.Cuenta);
        }
        
        if (tipoNotUsuario != TipoNotificacion.Ambos) {
            insertaLista(TipoNotificacion.Ambos);
        }
    }
    
}
