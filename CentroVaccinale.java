package covid23;

import java.util.concurrent.Semaphore;

public class CentroVaccinale {
	int capienza;
	Semaphore possoEntrare;
	CodaAccettazione codaAccettazione;
	CodaVaccinazione codaVaccinazione;

	/* avrei fatto decisamente prima a usare un AtomicInteger */
	int totVaccinati = 0;
	Semaphore mutexTotVaccinati = new Semaphore(1);
	void incrTotVaccinati() throws InterruptedException {
		mutexTotVaccinati.acquire();
		totVaccinati++;
		mutexTotVaccinati.release();
	}
	
	int totEntrati = 0;
	Semaphore mutexTotEntrati = new Semaphore(1);
	void incrTotEntrati() throws InterruptedException {
		mutexTotEntrati.acquire();
		totEntrati++;
		mutexTotEntrati.release();
	}

	public CentroVaccinale(int capienza, CodaAccettazione codaAccettazione,
			CodaVaccinazione codaVaccinazione) {
		this.capienza = capienza;
		possoEntrare = new Semaphore(capienza);
		this.codaAccettazione = codaAccettazione;
		this.codaVaccinazione = codaVaccinazione;
	}

	public void entra() throws InterruptedException {
		possoEntrare.acquire();
		incrTotEntrati();
	}

	public void esci() {
		possoEntrare.release();
	}
}    
