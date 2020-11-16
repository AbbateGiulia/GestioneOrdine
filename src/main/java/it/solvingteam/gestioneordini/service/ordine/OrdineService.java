package it.solvingteam.gestioneordini.service.ordine;

import java.util.List;

import it.solvingteam.gestioneordini.dao.ordine.OrdineDAO;
import it.solvingteam.gestioneordini.model.Articolo;
import it.solvingteam.gestioneordini.model.Ordine;


public interface OrdineService{
	
	public List<Ordine> listAll() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Ordine ordineInstance) throws Exception;

	public void inserisciNuovo(Ordine ordineInstance) throws Exception;

	public void rimuovi(Ordine ordineInstance) throws Exception;
	
	public void InserisciArticoloInOrdine( Ordine ordineInstance, Articolo articoloInstance) throws Exception;
	
	public List<Ordine> cercaOrdiniPerCategoria(Long IdCategoria) throws Exception;
	

	
	// per injection
		public void setOrdineDAO(OrdineDAO ordineDAO);


}
