package com.school.project.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.school.project.model.Aluno;

public class Alunos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Alunos() {
		
	}
	
	public Alunos(EntityManager manager) {
		this.manager = manager;
	}
	
	public Aluno porId(Long id) {
		return manager.find(Aluno.class, id);
	}
	
	public List<Aluno> pesquisar(String nome){ //Pesquisa dentro da table aluno um nome digitado
		TypedQuery<Aluno> query = manager
				.createQuery("from Aluno where nome like :nome", Aluno.class);
		query.setParameter("nome", nome + "%");
		
		return query.getResultList();
	}
	
	public List<Aluno> todos(){ 
		return manager.createQuery("from Aluno", Aluno.class).getResultList();
	}
	
	public Aluno guardar(Aluno aluno) {
		return manager.merge(aluno);
	}
	
	public void remover(Aluno aluno) {
		aluno = porId(aluno.getId());
		manager.remove(aluno);
	}
}
