/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.entregajsf.controlSesion.ControlAutorizacion;
import grupoj.prentrega1.Notificacion;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;

/**
 *
 * @author juanp
 */
@Named(value = "ver_notificacionesBean")
@RequestScoped
public class Ver_notificacionesBean {

    @Inject
    ControlAutorizacion controlAutorizacion;
    @Inject
    PersistenceMock persistencia;
    
    /**
     * Creates a new instance of Ver_notificacionesBean
     */
    public Ver_notificacionesBean() {
    }

    public ControlAutorizacion getControlAutorizacion() {
        return controlAutorizacion;
    }

    public void setControlAutorizacion(ControlAutorizacion controlAutorizacion) {
        this.controlAutorizacion = controlAutorizacion;
    }

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }
    
    public List<Notificacion> getNotificaciones() {
        List<Notificacion> list = persistencia.getListaUsuarios()
                .get(persistencia.getListaUsuarios().indexOf(controlAutorizacion.getUsuario())
).getNotificaciones();
        return list;
    }
    
}
