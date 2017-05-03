/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;



import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Lugar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;


/**
 *
 * @author migue
 */
@Named(value = "eliminarEvento")
@RequestScoped
public class EliminarEvento {

    @Inject
    private PersistenceMock persistencia;
    private String valor;
    private List<Evento> listaEventos;
    private Evento adv;
    private Evento a;
    
    @PostConstruct
    public void init() {
       
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaLugares(List<Evento> listaEventos) throws InterruptedException{
        //this.listaLugares = listaLugares;
        persistencia.setListaEventos(listaEventos);
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Evento getAdv() {
        return adv;
    }

    public void setAdv(Evento adv) {
        this.adv = adv;
    }
    
    
    public String eliminarEvento() throws InterruptedException{
    
     if(valor.equals("si")){
     
     }else{
     
     }
    return "gestionar_Evento.xhtml";
    }
    
    
}