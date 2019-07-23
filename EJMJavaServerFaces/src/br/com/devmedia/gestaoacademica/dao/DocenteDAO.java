package br.com.devmedia.gestaoacademica.dao;

import java.util.List;

import br.com.devmedia.gestaoacademica.model.Docente;

public interface DocenteDAO {

	public void adicionarDocente(Docente docente);

	public List<Docente> listarDocentes();

	public void excluirDocente(Docente docente);
}
