/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author JesusAlberto
 */

@ManagedBean
public class enviarNotificacionesBean {

    /**
     * Creates a new instance of enviarNotificacionesBean
     */
    public enviarNotificacionesBean() {
    }
    
    
    private String[] selectedGustos;
    private List<String> gustos;

    private String[] selectedFecha;
    private List<Date> dates;
    private List<String> fechas;
    @PostConstruct
    public void init() {
       gustos = new ArrayList<>();
       gustos.add("Musica");
       gustos.add("Teatro");
       gustos.add("Opera");
       gustos.add("Cine");
       gustos.add("Arte");
       gustos.add("Deportes");
       
       fechas = new ArrayList<>();
       fechas.add("Mañana");
       fechas.add("Esta semana");
       fechas.add("Este mes");
    }

    public List<String> getFechas() {
        return fechas;
    }

    public void setFechas(List<String> fechas) {
        this.fechas = fechas;
    }

    public String[] getSelectedFecha() {
        return selectedFecha;
    }

    public void setSelectedFecha(String[] selectedFecha) {
        this.selectedFecha = selectedFecha;
    }

    
    
    public String[] getSelectedGustos() {
        return selectedGustos;
    }

    public void setSelectedGustos(String[] selectedGustos) {
        this.selectedGustos = selectedGustos;
    }

    public List<String> getGustos() {
        return gustos;
    }

    public void setGustos(List<String> gustos) {
        this.gustos = gustos;
    }
 
     
}
