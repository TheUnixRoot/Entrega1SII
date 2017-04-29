/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Formulario;
import grupoj.prentrega1.Notificacion;
import grupoj.prentrega1.Tag;
import grupoj.prentrega1.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import mockingBeans.PersistenceMock;


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
    
     private PersistenceMock persistencia = new PersistenceMock();
    
    private String[] selectedGustos;
    private List<String> gustos;

    private String[] selectedFecha;
    //private List<Date> dates;
    private List<String> fechas;
    
    private List<Evento> eventos;
    private Evento selectedEvento;
    
    private List<Usuario> selectedUsuarios = new ArrayList();
    private Notificacion notificacion;
    
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
    
    public void notificacion(){
        //Obtenemos los usuarios interesados en el evento y los tags asociados al mismo
        List<Tag> tags = this.selectedEvento.getTagged_by();
        List<Usuario> interesados = this.selectedEvento.getInteresados_at();
        
        //Obtenemos la lista completa de usuarios
        List<Usuario> usuarios = persistencia.getListaUsuarios();
        
        for(Usuario u : usuarios){
            //Para cada usuario obtenemos su formulario y de ahi sus gustos
            Formulario form = u.getForm();
            List<Tag> tagsU = form.getForm_tags();
            
            //Dados los gustos del usuario los comparamos con los tags del evento: posible interesado
            ListIterator<Tag> it = tags.listIterator();
            ListIterator<Tag> it2 = tagsU.listIterator();
            boolean encontrado = false;
   
            while(it.hasNext() && !encontrado){
                 Tag t = it.next();
                while(it2.hasNext() && !encontrado){
                    Tag t2 = it2.next();
                    if(t.getTexto().equals(t2.getTexto())){
                        //Si coincide algun tag añadimos el usuario a la lista y cortamos los bucles
                        selectedUsuarios.add(u);
                        encontrado = true;
                    }
                } 
            }
            //Añadimos directamente a todos los interesados en dicho evento
            for(Usuario interesado : interesados){
                selectedUsuarios.add(interesado);
            }
        }
        
        notificacion = new Notificacion();
        //falta añadir cosas a la notificacion
        
        for(Usuario selected : selectedUsuarios){
            selected.getNotificaciones().add(notificacion);
        }
    }
     
}
