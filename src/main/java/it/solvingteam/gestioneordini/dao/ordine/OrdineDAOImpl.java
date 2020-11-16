package it.solvingteam.gestioneordini.dao.ordine;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.solvingteam.gestioneordini.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {
	
	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager=entityManager;
		
	}

	@Override
	public List<Ordine> list() throws Exception {
		return entityManager.createQuery("from Ordine", Ordine.class).getResultList();
	}

	@Override
	public Ordine get(Long id) throws Exception {
		return entityManager.find(Ordine.class, id);
	}

	@Override
	public void update(Ordine ordineInstance) throws Exception {
		if (ordineInstance == null) {
			throw new Exception("Problema valore in input");
		}

		ordineInstance = entityManager.merge(ordineInstance);
		
	}

	@Override
	public void insert(Ordine ordineInstance) throws Exception {
		if (ordineInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(ordineInstance);
		
	}

	@Override
	public void delete(Ordine ordineInstance) throws Exception {
		if (ordineInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.remove(entityManager.merge(ordineInstance));
		
	}
	
//1.	Voglio tutti gli ordini effettuati per una determinata categoria;
	
	public List<Ordine> findAllOrdiniByCategoria(Long categoriaId) throws Exception {
		TypedQuery<Ordine> query = entityManager.createQuery(" select distinct o from Ordine o join o.articoli a join a.categorie c where c.id=?1", Ordine.class);
		return query.setParameter(1, categoriaId).getResultList();
	}



}
