package covid23;

import java.util.concurrent.Semaphore;

public class GestitoMalissimo {
	public static void main(String[] JavaStaiZitto) throws InterruptedException {
		int nPersone = 200;
		int capienzaCentro = 50;

		int nAddettiAccettazione = 20;
		int nAddettiVaccinazione = 25;

		int secondiAccettazione = 10;
		int secondiVaccinazione = 7;
		int secondiDellaVergogna = 17;

		CodaAccettazione ca = new CodaAccettazione
				(nAddettiAccettazione, secondiAccettazione * 1000);
		CodaVaccinazione cv = new CodaVaccinazione
				(nAddettiVaccinazione, secondiVaccinazione * 1000);

		CentroVaccinale mandelaForum = new CentroVaccinale(capienzaCentro, ca, cv);

		int j = 0;
		Persona[] persone = new Persona[nPersone];
		
		for(Persona p : persone) {
			p = new Persona(mandelaForum, secondiDellaVergogna*1000);
			p.setName("Pierfra Precovid Bellissime Levissime " + (j++));
			p.start();
		}
		j = 0;
		for (AddettoAccettazione aa : ca.addetti) {
			aa.setName("Mario Rossi " + (j++));
			aa.start();
		}
		j = 0;
		for (AddettoVaccinazione aa : cv.addetti) {
			aa.setName("Maria Rosa " + (j++));
			aa.start();
		}


		int nSecondi = 120;
		for (int i = 0; i < nSecondi; ++i) {
			Thread.sleep(500);
			System.out.println("\n====begin PGP key====");
			System.out.println("Sono passati " + i + " secondi dall'evento");
			System.out.println("Sono presenti " + ca.coda.size() + " persone nella coda di accettazione");
			System.out.println("Sono presenti " + cv.coda.size() + " persone nella coda di vaccinazione");
			
			System.out.println("Semaphore coda accettazione "
			+ mandelaForum.codaAccettazione.genteInCoda.availablePermits());
			System.out.println("Mutex coda accettazione "
			+ mandelaForum.codaAccettazione.mutex.availablePermits());
			System.out.println("Semaphore coda vaccinale "
			+ mandelaForum.codaVaccinazione.genteInCoda.availablePermits());
			System.out.println("Tot che sono stati vaccinati "
			+ mandelaForum.totVaccinati);
			System.out.println("Tot che sono entrati "
			+ mandelaForum.totEntrati);
			System.out.println("=====end PGP key=====\n");
			Thread.sleep(500);
		}

		for (AddettoAccettazione aa : ca.addetti)
			aa.interrupt();
		for (AddettoVaccinazione av : cv.addetti)
			av.interrupt();

		for (AddettoAccettazione aa : ca.addetti)
			aa.join();
		for (AddettoVaccinazione av : cv.addetti)
			av.join();
	}
}

/* Person (Peterson)
 * Centro buffer limitato capacità m persone (semaforo contatore, e magari un ArrayList)
 * AddettoAccettazione extends Thread
 * AddettoVaccino extends Thread */
