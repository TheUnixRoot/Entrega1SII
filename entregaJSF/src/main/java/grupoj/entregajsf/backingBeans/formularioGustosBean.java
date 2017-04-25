/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author JesusAlberto
 */

@ManagedBean
public class formularioGustosBean {

       
    private String[] selectedGustos;
    private List<String> gustos;
 
    @PostConstruct
    public void init() {
       gustos = new ArrayList<>();
       gustos.add("Musica");
       gustos.add("Teatro");
       gustos.add("Opera");
       gustos.add("Cine");
       gustos.add("Arte");
       gustos.add("Deportes");
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
}
