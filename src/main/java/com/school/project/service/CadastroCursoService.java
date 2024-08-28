package com.school.project.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.school.project.model.Curso;
import com.school.project.repository.Cursos;
import com.school.project.util.Transacional;

public class CadastroCursoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Cursos cursos;

    @Transacional
    public void salvar(Curso curso) {
        cursos.guardar(curso);
    }

    @Transacional
    public void excluir(Curso curso) {
        cursos.remover(curso);
    }
}
