/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import mockingBeans.PersistenceMock;


/**
 *
 * @author JesusAlberto
 */

@ManagedBean
public class formularioGustosBean {

    private PersistenceMock persistencia;
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
    
    public void saveGustos(){
        List<String> l = new ArrayList<>();
         System.out.println(this.selectedGustos[0]);
  
        int i = 0;
        while(this.selectedGustos[i] != null){
            l.add(this.selectedGustos[i]);
            i++;
        }
      
        persistencia.setListaGustos(l);
        System.out.println("Todo bien todo correcto y yo que me alegro");
    }
}
