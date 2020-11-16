package it.solvingteam.gestioneordini.dao.categoria;

import java.util.List;

import it.solvingteam.gestioneordini.dao.IBaseDAO;
import it.solvingteam.gestioneordini.model.Categoria;

public interface CategoriaDAO extends IBaseDAO<Categoria>{
	
	public List <Categoria> findAllCategorieOrdine (Long IdOrdine) throws Exception;

}
