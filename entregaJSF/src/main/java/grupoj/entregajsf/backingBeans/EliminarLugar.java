/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;



import grupoj.prentrega1.Lugar;
import grupoj.prentrega1.Usuario;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;


/**
 *
 * @author migue
 */
@Named(value = "eliminarLugar")
@RequestScoped
public class EliminarLugar {

    @Inject
    private PersistenceMock persistencia;
    private String valor;
    private List<Lugar> listaLugares;
    private Lugar adv;
    private Lugar a;
    
    @PostConstruct
    public void init() {
       
    }

    public List<Lugar> getListaLugares() {
        return listaLugares;
    }

    public void setListaLugares(List<Lugar> listaLugares) throws InterruptedException{
        //this.listaLugares = listaLugares;
        persistencia.setListaLugares(listaLugares);
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Lugar getAdv() {
        return adv;
    }

    public void setAdv(Lugar adv) {
        this.adv = adv;
    }
    
    
    public String eliminarLugar() throws InterruptedException{
    
     
    return "gestionar_Lugar.xhtml";
    }
    
    
}