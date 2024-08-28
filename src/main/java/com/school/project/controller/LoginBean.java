package com.school.project.controller;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
public class LoginBean {
    private String username;
    private String password;
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

        if ("admin".equals(username) && "admin".equals(password)) {
            session.setAttribute("user", username);
            session.setAttribute("tipoUsuario", "admin");
            context.getExternalContext().redirect("restricted/Index.xhtml");
        } else if ("aluno".equals(username) && "aluno".equals(password)) {
            session.setAttribute("user", username);
            session.setAttribute("tipoUsuario", "aluno");
            context.getExternalContext().redirect("restricted/Index.xhtml");
        } else if ("prof".equals(username) && "prof".equals(password)) {
            session.setAttribute("user", username);
            session.setAttribute("tipoUsuario", "professor");
            context.getExternalContext().redirect("restricted/Index.xhtml");
        } else {
            context.addMessage(null, new javax.faces.application.FacesMessage("Invalid credentials"));
        }
    }

    public String getTipoUsuario() {
        return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tipoUsuario");
    }

    public void logout() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        context.getExternalContext().redirect("Login.xhtml");
    }
}
