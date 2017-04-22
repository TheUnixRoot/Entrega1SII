/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author JesusAlberto
 */
@Named(value = "contactoAdminBean")
@Dependent
public class contactoAdminBean {

    /**
     * Creates a new instance of contactoAdminBean
     */
    public contactoAdminBean() {
    }
    
    public void buttonAction(ActionEvent actionEvent) {
        addMessage("¡Mensaje enviado!");
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
