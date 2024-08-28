package com.school.project.controller;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.school.project.model.Curso;

public class CursoConverter implements Converter {

	private List<Curso> listaCursos;
	
    // Construtor para List<Curso>
    public CursoConverter(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }

	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		
		Long id = Long.valueOf(value);
		
		for(Curso curso: listaCursos) {
			if (id.equals(curso.getId())) {
				return curso;
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null) {
			return null;			
		}
		
		Curso curso = (Curso) value;
		
		return curso.getId().toString();
	}

}
