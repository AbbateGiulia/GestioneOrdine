package it.solvingteam.gestioneordini.dao.articolo;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;

import it.solvingteam.gestioneordini.model.Articolo;


public class ArticoloDAOImpl implements ArticoloDAO {
	
	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;
		
	}

	@Override
	public List<Articolo> list() throws Exception {
		return entityManager.createQuery("from Articolo", Articolo.class).getResultList();
	}

	@Override
	public Articolo get(Long id) throws Exception {
		return entityManager.find(Articolo.class, id);
	}

	@Override
	public void update(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("Problema valore in input");
		}

		articoloInstance = entityManager.merge(articoloInstance);
		
	}

	@Override
	public void insert(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(articoloInstance);

		
	}

	@Override
	public void delete(Articolo articoloInstance) throws Exception {
		if (articoloInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.remove(entityManager.merge(articoloInstance));
		
	}

	@Override
	public Double totaleCostoArticoliCategoria(Long IdCategoria) throws Exception {
		TypedQuery<Double> query= entityManager.createQuery("select sum (a.prezzoSingolo) from Articolo a join a.categorie c where c.id=?1", Double.class);
		 query.setParameter(1, IdCategoria);
		 Double totale = query.getSingleResult();
		 return totale;
	
	}


}
