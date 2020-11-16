package it.solvingteam.gestioneordini.test;



import java.util.HashSet;
import java.util.Set;

import it.solvingteam.gestioneordini.dao.EntityManagerUtil;
import it.solvingteam.gestioneordini.model.Articolo;
import it.solvingteam.gestioneordini.model.Categoria;
import it.solvingteam.gestioneordini.model.Ordine;
import it.solvingteam.gestioneordini.service.MyServiceFactory;
import it.solvingteam.gestioneordini.service.articolo.ArticoloService;
import it.solvingteam.gestioneordini.service.categoria.CategoriaService;
import it.solvingteam.gestioneordini.service.ordine.OrdineService;

public class MyTest {

	public static void main(String[] args) {

		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();

		try {
			System.out.println("test query");
			
			//non cancellare articolo se è in ordine , 
			//non cancellate categoria se contiene articoli, 
			//non aggiornare articolo se ordinato
			
			/*Articolo articoloInstance= new Articolo("Forbici", 4.5d);
			articoloServiceInstance.inserisciNuovo(articoloInstance);
			
			Categoria categoriaInstance= new Categoria("Giardinaggio");
			categoriaServiceInstance.inserisciNuovo(categoriaInstance);
			
			//Ordine ordineInstance = new Ordine("Via dei Sassi", "Enrico");
			
			 Articolo articoloDaDb = articoloServiceInstance.caricaSingoloElemento(1L);		
			System.out.println(articoloDaDb.getDescrizione());
			
			Categoria categoriaDaDb = categoriaServiceInstance.listAll().stream().findFirst().orElse(null);
			System.out.println(categoriaDaDb.getDescrizione());
			if (categoriaDaDb != null) {
				categoriaServiceInstance.aggiungiArticoloACategoria(categoriaDaDb, articoloDaDb);
			}
			
			Ordine ordineDaDb = ordineServiceInstance.listAll().stream().findFirst().orElse(null);
			System.out.println(ordineDaDb.getNomeDestinatario());
			if (ordineDaDb != null) {
				System.out.println("ordine esistente");
				ordineServiceInstance.InserisciArticoloInOrdine(ordineDaDb, articoloDaDb);
			}
			
			
			//TEST UPDATE OK
			Articolo articoloDaDb = articoloServiceInstance.caricaSingoloElemento(1L);
			
		
			articoloDaDb.setDescrizione("Pinze");
			articoloServiceInstance.aggiorna(articoloDaDb);
			*/
			Articolo articoloDaDb = articoloServiceInstance.caricaSingoloElemento(1L);
			Set<Articolo> articoliOrdinati= new HashSet<Articolo>();
			articoliOrdinati.add(articoloDaDb);
			
			Ordine ordineInstance = new Ordine("Via dei Passi", "Elena");
			//ordineInstance.setArticoli(articoliOrdinati); SE ORDINE HA LISTA VUOTA NON SVOLGE I METODI
			ordineServiceInstance.inserisciNuovo(ordineInstance);
			ordineServiceInstance.InserisciArticoloInOrdine(ordineInstance, articoloDaDb);
			
			
			
			//QUERY1
			System.out.println("Elenco ordini per categoria x:");
			for (Ordine ordineItem: ordineServiceInstance.cercaOrdiniPerCategoria(1L)) {
				System.out.println(ordineItem);			
			}
			// QUERY 2
			System.out.println("Elenco categorie per ordine x:");
			for (Categoria categoriaItem: categoriaServiceInstance.findAllCategorieOrdine(1L)) {
				System.out.println(categoriaItem);			
			}
			//QUERY 3
			System.out.println(articoloServiceInstance.totaleCostoArticoliCategoria(1L));
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}

	}
}
