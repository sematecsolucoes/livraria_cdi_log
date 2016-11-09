package br.com.sematec.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sematec.livraria.dao.AutorDAO;
import br.com.sematec.livraria.log.Log;
import br.com.sematec.livraria.modelo.Autor;
import br.com.sematec.livraria.tx.Transacional;

@Named
@ViewScoped
public class AutorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Autor autor = new Autor();
	private Integer autorId;

	@Inject
	private AutorDAO dao;

	@Log
	public void carregarAutorPelaId() {
		autor = dao.buscaPorId(autorId);
	}

	@Log
	public Autor getAutor() {
		return autor;
	}

	@Log
	public List<Autor> getAutores() {
		return dao.listaTodos();
	}

	@Log
	public Integer getAutorId() {
		return autorId;
	}

	@Log
	@Transacional
	public String gravar() {
		System.out.println("Gravando autor " + autor.getNome());
		if (autor.getId() == null) {
			dao.adiciona(autor);
		} else {
			dao.atualiza(autor);
		}
		autor = new Autor();
		return "livro?faces-redirect=true";
	}

	@Log
	@Transacional
	public void remover(Autor autor) {
		System.out.println("Removendo autor " + autor.getNome());
		dao.remove(autor);
	}

	@Log
	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@Log
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
}
