package covid23;

import java.util.concurrent.Semaphore;

public class CodaVaccinazione extends Coda {
	AddettoVaccinazione[] addetti;

	/* da inizializzare con un array di AddettiVaccinazione */
	public CodaVaccinazione(int nAddetti, int tempoAddetti) {
		super(nAddetti, tempoAddetti);
		this.addetti = new AddettoVaccinazione[nAddetti];
		initAddetti(tempoAddetti);
	}
	
	void initAddetti(int tempoAddetti) {
		for(int i = 0; i<addetti.length; ++i)
			addetti[i] = new AddettoVaccinazione(tempoAddetti, this);
	}
}

class AddettoVaccinazione extends Addetto {
	public AddettoVaccinazione(int tempo, Coda coda) {
		super(tempo, coda);
	}

	@Override
	public void run() {
		try {
			while (!interrupted()) {
				Persona p = coda.pigliaDaCoda();
				sleep(tempoRichiesto);
				vaccina(p);
			}
		} catch (InterruptedException e) {
			System.out.println("Ma che maniere sono queste! Signora! " + getName());
		}
	}

	void vaccina(Persona p) throws InterruptedException {
		p.centro.incrTotVaccinati();
		p.attesaPerdenti();
	}
}
