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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author anaes
 */
@Entity
public class Lugar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private boolean borrado;
    private String descripcion;
    private String fotos;
    
    // Relacion ocurre Bidireccional Evento <-> Lugar
    @OneToMany(mappedBy = "ocurre_in")
    private List<Evento> ocurren_at;
    
    // Relacion Bidireccional Lugar <-> Geolocalizacion
    @OneToOne
    @JoinColumn(name = "tiene_geolocalizacion", nullable = false)
    private Geolocalizacion geolocalizacion;

    // Relacion Bidireccional Lugar <-> Periodista
    @ManyToMany(mappedBy = "gestionarLugar")
    private List<Periodista> gestionadoPor;

    // Relacion recibe Bidireccional Valoracion_lug <-> Lugar
    @OneToMany(mappedBy = "valoracion_sobre")
    private List<Valoracion_lug> valoraciones_sobre;
    
    
    
    
    public Geolocalizacion getGeolocalizacion() {
        return geolocalizacion;
    }

    public void setGeolocalizacion(Geolocalizacion geolocalizacion) {
        this.geolocalizacion = geolocalizacion;
    }

    public List<Periodista> getGestionadoPor() {
        return gestionadoPor;
    }

    public void setGestionadoPor(List<Periodista> gestionadoPor) {
        this.gestionadoPor = gestionadoPor;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lugar)) {
            return false;
        }
        Lugar other = (Lugar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupoj.prentrega1.Lugar[ id=" + id + " ]";
    }
    
}
