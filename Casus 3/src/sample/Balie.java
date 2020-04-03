package sample;

import java.util.concurrent.ArrayBlockingQueue;

public class Balie {

	private ArrayBlockingQueue<Bestelling> bestellingen = null;			
	private ArrayBlockingQueue<Maaltijd> maaltijden = null;

	public Balie() {
		this.bestellingen = new ArrayBlockingQueue<Bestelling>(20);
		this.maaltijden = new ArrayBlockingQueue<Maaltijd>(20);
	}

	public void plaatsBestelling(Bestelling b) {
		try {
			bestellingen.put(b);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}									
	}
	
	public boolean erZijnNogBestellingen() {					
		return !bestellingen.isEmpty();							
	}	
	
	public Bestelling pakBestelling() {							
		Bestelling res = null;									//Als de res=bestellingen.take(); om wat voor reden dan ook niet uitgevoerd word, kan de methode 
		if (erZijnNogBestellingen()) {							//nogsteeds wat returnen, en zal het programma dus niet stoppen. Dat is goed!
			try {
				res = bestellingen.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}						
		}
		return res;												
	}

	public void plaatsMaaltijd(Maaltijd m) {					
		try {
			maaltijden.put(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean erZijnNogMaaltijden() {	
		return !maaltijden.isEmpty();
	}

	public Maaltijd pakMaaltijd() {							
		Maaltijd res = null;
		try {
			res = maaltijden.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public void genereerBestellingen() {
		for (int i = 0; i < Restaurant.AANTALBESTELLINGEN; i++) {
			int tafelnummer = Util.randInt(1, Restaurant.AANTALTAFELS);
			int bereidingstijd = Util.randInt(Restaurant.MINIMALE_BEREIDINGSTIJD, Restaurant.MAXIMALE_BEREIDINGSTIJD);
			Bestelling b = new Bestelling(bereidingstijd, tafelnummer);
			this.plaatsBestelling(b);
		}
	}
	
	public ArrayBlockingQueue<Bestelling> getBestellingen() {
		return this.bestellingen;
	}

}
