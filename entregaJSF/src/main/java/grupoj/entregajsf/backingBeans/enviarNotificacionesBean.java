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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

 
    
    private PersistenceMock persistencia;
    
    private List<String> selectedGustos;
    private List<String> gustos;

    private List<String> selectedFecha;
    //private List<Date> dates;
    private List<String> fechas;
    
    private List<Evento> listaCoincidencias;
    private Evento selectedEvento;
    
    private List<Usuario> selectedUsuarios;
    private Notificacion notificacion;
    private List<Evento> listaEventos;
    
    private boolean vacio;
    
       /**
     * Creates a new instance of enviarNotificacionesBean
     */
    public enviarNotificacionesBean() {
        this.vacio = true;
        
        this.listaCoincidencias = new ArrayList();
        this.selectedUsuarios = new ArrayList();
        this.persistencia = new PersistenceMock();
        this.listaEventos = persistencia.getListaEventos();
    }
    
    @PostConstruct
    public void init() {
       gustos = new ArrayList<>();
       gustos.add("Música");
       gustos.add("Teatro");
       gustos.add("Opera");
       gustos.add("Cine");
       gustos.add("Arte");
       gustos.add("Deportes");
       
       fechas = new ArrayList<>();
       fechas.add("Hoy");
       fechas.add("Mañana");
       fechas.add("Proximos 7 dias");
      
    }

    public List<String> getFechas() {
        return fechas;
    }

    public void setFechas(List<String> fechas) {
        this.fechas = fechas;
    }

    public List<String> getSelectedFecha() {
        return selectedFecha;
    }

    public void setSelectedFecha(List<String> selectedFecha) {
        this.selectedFecha = selectedFecha;
    }
    
    public List<String> getSelectedGustos() {
        return selectedGustos;
    }

    public void setSelectedGustos(List<String> selectedGustos) {
        this.selectedGustos = selectedGustos;
    }

    public List<String> getGustos() {
        return gustos;
    }

    public void setGustos(List<String> gustos) {
        this.gustos = gustos;
    }

    public List<Evento> getListaCoincidencias() {
        return listaCoincidencias;
    }

    public void setListaCoincidencias(List<Evento> listaCoincidencias) {
        this.listaCoincidencias = listaCoincidencias;
    }

    public boolean isVacio() {
        return vacio;
    }

    public void setVacio(boolean vacio) {
        this.vacio = vacio;
    }
    
    
    
    
    public String filtrar(){
        
        System.out.println(listaEventos.get(0)+"papas");
        boolean coincide = false;
        boolean tip=false, f1=false;
        if(!selectedGustos.isEmpty()) tip=true;     
        if(!selectedFecha.isEmpty()) f1=true;
             // selectedFecha.
         for(Evento e : listaEventos){
            System.out.println(e+"papas2");
            coincide = false;
            if(tip){
                for(String s : selectedGustos){
                    System.out.println(e+"papas3");
                    for(Tag t : e.getTagged_by()){
                        if(t.getTexto().equals(s)){
                            coincide = true;
                            System.out.println(e+"papas4");
                        }
                    }
                }
            }else{
                coincide=true;
            }
            if(coincide){
                coincide=false;
                if(f1){
                    System.out.println(e+"papas5");
                    Date hoy;
                    Date mañana;
                    Date semana;
                    DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    if(selectedFecha.get(0).equals("Hoy")){

                        System.out.println(e+"papas6");
                        hoy = new Date();
                        /*System.out.println(formato.format(hoy)); 
                        System.out.println(formato.format(e.getFecha()));
                        System.out.println(hoy); 
                        System.out.println(e.getFecha());*/
                        Calendar c = Calendar.getInstance(); 
                        c.setTime(hoy); 
                        int day = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
                        //System.out.println(day);
                        c.setTime(e.getFecha()); 
                        int day2 = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
                        //System.out.println(day);
                        if(day == day2){
                            listaCoincidencias.add(e);
                            this.vacio=false;
                            System.out.println(e+"papas7");
                        }

                    }else if(selectedFecha.get(0).equals("Mañana")){
                        
                        mañana = new Date();
                        Calendar c = Calendar.getInstance(); 
                        //c.setTime(mañana); 
                        //c.add(Calendar.DATE, 1);
                        int day = c.get(Calendar.DAY_OF_WEEK_IN_MONTH)+1;
                        System.out.println(c.getTime());
                        
                        //Calendar c2 = Calendar.getInstance(); 
                        c.setTime(e.getFecha()); 
                        int day2 = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
                        System.out.println(c.getTime());
                        
                        /*System.out.println(mañana);
                        System.out.println(day);
                        System.out.println(day2);*/
                        if(day == day2){
                            listaCoincidencias.add(e);
                            this.vacio=false;
                            System.out.println(e+"papas8");
                        }

                    }else if(selectedFecha.get(0).equals("Proximos 7 dias")){
                        
                        mañana = new Date();
                        Calendar c = Calendar.getInstance(); 
                        //c.setTime(mañana); 
                        //c.add(Calendar.DATE, 1);
                        int day = c.get(Calendar.DAY_OF_WEEK_IN_MONTH)+1;    

                        semana = new Date();
                        //c.setTime(semana); 
                       // c.add(Calendar.DATE, 7);
                        
                        int day2 = c.get(Calendar.DAY_OF_WEEK_IN_MONTH)+7;
                        
                        //c.setTime(e.getFecha()); 
                        int day3 = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
                        System.out.println(day);
                        System.out.println(day2);
                        System.out.println(day3);

                        if(day3 >= day && day3 <= day2){
                            listaCoincidencias.add(e);
                            this.vacio=false;
                            System.out.println(e+"papas9");
                        }
                    }
                        
                }else{
                    listaCoincidencias.add(e);
                    this.vacio=false;
                }
                    
            }
        }
        return "enviarNotificacionesRes";
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
    
    public String volver(){
        return "enviarNotificaciones.xhtml";
    }
     
}
