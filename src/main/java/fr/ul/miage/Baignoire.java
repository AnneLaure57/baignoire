package fr.ul.miage;

import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

public class Baignoire {
	private float volume;
	private float qteEauTot;
	private float qteVerse;
	private float qteFuite;
	private Semaphore semaphore;
	
	private static final Logger LOG = Logger.getLogger(Baignoire.class.getName());
	
	public Baignoire(float volume, float qteEauTot, float qteVerse, float qteFuite) {
		super();
		this.volume = volume;
		this.qteEauTot = qteEauTot;
		this.qteVerse = qteVerse;
		this.qteFuite = qteFuite;
		this.semaphore = new Semaphore(1);
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		try {
			semaphore.acquire();
			this.volume = volume;
			if(this.getQteEauTot() > volume) {
				this.qteEauTot = volume;
			}
			
		} catch (InterruptedException e) {
			LOG.severe("Problème avec le volume totale de la baignoire");
			e.printStackTrace();
		}
		semaphore.release();
		
	}

	public float getQteEauTot() {
		return qteEauTot;
	}
	
	public boolean estPlein() {
		if(this.volume == this.getQteEauTot()) {
			return true;
		} else {

			return false;
		}
	}

	public void setQteEauTot(float qteEauTot) {
		this.qteEauTot = qteEauTot;
	}

	public float getQteVerse() {
		return qteVerse;
	}

	public void setQteVerse(float qteVerse) {
		try {
			semaphore.acquire();
			this.qteVerse = qteVerse;
		} catch (InterruptedException e) {
			LOG.severe("Problème avec la qteVerse");
			e.printStackTrace();
		}
		semaphore.release();	
	}

	public float getQteFuite() {
		return qteFuite;
	}

	public void setQteFuite(float qteFuite) {
		try {
			semaphore.acquire();
			this.qteFuite = qteFuite;
		} catch (InterruptedException e) {
			LOG.severe("Problème avec la qteFuite");
			e.printStackTrace();
		}
		semaphore.release();
	}
	
	//ajouter de l'eau dans la baignoire
	public void remplir() {
		try {
			semaphore.acquire();
			if(this.estPlein()) {
				return;
			}	
			if(this.qteEauTot + (this.qteVerse/100) <= this.volume) {
				this.qteEauTot = this.qteEauTot + (this.qteVerse/100);
			} else {
				this.setQteEauTot(this.volume);
			}
		} catch (InterruptedException e) {
			LOG.severe("Impossible de remplir la baignoire");
			e.printStackTrace();
		}
		semaphore.release();
	}
	
	//verser de l'eau dans la baignoire
	public void vider() {
		try {
			semaphore.acquire();
			if(this.estPlein()) {
				return;
			}
			if(this.qteEauTot - (this.qteFuite/100) >= 0) {
				this.qteEauTot = this.qteEauTot - (this.qteFuite/100);
			} else {
				this.setQteEauTot(0);
			}
		} catch (InterruptedException e) {
			LOG.severe("Impossible de vider la baignoire");
			e.printStackTrace();
		}
		semaphore.release();
	}

	@Override
	public String toString() {
		return "Baignoire [volume=" + volume + ", qteEauTot=" + qteEauTot + ", qteVerse=" + qteVerse + ", qteFuite="
				+ qteFuite + "]";
	}
}
