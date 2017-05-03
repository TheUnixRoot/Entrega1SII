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
        
    }
    
       
    private String[] selectedTipoEvento = new String[50];
    private List<String> tiposEvento;
    private List<String> lugares;
    private Date fecha1;
    private Date fecha2;
    private int precio1;
    private int precio2;
    private String[] selectedLugares = new String[50];
    private PersistenceMock persistencia = new PersistenceMock();
    private List<Evento> listaEventos;
    private List<Evento> listaCoincidencias = new ArrayList<Evento>();
    private boolean tip=false, lug=false, f1=false,f2=false,p2=false;
    
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
       selectedTipoEvento = new String[50];
       selectedLugares = new String[50];
       fecha1 = null;
       fecha2 = null;
       precio1 = 0;
       precio2 = 0;
    }

    public String[] getSelectedTipoEvento() {
        if(selectedTipoEvento == null){
            return null;
        }
        
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

    public List<Evento> getListaCoincidencias() {
        return listaCoincidencias;
    }

    public void setListaCoincidencias(List<Evento> listaCoincidencias) {
        this.listaCoincidencias = listaCoincidencias;
    }
    
    public String buscar(){
        listaEventos = persistencia.getListaEventos();
        boolean coincide = false;
        
        
        if(this.getSelectedTipoEvento() != null) tip=true;     
        if(this.getSelectedLugares() != null) lug=true;
        if(fecha1!=null) f1=true;
        if(fecha2!=null) f2=true;
        if(precio2 !=0) p2=true;
        
        for(Evento e : listaEventos){
            coincide = false;
            if(tip){
                if(e.getTagged_by() != null && !e.getTagged_by().isEmpty()){
                    for(String s : selectedTipoEvento){     
                        for(Tag t : e.getTagged_by()){
                            if(t.getTexto().equals(s)){
                                coincide = true;
                                
                            }
                        }
                    }
                }else{
                    coincide=true;
                }
                
            }else{
                coincide=true;
            }
            if(coincide){
                coincide = false;
                
                    if(f1 && f2){
                        if( e.getFecha().after(fecha1)&&e.getFecha().before(fecha2) //Eliminar esta condicion y poner las siguientes:
                                /*(e.getFecha1().after(fecha1) && e.getFecha2().after(fecha2)) ||
                                (e.getFecha1().after(fecha1) && e.getFecha2().before(fecha2)) ||
                                (e.getFecha1().before(fecha1) && e.getFecha2().before(fecha2)) ||
                                (e.getFecha1().before(fecha1) && e.getFecha2().before(fecha2))*/
                                ){
                           coincide=true;
                        }
                    }else{
                        coincide=true;
                    }
                    if(coincide){
                        if(p2){
                            if((e.getPrecio()>=precio1) && (e.getPrecio() <=precio2)){
                                listaCoincidencias.add(e);
                            }
                        }else{
                            listaCoincidencias.add(e);
                        }
                    }
                }
            }
        
        
        return "resultadoBuscarEvento.xhtml";
    }
}
