package it.solvingteam.gestioneordini.service.categoria;

import java.util.List;

import javax.persistence.EntityManager;

import it.solvingteam.gestioneordini.dao.EntityManagerUtil;
import it.solvingteam.gestioneordini.dao.categoria.CategoriaDAO;
import it.solvingteam.gestioneordini.model.Articolo;
import it.solvingteam.gestioneordini.model.Categoria;

public class CategoriaServiceImpl implements CategoriaService {
	
	private CategoriaDAO categoriaDAO;

	@Override
	public List<Categoria> listAll() throws Exception {
		try {
			// uso l'injection per il dao
			categoriaDAO.setEntityManager(EntityManagerUtil.getEntityManager());

			// eseguo quello che realmente devo fare
			return categoriaDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Categoria caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// uso l'injection per il dao
					categoriaDAO.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					return categoriaDAO.get(id);

				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} finally {
					entityManager.close();
				}
	}

	@Override
	public void aggiorna(Categoria categoriaInstance) throws Exception {
		
		for(Categoria c: categoriaDAO.list()) {
			if(c.equals(categoriaInstance)) {
				System.out.println("categoria già esistente");
				return;
			}
		}
		// questo Ã¨ come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo Ã¨ come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			categoriaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			categoriaDAO.update(categoriaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}

		
	}

	@Override
	public void inserisciNuovo(Categoria categoriaInstance) throws Exception {
		
		for(Categoria c: categoriaDAO.list()) {
			if(c.equals(categoriaInstance)) {
				System.out.println("categoria esistente");
				return;
			}
		}
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// questo è come il MyConnection.getConnection()
					entityManager.getTransaction().begin();

					// uso l'injection per il dao
					categoriaDAO.setEntityManager(entityManager);
					

					// eseguo quello che realmente devo fare
					categoriaDAO.insert(categoriaInstance);

					entityManager.getTransaction().commit();
				} catch (Exception e) {
					entityManager.getTransaction().rollback();
					e.printStackTrace();
					throw e;
				}
		
	}

	@Override
	public void rimuovi(Categoria categoriaInstance) throws Exception {
		// questo Ã¨ come una connection
		
		if(categoriaInstance.getArticoli().size()>0) {
			System.out.println("categoria contente articoli, impossibile rimuovere");
			return;
		}
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			// questo Ã¨ come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			categoriaDAO.setEntityManager(entityManager);
										

			// eseguo quello che realmente devo fare
			categoriaDAO.delete(categoriaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO=categoriaDAO;
		
	}

	@Override
	public void aggiungiArticoloACategoria(Categoria categoriaInstance, Articolo articoloInstance) throws Exception {
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// questo è come il MyConnection.getConnection()
					entityManager.getTransaction().begin();

					// uso l'injection per il dao
					categoriaDAO.setEntityManager(entityManager);

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

	@Override
	public List<Categoria> findAllCategorieOrdine(Long IdOrdine) throws Exception {
		try {
			// uso l'injection per il dao
			categoriaDAO.setEntityManager(EntityManagerUtil.getEntityManager());

			// eseguo quello che realmente devo fare
			return categoriaDAO.findAllCategorieOrdine(IdOrdine);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
