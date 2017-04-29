/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import java.util.*;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import mockingBeans.PersistenceMock;
import grupoj.prentrega1.*;

/**
 *
 * @author anaes
 */
@Named(value = "buscarEvento")
@RequestScoped
public class buscarEvento {

    /**
     * Creates a new instance of buscarEvento
     */
    public buscarEvento() {
        selectedTipoEvento = null;
        selectedLugares = null;
        fecha1 = null;
        fecha2 = null;
    }
    
       
    private String[] selectedTipoEvento;
    private List<String> tiposEvento;
    private List<String> lugares;
    private Date fecha1;
    private Date fecha2;
    private int precio1;
    private int precio2;
    private String[] selectedLugares;
    private PersistenceMock persistencia = new PersistenceMock();
    private List<Evento> listaEventos;
    
    @PostConstruct
    public void init() {
       tiposEvento = new ArrayList<>();
       tiposEvento.add("Música");
       tiposEvento.add("Teatro");
       tiposEvento.add("Ópera");
       tiposEvento.add("Cine");
       tiposEvento.add("Arte");
       tiposEvento.add("Deportes");
       lugares = new ArrayList<>();
       lugares.add("Málaga");
       lugares.add("Antequera");
       lugares.add("Mijas");
       lugares.add("Villanueva del Rosario");
    }

    public String[] getSelectedTipoEvento() {
        return selectedTipoEvento;
    }
 
    public void  setSelectedTipoEvento(String[] selectedTipoEvento) {
        this.selectedTipoEvento = selectedTipoEvento;
    }
 
    public List<String> getTiposEvento() {
        return tiposEvento;
    }
    public List<String> getLugares() {
        return lugares;
    }
    
    public void setFecha1(Date fecha1){
        this.fecha1=fecha1;
    }
    public void setFecha2(Date fecha2){
        this.fecha2=fecha2;
    }
    public Date getFecha1(){
        return fecha1;
    }
    public Date getFecha2(){
        return fecha2;
    }
    
    public void setSelectedLugares(String[] lugar){
        this.selectedLugares = lugar;
    }
    
    public String[] getSelectedLugares(){
        if(selectedLugares == null){
            return null;
        }
        return selectedLugares;
    }

    public int getPrecio1() {
        return precio1;
    }

    public void setPrecio1(int precio1) {
        this.precio1 = precio1;
    }

    public int getPrecio2() {
        return precio2;
    }

    public void setPrecio2(int precio2) {
        this.precio2 = precio2;
    }
    
    public String buscar(){
        listaEventos = persistencia.getListaEventos();
        List<Evento> listaCoincidencias = new ArrayList<Evento>();
        for(Evento e : listaEventos){
            
        }
        
        return "index.html";
    }
}
