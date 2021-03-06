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
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
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
    private List<String> fechas;
    
    private List<Evento> listaCoincidencias;
    private String selectedEventoS;
    private Evento selectedEvento;
    
    private List<Usuario> selectedUsuarios;
    private Notificacion notificacion;
    private List<Evento> listaEventos;
    
    private String notificacionPersonalizada;
    
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

    public String getSelectedEventoS() {
        return selectedEventoS;
    }

    public void setSelectedEventoS(String selectedEventoS) {
        this.selectedEventoS = selectedEventoS;
    }
    
    

    public Evento getSelectedEvento() {
        return selectedEvento;
    }

    public void setSelectedEvento(Evento selectedEvento) {
        this.selectedEvento = selectedEvento;
    }

    
    
    public String getNotificacionPersonalizada() {
        return notificacionPersonalizada;
    }

    public void setNotificacionPersonalizada(String notificacionPersonalizada) {
        this.notificacionPersonalizada = notificacionPersonalizada;
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
        
        boolean coincide = false;
        boolean tip=false, f1=false;
        if(!selectedGustos.isEmpty()) tip=true;     
        if(!selectedFecha.isEmpty()) f1=true;
            
         for(Evento e : listaEventos){
           
            coincide = false;
            if(tip){
                for(String s : selectedGustos){
                    
                    for(Tag t : e.getTagged_by()){
                        if(t.getTexto().equals(s)){
                            coincide = true;
                            
                        }
                    }
                }
            }else{
                coincide=true;
            }
            if(coincide){
                coincide=false;
                if(f1){
                  
                    Date hoy;
                    Date mañana;
                    Date semana;
                    DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    if(selectedFecha.get(0).equals("Hoy")){

                       
                        hoy = new Date();
                       
                        Calendar c = Calendar.getInstance(); 
                        c.setTime(hoy); 
                        int day = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
                      
                        c.setTime(e.getFecha_inicio());
                        int init = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
                        
                        c.setTime(e.getFecha_fin());
                        int end = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
                      
                        if(day >=init && day <=end){
                            listaCoincidencias.add(e);
                            this.vacio=false;
                          
                        }

                    }else if(selectedFecha.get(0).equals("Mañana")){
                        
                        mañana = new Date();
                        Calendar c = Calendar.getInstance(); 
                        
                        int day = c.get(Calendar.DAY_OF_WEEK_IN_MONTH)+1;
                       
                        
                       
                        c.setTime(e.getFecha_inicio());
                        int init = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
                        
                        c.setTime(e.getFecha_fin());
                        int end = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
                        
                      
                        if(day >=init && day <=end){
                            listaCoincidencias.add(e);
                            this.vacio=false;
                            
                        }

                    }else if(selectedFecha.get(0).equals("Proximos 7 dias")){
                        
                        mañana = new Date();
                        Calendar c = Calendar.getInstance(); 
                       
                        int day = c.get(Calendar.DAY_OF_WEEK_IN_MONTH)+1;    

                        semana = new Date();

                        int day2 = c.get(Calendar.DAY_OF_WEEK_IN_MONTH)+7;

                        c.setTime(e.getFecha_inicio());
                        int init = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
                        
                        c.setTime(e.getFecha_fin());
                        int end = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);

                        if((day>=init && day<=end) || (day2>=init && day2<=end)){
                            listaCoincidencias.add(e);
                            this.vacio=false;
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
    
    public void seleccionaUsuarios(){
        
              
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
    }
    
    public void notificacion(int n){
        notificacion = new Notificacion();
        notificacion.setId(Long.MIN_VALUE);
        if(n==0){
            notificacion.setContenido("Hay un evento proximo que te puede interesar");
        }else{
            notificacion.setContenido(notificacionPersonalizada);
        }
        
        notificacion.setFecha(new Date());
    }
    
    public void enviaNotificacion(int n){
        
        /*Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String s =  params.get("evento");
        System.out.println(s);
        Long l = Long.parseLong(s);*/
        System.out.println(selectedEventoS);
        for(Evento e : listaCoincidencias){
            if(e.getNombre().equals(selectedEventoS)){
                this.selectedEvento=e;
            }
        }
         
        seleccionaUsuarios();
        if(n==0){
            notificacion(0);
        }else{
            notificacion(1);
        }

        for(Usuario selected : selectedUsuarios){
            selected.getNotificaciones().add(notificacion);

        }

    }
    
    public String editarNotificacion(){
      
        /* Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
         String s =  params.get("evento2");
        
         Long l = Long.parseLong(s);
         for(Evento e : listaCoincidencias){
             if(e.getId().equals(l)){
                 this.selectedEvento=e;
             }
         }*/
        return "editarNotificacion.xhtml";
    }
    
    public String volver(){
        return "enviarNotificaciones.xhtml";
    }

}

