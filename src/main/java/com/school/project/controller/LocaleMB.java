package com.school.project.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

@ManagedBean(name="localeMB")
@SessionScoped
public class LocaleMB implements Serializable {
    private static final long serialVersionUID = 1L;
    private Locale currentLocale = new Locale.Builder().setLanguage("pt").setRegion("BR").build();
    
    public void englishLocale() {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        currentLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
        viewRoot.setLocale(currentLocale);
    }
    
    public void portugueseLocale() {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        currentLocale = new Locale.Builder().setLanguage("pt").setRegion("BR").build();
        viewRoot.setLocale(currentLocale);
    }
    
    public Locale getCurrentLocale() {
        return currentLocale;
    }
}

