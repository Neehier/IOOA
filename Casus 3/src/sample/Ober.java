package sample;

import java.util.concurrent.TimeUnit;

public class Ober implements java.lang.Runnable {

	private String id;
	private Balie balie = null;
	private static final int BEZORGTIJD = 100;

	public Ober(String id, Balie balie) {					
		this.id = id;
		this.balie = balie;
	}
	
	public void run() {
		bezorgMaaltijden();
	}

	public void bezorgMaaltijden() {
		int i = 0;
		while (balie.erZijnNogBestellingen()) {				
			Maaltijd maaltijd = balie.pakMaaltijd();		
			bezorgMaaltijd(maaltijd);						
		}
		if(balie.getBestellingen().size()==0 & i==0)  {
			Maaltijd maaltijd = balie.pakMaaltijd();
			bezorgMaaltijd(maaltijd);
			i = 1;
		}
	}

	private void bezorgMaaltijd(Maaltijd m) {
		try {
			TimeUnit.MILLISECONDS.sleep(BEZORGTIJD);
			System.out.println("Ober " + this.id + " bezorgt maaltijd " + m.getTafelnummer());
		}
		catch (InterruptedException e) {
		}
	}

}
