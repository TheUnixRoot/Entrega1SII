

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;
import grupoj.prentrega1.Geolocalizacion;
import grupoj.prentrega1.Lugar;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;

/**
 *
 * @author Migue
 */
@Named(value = "crearLugarBean")
@RequestScoped
public class CrearLugarBean {

    /**
     * Creates a new instance 
     * 
     * 
     */
    @Inject
    private PersistenceMock persistencia;
    private List<Lugar> lugares;
    
    private String nombre;
    private String descripcion;
    private boolean borrado;
    private String fotos;
    private String direccion;
    private String ciudad;
    

    private UIComponent enviar;
    
    public CrearLugarBean() {
        persistencia = new PersistenceMock();
        lugares = persistencia.getListaLugares();

       
    }
    
    public String insertarLugar() throws InterruptedException{
      if(existeLugar(nombre)){
          
      
      FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Lugar ya existente en la base de datos","Lugar ya existente en la base de datos");
      FacesContext.getCurrentInstance().addMessage("mensaje", fm);
      
      return null;
      }
      else{
      
      
      Lugar l=new Lugar();
      l.setNombre(nombre);
      l.setDescripcion(descripcion);
      l.setBorrado(false);
      l.setFotos(fotos);
      Geolocalizacion g = new Geolocalizacion();
      g.setDireccion(direccion);
      g.setCiudad(ciudad);
      l.setGeolocalizacion(g);
      lugares.add(l);
      persistencia.setListaLugares(lugares);

      return "gestion_lugar.xhtml";
      }
     
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Iterable<Lugar> getLugares() {
        return lugares;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    private boolean existeLugar(String nombre){
    
    boolean b=false;
    for(Lugar i : lugares){
        if(i.getNombre().equals(nombre)){
        b=true;
        }
    }
    return b;
    }
    


    
    
    
}
