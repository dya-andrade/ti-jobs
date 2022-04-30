package br.com.tijobs.util;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import org.omnifaces.util.Messages;


@Named
@ApplicationScoped
public class Message {
	
    public static void addDetailMessage(String message, FacesMessage.Severity severity) {

        FacesMessage facesMessage = Messages.create("").detail(message).get();
        if (severity != null && severity != FacesMessage.SEVERITY_INFO) {
            facesMessage.setSeverity(severity);
        }
        Messages.add(null, facesMessage);
    }

}
