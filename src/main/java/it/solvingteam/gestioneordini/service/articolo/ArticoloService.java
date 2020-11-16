package it.solvingteam.gestioneordini.service.articolo;

import java.util.List;

import it.solvingteam.gestioneordini.dao.articolo.ArticoloDAO;
import it.solvingteam.gestioneordini.model.Articolo;
import it.solvingteam.gestioneordini.model.Categoria;



public interface ArticoloService {
	
	public List<Articolo> listAll() throws Exception;

	public Articolo caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Articolo articoloInstance) throws Exception;

	public void inserisciNuovo(Articolo articoloInstance) throws Exception;

	public void rimuovi(Articolo articoloInstance) throws Exception;
	
	public void aggiungiCategoriaAArticolo(Articolo articoloInstance, Categoria categoriaInstance) throws Exception;
	
	public Double totaleCostoArticoliCategoria(Long IdCategoria) throws Exception;
	
	// per injection
		public void setArticoloDAO(ArticoloDAO articoloDAO);

}
