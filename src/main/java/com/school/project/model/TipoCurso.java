package com.school.project.model;

public enum TipoCurso {
	
	Fundamental("Ensino Fundamental"),
	EM("Ensino Médio"),
	Preparatorio("Curso Preparatório");
	
	private String descricao;
	
	TipoCurso(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
