package sample;

public class Restaurant {

	Thread threadOber1;
	Thread threadOber2;
	Thread threadKok1;
	Thread threadKok2;
	public static final int AANTALTAFELS = 10;				
	public static final int AANTALBESTELLINGEN = 20;		
	public static final int MINIMALE_BEREIDINGSTIJD = 750;  
	public static final int MAXIMALE_BEREIDINGSTIJD = 1500;	

	public Restaurant() {
		Balie balie = new Balie();
		balie.genereerBestellingen();
		threadKok1 = new Thread(new Kok("1", balie));
		threadKok2 = new Thread(new Kok("2", balie));
		threadOber1 = new Thread(new Ober("1", balie));
		threadOber2 = new Thread(new Ober("2", balie));

	}

	public void start() {
		this.threadKok1.start();
		this.threadKok2.start();
		this.threadOber1.start();								
		this.threadOber2.start();
	}
}