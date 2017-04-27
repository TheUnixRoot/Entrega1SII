/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Formulario;
import grupoj.prentrega1.Tag;
import grupoj.prentrega1.TipoNotificacion;
import grupoj.prentrega1.Usuario;
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

    private PersistenceMock persistencia = new PersistenceMock();
    private String[] selectedGustos;
    private List<String> gustos;
    private Usuario user = persistencia.getListaUsuarios().get(0);//usuario de prueba
    private List<Tag> tags;
    private Formulario formulario;
 
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
        List<Tag> l = new ArrayList<>();
        //System.out.println(this.selectedGustos[0]);
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
        //System.out.println(l);
        this.formulario.setForm_tags(l);
        //System.out.println(this.formulario);
        //System.out.println(this.formulario.getForm_tags().get(0).getTexto());
        persistencia.setFormulario(this.formulario);
        //System.out.println("Todo bien todo correcto y yo que me alegro");
    }
}
