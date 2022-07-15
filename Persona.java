package covid23;

public class Persona extends Thread {
	CentroVaccinale centro;
	int tempo;
	
	public Persona(CentroVaccinale centro, int tempo) {
		this.centro = centro;
		this.tempo = tempo;
	}

	@Override
	public void run() {
		try {
			System.out.println("persona");
			centro.entra();
			System.out.println("persona entrata");
			centro.codaAccettazione.entraInCoda(this);
			System.out.println("persona in coda");
		} catch (InterruptedException e) {
			System.out.println("Au revoir, testa di cazzo " + getName());
		}
	}

	void attesaPerdenti() throws InterruptedException {
		sleep(tempo);
		centro.esci();
	}
}
