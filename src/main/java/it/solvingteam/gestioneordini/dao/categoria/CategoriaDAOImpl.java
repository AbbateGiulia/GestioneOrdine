package it.solvingteam.gestioneordini.dao.categoria;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.solvingteam.gestioneordini.model.Categoria;


public class CategoriaDAOImpl implements CategoriaDAO{
	
	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;
		
	}
	
	@Override
	public List<Categoria> list() throws Exception {
		return entityManager.createQuery("from Categoria", Categoria.class).getResultList();
	}

	@Override
	public Categoria get(Long id) throws Exception {
		return entityManager.find(Categoria.class, id);
	}

	@Override
	public void update(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}

		categoriaInstance = entityManager.merge(categoriaInstance);
		
	}

	@Override
	public void insert(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(categoriaInstance);
		
	}

	@Override
	public void delete(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.remove(entityManager.merge(categoriaInstance));
		
	}

	//2.	Voglio tutte le categorie degli articoli di un determinato ordine;
	
	@Override
	public List<Categoria> findAllCategorieOrdine(Long IdOrdine) throws Exception {
		TypedQuery<Categoria> query = entityManager.createQuery(" select distinct c from Categoria c join c.articoli a join  a.ordine o where o.id=?1", Categoria.class);
		return query.setParameter(1, IdOrdine).getResultList();
	}
	
	

}
