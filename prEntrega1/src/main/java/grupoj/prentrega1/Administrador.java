/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.prentrega1;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author user1
 */
@Entity
public class Administrador extends Periodista {

    @Column( name = "identificador", nullable = false)
    private Long identificador;

    // Relacion "recibir" de la entidad Administrador con la entidad Mensaje
    @ManyToMany
    @JoinTable(name = "jnd_recibir_mensaje",
            joinColumns = @JoinColumn(name = "administrador_fk"),
            inverseJoinColumns = @JoinColumn(name = "mensaje_fk"))
    private List<Mensaje> recibirMensaje;
    
    // Relacion "recibir" de la entidad Administrador con la entidad Usuario
    @ManyToMany
    @JoinTable(name = "jnd_es_gestionado",
            joinColumns = @JoinColumn(name = "administrador_fk"),
            inverseJoinColumns = @JoinColumn(name = "usuario_fk"))
    private List<Usuario> esGestionado;

    @OneToMany(mappedBy = "admin")
    private List<Anuncio> anuncios_by;




    public List<Anuncio> getAnuncios_by() {
        return anuncios_by;
    }

    public void setAnuncios_by(List<Anuncio> anuncios_by) {
        this.anuncios_by = anuncios_by;
    }
    
    public List<Mensaje> getRecibirMensaje() {
        return recibirMensaje;
    }

    public void setRecibirMensaje(List<Mensaje> recibirMensaje) {
        this.recibirMensaje = recibirMensaje;
    }

    public List<Usuario> getEsGestionado() {
        return esGestionado;
    }

    public void setEsGestionado(List<Usuario> esGestionado) {
        this.esGestionado = esGestionado;
    }
    
    
    
    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    
    
}
