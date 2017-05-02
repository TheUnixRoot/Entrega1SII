/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.backingBeans;

import grupoj.prentrega1.Usuario;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import mockingBeans.PersistenceMock;

/**
 *
 * @author juanp
 */
@Named(value = "signupBean")
@RequestScoped
public class SignupBean {
    
    @Inject
    PersistenceMock persistencia;
    Usuario usuario;
    String pass1;
    String pass2;
    String email;
    
    /**
     * Creates a new instance of SignupBean
     */
    public SignupBean() {
        usuario = new Usuario();
    }

    public PersistenceMock getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(PersistenceMock persistencia) {
        this.persistencia = persistencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void validatePassword(ComponentSystemEvent event) {

	  FacesContext fc = FacesContext.getCurrentInstance();

	  UIComponent components = event.getComponent();

	  if (pass1.isEmpty() || pass2.isEmpty()) {
		return;
	  }

	  if (!pass1.equals(pass2)) {

		FacesMessage msg = new FacesMessage("Password must match confirm password");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                UIInput uiInputPassword = (UIInput) components.findComponent("password");
	  	fc.addMessage(uiInputPassword.getClientId(), msg);
		fc.renderResponse();

	  }

    }
    
}
