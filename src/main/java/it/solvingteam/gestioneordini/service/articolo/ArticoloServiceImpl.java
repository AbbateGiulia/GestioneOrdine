package it.solvingteam.gestioneordini.service.articolo;

import java.util.List;

import javax.persistence.EntityManager;

import it.solvingteam.gestioneordini.dao.EntityManagerUtil;
import it.solvingteam.gestioneordini.dao.articolo.ArticoloDAO;
import it.solvingteam.gestioneordini.model.Articolo;
import it.solvingteam.gestioneordini.model.Categoria;


public class ArticoloServiceImpl implements ArticoloService{
	
	private ArticoloDAO articoloDAO;

	@Override
	public List<Articolo> listAll() throws Exception {
		try {
			// uso l'injection per il dao
			articoloDAO.setEntityManager(EntityManagerUtil.getEntityManager());

			// eseguo quello che realmente devo fare
			return articoloDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Articolo caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// uso l'injection per il dao
					articoloDAO.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					return articoloDAO.get(id);

				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} finally {
					entityManager.close();
				}
	}

	@Override
	public void aggiorna(Articolo articoloInstance) throws Exception {
		// questo Ã¨ come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		if(articoloInstance.getOrdine()!= null) {
			System.out.println("non puoi modificare un articolo già ordinato");
		}

		try {
			// questo Ã¨ come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			articoloDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			articoloDAO.update(articoloInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void inserisciNuovo(Articolo articoloInstance) throws Exception {
		
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// questo è come il MyConnection.getConnection()
					entityManager.getTransaction().begin();

					// uso l'injection per il dao
					articoloDAO.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					articoloDAO.insert(articoloInstance);

					entityManager.getTransaction().commit();
				} catch (Exception e) {
					entityManager.getTransaction().rollback();
					e.printStackTrace();
					throw e;
				}
		
	}

	@Override
	public void rimuovi(Articolo articoloInstance) throws Exception {
		
		if(articoloInstance.getOrdine()!= null) {
			System.out.println("non puoi rimuovere un articolo già ordinato");
		}
		// questo Ã¨ come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo Ã¨ come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			articoloDAO.setEntityManager(entityManager);			

			// eseguo quello che realmente devo fare
			articoloDAO.delete(articoloInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void setArticoloDAO(ArticoloDAO articoloDAO) {
		this.articoloDAO=articoloDAO;
		
	}

	@Override
	public Double totaleCostoArticoliCategoria(Long IdCategoria) throws Exception {
		try {
			// uso l'injection per il dao
			articoloDAO.setEntityManager(EntityManagerUtil.getEntityManager());

			// eseguo quello che realmente devo fare
			return articoloDAO.totaleCostoArticoliCategoria(IdCategoria);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void aggiungiCategoriaAArticolo(Articolo articoloInstance, Categoria categoriaInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			articoloDAO.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// se articolo gia prensente riconosciuto e non inserito
			categoriaInstance = entityManager.merge(categoriaInstance);
			articoloInstance = entityManager.merge(articoloInstance);
					
			
			//aggiungo articolo al set articoli di categoria
			categoriaInstance.getArticoli().add(articoloInstance);
			articoloInstance.getCategorie().add(categoriaInstance);
			
			
			for (Articolo a: categoriaInstance.getArticoli()) {
				System.out.println( "articolo aggiunto" + a.getDescrizione());
			}
			

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		
	}



}
