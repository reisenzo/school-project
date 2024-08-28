package com.school.project.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.school.project.model.Professor;

public class Professores implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Professores() {
		
	}
	
	public Professores(EntityManager manager) {
		this.manager = manager;
	}
	
	public Professor porId(Long id) {
		return manager.find(Professor.class, id);
	}
	
	public List<Professor> pesquisar(String nome){ //Pesquisa dentro da table prof um nome digitado
		TypedQuery<Professor> query = manager
				.createQuery("from Professor where nome like :nome", Professor.class);
		query.setParameter("nome", nome + "%");
		
		return query.getResultList();
	}
	
	public List<Professor> todas(){
		return manager.createQuery("from Professor", Professor.class).getResultList();
	}
	
	public Professor guardar(Professor professor) {
		return manager.merge(professor);
	}
	
	public void remover(Professor professor) {
		professor = porId(professor.getId());
		manager.remove(professor);
	}
}
