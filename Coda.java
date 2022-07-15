package covid23;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Coda {
	/* coda */
	ArrayList<Persona> coda = new ArrayList<>();
	Semaphore genteInCoda = new Semaphore(0);
	Semaphore mutex = new Semaphore(1);
	/* con addetti */
	int nAddetti;
	int tempoAddetti;

	public Coda(int nAddetti, int tempoAddetti) {
		this.nAddetti = nAddetti;
		this.tempoAddetti = tempoAddetti;
	}

	public void entraInCoda(Persona p) throws InterruptedException {
		mutex.acquire();
		coda.add(p);
		genteInCoda.release();
		mutex.release();
	}

	public Persona pigliaDaCoda() throws InterruptedException {
		genteInCoda.acquire();
		/* non dovrebbero esserci problemi se
		 * entraInCoda si mette qui in mezzo, spero */
		mutex.acquire();
		Persona ret = coda.get(0);
		coda.remove(0);
		mutex.release();
		return ret;
	}
}

class Addetto extends Thread {
	int tempoRichiesto;
	Coda coda;
	
	public Addetto(int tempo, Coda coda) {
		this.tempoRichiesto = tempo;
		this.coda = coda;
	}
	
	@Override
	public void run() {
		try {
			while(!interrupted()) {
				System.out.println("Questa è una classe base e dovresti estenderala");
				sleep(tempoRichiesto);
			}
		} catch (InterruptedException e) {
			System.out.println("Ti ho già detto che doveresto estedermi, con affetto");
		}
	}
}