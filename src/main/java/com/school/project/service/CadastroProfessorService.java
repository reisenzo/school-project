package com.school.project.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.school.project.model.Aluno;
import com.school.project.model.Professor;
import com.school.project.repository.Alunos;
import com.school.project.repository.Professores;
import com.school.project.util.Transacional;

public class CadastroProfessorService  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Apenas para criação de professores, dois metodos reutilizados
	
	@Inject
	private Professores professores;
	
	@Transacional
	public void salvar(Professor professor) {
		professores.guardar(professor);
	}
	
	@Transacional
	public void excluir(Professor professor) {
		professores.remover(professor);
	}
	
	@Inject
	private Alunos alunos;
	
	@Transacional
	public void salvar(Aluno aluno) {
		alunos.guardar(aluno);
	}
	
	@Transacional
	public void excluir(Aluno aluno) {
		alunos.remover(aluno);
	}
	
	
}
