package covid23;

public class CodaAccettazione extends Coda {
	AddettoAccettazione[] addetti;

	/* da inizializzare con una coda di addettiAccettazione */
	public CodaAccettazione(int nAddetti, int tempoAddetti) {
		super(nAddetti, tempoAddetti);
		this.addetti = new AddettoAccettazione[nAddetti];
		initAddetti(tempoAddetti);
	}

	void initAddetti(int tempoAddetti) {
		for(int i = 0; i<addetti.length; ++i)
			addetti[i] = new AddettoAccettazione(tempoAddetti, this);
	}
}

class AddettoAccettazione extends Addetto {
	public AddettoAccettazione(int tempo, CodaAccettazione coda) {
		super(tempo, coda);
	}

	@Override
	public void run() {
		try {
			while (!interrupted()) {
				System.out.println("Signora io non lo accetto " + getName());
				Persona p = coda.pigliaDaCoda();
				sleep(tempoRichiesto);
				controlla(p);
			}
		} catch (InterruptedException e) {
			System.out.println("Ma che maniere sono queste! Signora! " + getName());
		}
	}

	void controlla(Persona p) throws InterruptedException {
		System.out.println("Sto controllando " + getName());
		int dado = (int) (100 * Math.random());
		if (dado < 5) {
			p.centro.esci();
			p.interrupt();
		} else {
			p.centro.codaVaccinazione.entraInCoda(p);
		}
	}
}