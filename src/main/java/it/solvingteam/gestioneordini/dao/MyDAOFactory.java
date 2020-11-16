package it.solvingteam.gestioneordini.dao;

import it.solvingteam.gestioneordini.dao.articolo.ArticoloDAO;
import it.solvingteam.gestioneordini.dao.articolo.ArticoloDAOImpl;
import it.solvingteam.gestioneordini.dao.categoria.CategoriaDAO;
import it.solvingteam.gestioneordini.dao.categoria.CategoriaDAOImpl;
import it.solvingteam.gestioneordini.dao.ordine.OrdineDAO;
import it.solvingteam.gestioneordini.dao.ordine.OrdineDAOImpl;

public class MyDAOFactory {

	// rendiamo questo factory SINGLETON
	private static ArticoloDAO ARTICOLO_DAO_INSTANCE = null;
	private static OrdineDAO ORDINE_DAO_INSTANCE = null;
	private static CategoriaDAO CATEGORIA_DAO_INSTANCE = null;

	public static ArticoloDAO getArticoloDAOInstance() {
		if (ARTICOLO_DAO_INSTANCE == null)
			ARTICOLO_DAO_INSTANCE = new ArticoloDAOImpl();
		return ARTICOLO_DAO_INSTANCE;
	}

	public static OrdineDAO getOrdineDAOInstance() {
		if (ORDINE_DAO_INSTANCE == null)
			ORDINE_DAO_INSTANCE = new OrdineDAOImpl();
		return ORDINE_DAO_INSTANCE;
	}
	
	public static CategoriaDAO getCategoriaDAOInstance() {
		if (CATEGORIA_DAO_INSTANCE == null)
			CATEGORIA_DAO_INSTANCE = new CategoriaDAOImpl();
		return CATEGORIA_DAO_INSTANCE;
	}

}
