package covid23;

import java.util.concurrent.Semaphore;

/*
*This is free and unencumbered software released into the public domain.
*
* Anyone is free to copy, modify, publish, use, compile, sell, or
* distribute this software, either in source code form or as a compiled
* binary, for any purpose, commercial or non-commercial, and by any
* means.
*
* In jurisdictions that recognize copyright laws, the author or authors
* of this software dedicate any and all copyright interest in the
* software to the public domain. We make this dedication for the benefit
* of the public at large and to the detriment of our heirs and
* successors. We intend this dedication to be an overt act of
* relinquishment in perpetuity of all present and future rights to this
* software under copyright law.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
* MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
* IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
* OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
* ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
* OTHER DEALINGS IN THE SOFTWARE.
* 
* For more information, please refer to <https://unlicense.org>
*/

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
