package com.school.project.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.school.project.model.Curso;
import com.school.project.model.Professor;
import com.school.project.model.TipoCurso;
import com.school.project.repository.Cursos;
import com.school.project.repository.Professores;
import com.school.project.service.CadastroProfessorService;

@Named
@ViewScoped
public class GestaoProfessoresBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Professores professores;

	@Inject
	private Cursos cursos;

	@Inject
	private CadastroProfessorService cadastroProfessorService;

	private List<Professor> listaProfessores;
	private String termoPesquisa;
	private Converter cursoConverter;
	private Professor professor;

	public void prepararNovoProfessor() {
		professor = new Professor();
	}

	public void prepararEdicao() {
		cursoConverter = new CursoConverter(Arrays.asList(professor.getCurso()));
	}

	public void salvar() {
		cadastroProfessorService.salvar(professor);

		atualizarRegistros();
		
		PrimeFaces.current().ajax().update(Arrays.asList("frm:profDataTable", "frm:messages"));

	}

	public void excluir() {
		cadastroProfessorService.excluir(professor);

		professor = null;

		atualizarRegistros();

	}

	public void pesquisar() {
		listaProfessores = professores.pesquisar(termoPesquisa);
	}

	public void todosProfessores() {
		listaProfessores = professores.todas();
	}

	public List<Curso> completarCurso(String termo) {
		List<Curso> listaCursos = cursos.pesquisar(termo);
		cursoConverter = new CursoConverter(listaCursos);
		return listaCursos;
	}

	private void atualizarRegistros() {
		if (jaHouvePesquisa()) {
			pesquisar();
		} else {
			todosProfessores();
		}
	}

	private boolean jaHouvePesquisa() {
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}

	public List<Professor> getListaProfessores() {
		return listaProfessores;
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

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public boolean isProfessorSelecionado() {
		return professor != null && professor.getId() != null;
	}
}
