/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Evento;
import grupoj.prentrega1.Lugar;
import grupoj.prentrega1.Usuario;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Migue
 */
@Named(value = "crearEventoBean")
@RequestScoped
public class CrearEventoBean {

    /**
     * Creates a new instance 
     * 
     * 
     */
    @Inject
    private PersistenceMock persistencia;
    private List<Lugar> lugares;
    private List<Evento> eventos;
    private String nombre;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Date hora;
    private boolean validado;
    private String descripcion;
    private double precio;
    private String donde_comprar;
    private boolean borrado;
    private String ocurre_in;
    private Usuario subido_by;
    private String borrad;
    private UIComponent enviar;
    UploadedFile file;
    
    public CrearEventoBean() {
        persistencia = new PersistenceMock();
        lugares = persistencia.getListaLugares();
        eventos = persistencia.getListaEventos();
       
    }
    
    public String insertarEvento() throws InterruptedException{
      if(existeEvento(nombre)){
          
      
      FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Evento ya existente en la base de datos","Lugar ya existente en la base de datos");
      FacesContext.getCurrentInstance().addMessage("mensaje", fm);
      
      return null;
      }
      else{
      
      Evento e=new Evento();
      fecha_inicio.setHours(hora.getHours());
      fecha_inicio.setMinutes(hora.getMinutes());
      e.setNombre(nombre);
      e.setPrecio(precio);
      e.setDonde_comprar(donde_comprar);
      e.setFecha_inicio(fecha_inicio);
      e.setFecha_fin(fecha_fin);
      e.setDescripcion(descripcion);
      e.setOcurre_in(buscarLugar(ocurre_in));
      e.setBorrado(false);
      e.setMultimedia(file.getContents());
      //e.setSubido_by(subido_by);
      //e.setValidado(validado);
      eventos.add(e);
      
      persistencia.setListaEventos(eventos);
      return "gestion_evento.xhtml";
      }
     
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDonde_comprar() {
        return donde_comprar;
    }

    public void setDonde_comprar(String donde_comprar) {
        this.donde_comprar = donde_comprar;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public String getOcurre_in() {
        return ocurre_in;
    }

    public void setOcurre_in(String ocurre_in) {
        this.ocurre_in = ocurre_in;
    }

    public Usuario getSubido_by() {
        return subido_by;
    }

    public void setSubido_by(Usuario subido_by) {
        this.subido_by = subido_by;
    }

    public List<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    



    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public UIComponent getEnviar() {
        return enviar;
    }

    public void setEnviar(UIComponent enviar) {
        this.enviar = enviar;
    }

    public String getBorrad() {
        return borrad;
    }

    public void setBorrad(String borrad) {
        this.borrad = borrad;
    }

    
    

    private boolean existeEvento(String nombre){
    
    boolean b=false;
    for(Evento i : eventos){
        if(i.getNombre().equals(nombre)){
        b=true;
        }
    }
    return b;
    }
    
    private Lugar buscarLugar(String o){
    
    Lugar lg = new Lugar();
     for(Lugar l : lugares){
         if(l.getNombre().equals(o)){
            lg=l;
        } 
     }
     return lg;
    }

    

    
    
    
}
