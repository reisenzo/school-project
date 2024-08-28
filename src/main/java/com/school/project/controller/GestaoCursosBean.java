package com.school.project.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.PrimeFaces;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.school.project.model.Aluno;
import com.school.project.model.Curso;
import com.school.project.model.Professor;
import com.school.project.repository.Cursos;
import com.school.project.service.CadastroCursoService;
import com.school.project.util.FacesMessages;

@Named
@ViewScoped
public class GestaoCursosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Cursos cursos;

	@Inject
	private FacesMessages messages;

	@Inject
	private CadastroCursoService cadastroCursoService;

	private List<Curso> listaCursos;

	private String termoPesquisa;

	private Curso curso;

	public void preparaNovoCurso() {
		curso = new Curso();
	}

	public void preparaEdicao() {
		@SuppressWarnings("unused")
		CursoConverter cursoConverter = new CursoConverter(Arrays.asList(curso));
	}

	public void salvar() {
		try {
			if (curso.getId() == null) {
				cadastroCursoService.salvar(curso);
			} else {
				cadastroCursoService.salvar(curso);
			}
			atualizarRegistros();
			PrimeFaces.current().ajax().update(Arrays.asList("frm:cursosDataTable", "frm:messages"));
			PrimeFaces.current().executeScript("PF('cursoDialogWidgetVar').hide()"); // Correção do script de fechamento
		} catch (Exception e) {
			messages.info(e.getMessage());
		}
	}

	public void excluir() {
		cadastroCursoService.excluir(curso);

		curso = null;

		atualizarRegistros();

	}

	public void pesquisar() {
		listaCursos = cursos.pesquisar(termoPesquisa);

	}

	public void todosCursos() {
		listaCursos = cursos.todos();
	}

	private void atualizarRegistros() {
		if (jaHouvePesquisa()) {
			pesquisar();
		} else {
			todosCursos();
		}
	}

	private boolean jaHouvePesquisa() {
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public boolean isCursoSelecionado() {
		return curso != null && curso.getId() != null;
	}

	public void downloadPDF() {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

	    try {
	        Document document = new Document();
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment;filename=cursos.pdf");
	        OutputStream out = response.getOutputStream();
	        PdfWriter.getInstance(document, out);
	        document.open();

	        for (Curso curso : getListaCursos()) {
	            document.add(new Paragraph("Curso: " + curso.getDescricao()));

	            Professor professor = curso.getProfessor();
	            String professorNome = (professor != null) ? professor.getNome() : "N/A";
	            document.add(new Paragraph("Professor: " + professorNome));

	            List<Aluno> alunos = curso.getAlunos();
	            StringBuilder alunosStr = new StringBuilder("Alunos: ");
	            if (alunos != null && !alunos.isEmpty()) {
	                for (Aluno aluno : alunos) {
	                    alunosStr.append(aluno.getNome()).append(", ");
	                }
	                if (alunosStr.length() > 0) {
	                    alunosStr.setLength(alunosStr.length() - 2); // Remove a última vírgula e espaço
	                }
	            } else {
	                alunosStr.append("N/A");
	            }
	            document.add(new Paragraph(alunosStr.toString()));
	            document.add(new Paragraph("\n"));
	        }

	        document.close();
	        out.flush();
	        out.close();
	        facesContext.responseComplete();
	    } catch (DocumentException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
