package com.school.project.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.school.project.model.Aluno;
import com.school.project.model.Curso;
import com.school.project.model.TipoCurso;
import com.school.project.repository.Alunos;
import com.school.project.repository.Cursos;
import com.school.project.service.CadastroProfessorService;

@Named
@ViewScoped
public class GestaoAlunosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Alunos alunos;
		
	@Inject
	private Cursos cursos;
	
	@Inject
	private CadastroProfessorService cadastroProfessorService;
	
	private List<Aluno> listaAlunos;
	
	private String termoPesquisa;
	
	private Converter cursoConverter;
	
	private Aluno aluno;
	
	public void preparaNovoAluno() {
		aluno = new Aluno();
	}
	
	public void preparaEdicao() {
	    cursoConverter = new CursoConverter(aluno.getCursos());
	}

	
	public void salvar() {
		cadastroProfessorService.salvar(aluno);
		
		atualizarRegistros();
				
		PrimeFaces.current().ajax().update(Arrays.asList("frm:alunosDataTable", "frm:messages"));
	}
	
	public void excluir() {
		cadastroProfessorService.excluir(aluno);
		
		aluno = null;
		
		atualizarRegistros();
		

	}
	
	public void pesquisar() {
		listaAlunos = alunos.pesquisar(termoPesquisa);
		
	}
	
	public void todosAlunos() {
		listaAlunos = alunos.todos();
	}
	
	public List<Curso> completarCurso(String termo) {
		List<Curso> listaCursos = cursos.pesquisar(termo);
		
		cursoConverter = new CursoConverter(listaCursos);
		
		return listaCursos;
	}
	
	private void atualizarRegistros() {
		if (jaHouvePesquisa()) {
			pesquisar();
		}else {
			todosAlunos();
		}
	}
	
	private boolean jaHouvePesquisa() {
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}
	
	public List<Aluno> getListaAlunos() {
		return listaAlunos;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	
	public TipoCurso[] getTiposCurso() {
		return TipoCurso.values();
	}
	
	public Converter getCursoConverter() {
		return cursoConverter;
	}
	
	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public boolean isAlunoSelecionado() {
		
		return aluno != null && aluno.getId() != null;
		
	}
}
