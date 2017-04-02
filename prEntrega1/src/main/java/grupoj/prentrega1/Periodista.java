/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.prentrega1;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author user1
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Periodista extends Usuario {

    @Column( name = "seccion", nullable = false)
    private String seccion;
    @Column( name = "puesto", nullable = false)
    private String puesto;

    // Relacion "gestionar" de la entidad Periodista con la entidad Notificacion
    @ManyToMany
    @JoinTable(name = "jnd_gestionarNotificacion",
            joinColumns = @JoinColumn(name = "periodista_fk"),
            inverseJoinColumns = @JoinColumn(name = "notificacion_fk"))
    private List<Notificacion> gestionarNotificacion;
    
    // Relacion "gestionar" de la entidad Periodista con la entidad Lugar
    @ManyToMany
    @JoinTable(name = "jnd_gestionarLugar",
            joinColumns = @JoinColumn(name = "periodista_fk"),
            inverseJoinColumns = @JoinColumn(name = "lugar_fk"))
    private List<Lugar> gestionarLugar;
    
    // Relacion "gestionar" de la entidad Periodista con la entidad Evento
    @ManyToMany
    @JoinTable(name = "jnd_gestionarEvento",
            joinColumns = @JoinColumn(name = "periodista_fk"),
            inverseJoinColumns = @JoinColumn(name = "evento_fk"))
    private List<Evento> gestionarEvento;
    
    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    
}
