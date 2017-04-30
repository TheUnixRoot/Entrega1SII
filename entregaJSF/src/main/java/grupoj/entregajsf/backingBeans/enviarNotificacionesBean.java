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
    
    private String[] selectedGustos;
    private List<String> gustos;

    private List<String> selectedFecha;
    //private List<Date> dates;
    private List<String> fechas;
    
    private List<Evento> listaCoincidencias;
    private Evento selectedEvento;
    
    private List<Usuario> selectedUsuarios;
    private Notificacion notificacion;
    private List<Evento> listaEventos;
    
    private boolean recarga;
    
       /**
     * Creates a new instance of enviarNotificacionesBean
     */
    public enviarNotificacionesBean() {
        this.recarga = false;
        
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

    public List<Evento> getListaCoincidencias() {
        return listaCoincidencias;
    }

    public void setListaCoincidencias(List<Evento> listaCoincidencias) {
        this.listaCoincidencias = listaCoincidencias;
    }

    public boolean isRecarga() {
        return recarga;
    }

    public void setRecarga(boolean recarga) {
        this.recarga = recarga;
    }
    
    
    
    
    public String filtrar(){
        
        System.out.println(listaEventos.get(0)+"papas");
        boolean coincide = false;
        boolean tip=false, f1=false;
        if(selectedGustos[0] != null) tip=true;     
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
                        System.out.println(formato.format(hoy)); 
                        System.out.println(formato.format(e.getFecha()));
                       // System.out.println(hoy); 
                        //System.out.println(e.getFecha());
                        //hoy.equals(hoy);
                        if(e.getFecha().equals(hoy)){
                            listaCoincidencias.add(e);
                            System.out.println(e+"papas7");
                        }

                    }else if(selectedFecha.get(0).equals("Mañana")){
                        
                        mañana = new Date();
                        Calendar c = Calendar.getInstance(); 
                        c.setTime(mañana); 
                        c.add(Calendar.DATE, 1);
                        mañana = c.getTime();
                        System.out.println(formato.format(mañana)); 

                        if(e.getFecha().equals(mañana)){
                            listaCoincidencias.add(e);
                        }

                    }else if(selectedFecha.get(0).equals("Proximos 7 dias")){
                        
                        mañana = new Date();
                        Calendar c = Calendar.getInstance(); 
                        c.setTime(mañana); 
                        c.add(Calendar.DATE, 1);
                        mañana = c.getTime();
                        System.out.println(formato.format(mañana));                             

                        semana = new Date();
                        c.setTime(semana); 
                        c.add(Calendar.DATE, 7);
                        semana = c.getTime();
                        System.out.println(formato.format(semana)); 

                        if(e.getFecha().after(mañana)&&e.getFecha().before(semana)){
                            listaCoincidencias.add(e);
                        }
                    }
                        
                }else{
                    listaCoincidencias.add(e);
                }
                    
            }
        }
         System.out.println(listaCoincidencias.get(0));
         this.recarga=true;
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
     
}
