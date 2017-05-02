/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.controlSesion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author juanp
 */
@FacesValidator("passValidator")
public class PassValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = value.toString();
        Pattern patron = Pattern.compile("[a-zA-Z0-9]+?@[a-zA-Z0-9]+?\".\"(com|es|org|co\".\"uk|net)");
        Matcher m = patron.matcher(email);
        if (!m.matches())
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las Contraseñas no coinciden", null)
            );
    }
    
}
