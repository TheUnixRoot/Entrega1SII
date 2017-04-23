/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Anuncio;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;

/**
 *
 * @author juanp
 */
@Named(value = "crud_anunciosBean")
@Dependent
public class Crud_anunciosBean {

    @Inject
    private PersistenceMock persistencia;
    private Iterable<Anuncio> anuncios;
    /**
     * Creates a new instance of Crud_anunciosBean
     */
    public Crud_anunciosBean() {
        anuncios = persistencia.getListaAnuncios();
    }

    public Iterable<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(Iterable<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }
    
}
