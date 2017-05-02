/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Formulario;
import grupoj.prentrega1.Tag;
import grupoj.prentrega1.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
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
    private Usuario user;
    private List<Tag> tags;
    private Formulario formulario;

    public formularioGustosBean() {
        
        this.persistencia = new PersistenceMock();
        //this.user = persistencia.getListaUsuarios().get(0);//usuario de prueba
    }
 
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
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        //this.setId(Long.parseLong(params.get("id")));
        Usuario uuu = new Usuario();
        uuu.setId(Long.parseLong(params.get("id")));
        System.out.println(Long.parseLong(params.get("id")));
        
         if ( this.persistencia.getListaUsuarios().contains(uuu) ) {
             this.user = this.persistencia.getListaUsuarios().get(this.persistencia.getListaUsuarios().indexOf(uuu));
         }else{
              this.user = null;
         }
           
        uuu = null;
        
        List<Tag> l = new ArrayList<>();
        this.formulario = new Formulario();
        this.formulario.setId(Long.MIN_VALUE);
        formulario.setUsuario(this.user);
        long i = 0;
              
        for(String gusto : this.selectedGustos){
            Tag tag= new Tag();
            tag.setId(i);
            tag.setTexto(gusto);
            tag.setForm(this.formulario);
            //System.out.println(tag.getTexto());
            l.add(tag);
            i++;
        }
       
        this.formulario.setForm_tags(l);
        this.user.setForm(this.formulario);
        
    }
    
    public String goIndex(){
        return "index.xhtml";
    }
}