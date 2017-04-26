/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

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

    private boolean notificacionesActivas;
    private String tipoNotificaciones;
    
    public boolean isNotificacionesActivas() {
        return notificacionesActivas;
    }

    public void setNotificacionesActivas(boolean notificacionesActivas) {
        this.notificacionesActivas = notificacionesActivas;
    }

    public String getTipoNotificaciones() {
        return tipoNotificaciones;
    }

    public void setTipoNotificaciones(String tipoNotificaciones) {
        this.tipoNotificaciones = tipoNotificaciones;
    }
    
}
