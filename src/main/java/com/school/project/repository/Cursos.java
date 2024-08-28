package com.school.project.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.school.project.model.Curso;

public class Cursos implements Serializable{

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Cursos() {

    }

    public Cursos(EntityManager manager) {
        this.manager = manager;
    }

    public List<Curso> pesquisar(String descricao) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        CriteriaQuery<Curso> criteriaQuery = criteriaBuilder.createQuery(Curso.class);

        Root<Curso> root = criteriaQuery.from(Curso.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.like(root.get("descricao").as(String.class), descricao + "%"));

        TypedQuery<Curso> query = manager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    public List<Curso> todos() {
        TypedQuery<Curso> query = manager.createQuery("from Curso", Curso.class);
        return query.getResultList();
    }

    public Curso guardar(Curso curso) {
        return manager.merge(curso);
    }

    public void remover(Curso curso) {
        curso = manager.find(Curso.class, curso.getId());
        manager.remove(curso);
    }
    
    
    
}
