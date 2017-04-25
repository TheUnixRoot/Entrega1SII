/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author JesusAlberto
 */
@Named(value = "formularioGustosBean")
@RequestScoped
public class formularioGustosBean {

    /**
     * Creates a new instance of formularioGustosBean
     */
    public formularioGustosBean() {
    }
    
    
    private String[] selectedGustos;
    private List<String> gustos;
 
 
    @PostConstruct
    public void init() {
        gustos = new ArrayList<String>();
        gustos.add("Musica");
        gustos.add("Teatro");
        gustos.add("Cine");
        gustos.add("Opera");
        gustos.add("Deportes");
        
 
    }
 
 
    public String[] getSelectedGustos() {
        return selectedGustos;
    }
 
    public void setSelectedGustos(String[] selectedGustos) {
        this.selectedGustos = selectedGustos;
    }
 
   
    
 
    public List<String> getGustos() {
        //System.out.println(selectedGustos[0]);
        return gustos;
    }
 
   
    
}
