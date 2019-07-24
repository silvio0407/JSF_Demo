package br.com.devmedia.gestaoacademica.control;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.devmedia.gestaoacademica.dao.DocenteDAO;
import br.com.devmedia.gestaoacademica.dao.DocenteDAOImpl;
import br.com.devmedia.gestaoacademica.model.Docente;

@ManagedBean(name = "docenteController")
@SessionScoped
public class DocenteController {

	private Docente docente;
	private DataModel<Docente> listaDocentes;
	private String msg;

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public DataModel<Docente> getListaDocentes() {

		DocenteDAO dao = new DocenteDAOImpl();
		List<Docente> lista = populaListaDocente();//dao.listarDocentes();
		listaDocentes = new ListDataModel<Docente>(lista);
		return listaDocentes;
	}
	
	private List<Docente> populaListaDocente(){
		Docente doc = new Docente();
		doc.setId(1);
		doc.setNome("TEste");
		doc.setMatricula("123445678");
		doc.setTitulacao("Teste");
		
		List<Docente> listaDocente = new ArrayList<>();
		listaDocente.add(doc);
		
		return listaDocente;
	}

	public void setListaDocentes(DataModel<Docente> listaDocentes) {
		this.listaDocentes = listaDocentes;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String adicionarForm() {
		docente = new Docente();

		return "inserir_docente_form";
	}

	public String adicionarDocente() {

		DocenteDAO dao = new DocenteDAOImpl();
		dao.adicionarDocente(docente);

		setMsg("Salvo com sucesso!");
		return "inserir_docente_form";
	}

	public String excluirDocente() {

		Docente d = (Docente) (listaDocentes.getRowData());
		DocenteDAO dao = new DocenteDAOImpl();
		dao.excluirDocente(d);

		setMsg("Excluído com sucesso!");
		return "listar_docentes";

	}

	public String listarForm() {

		return "listar_docentes";
	}

	@PostConstruct
	public void init() {
		docente = new Docente();
	}
}
