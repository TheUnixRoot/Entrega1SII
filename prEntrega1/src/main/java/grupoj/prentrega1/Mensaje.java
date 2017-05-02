/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.prentrega1;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author anaes
 */
@Entity
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String asunto;
    private String texto;
    
    // Relacion Bidireccional Mensaje <-> Usuario
    @ManyToOne
    @JoinColumn(name = "envia_mensaje", nullable = false)
    private Usuario enviadoPor;

    // Relacion Bidireccional Mensaje <-> Administrador
    @ManyToMany(mappedBy = "recibirMensaje")
    private List<Administrador> recibidoPor;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    
    
    public Usuario getEnviadoPor() {
        return enviadoPor;
    }

    public void setEnviadoPor(Usuario enviadoPor) {
        this.enviadoPor = enviadoPor;
    }

    public List<Administrador> getRecibidoPor() {
        return recibidoPor;
    }

    public void setRecibidoPor(List<Administrador> recibidoPor) {
        this.recibidoPor = recibidoPor;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupoj.prentrega1.Mensaje[ id=" + id + " ]";
    }
    
}
