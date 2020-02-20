package fr.ul.miage;

import java.util.concurrent.Callable;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

public class TableauDeBord implements Callable<Float> {
	
	private Baignoire baignoire;
	private Button btn;
	private ProgressBar pB;
	private Slider vol;
	private Text res;
	private Text time;
	
	public TableauDeBord(Baignoire baignoire, Button btn, ProgressBar pB, Slider vol, Text res, Text time) {
		super();
		this.baignoire = baignoire;
		this.btn = btn;
		this.pB = pB;
		this.vol = vol;
		this.res = res;
		this.time = time;
	}
	
	//Conversion des heures et minutes
	public String convertDuree(long dureeTotale) {
		
		//Calculer les minutes
		long diffmin = (long) (dureeTotale / 60);
		
		//Calculer les secondes
		long diffsec = (long) (dureeTotale - (60 * diffmin));
		
		//Afficher les minutes et secondes
		String dureeMS = String.format(" %s minute(s) %s seconde(s)", diffmin, diffsec);
		
		return dureeMS;
	}

	@Override
	public Float call() throws Exception {
		long depart = System.currentTimeMillis();
		long duree = 0;
		btn.setDisable(true);
		vol.setDisable(true);
		while(!this.baignoire.estPlein()){
			Thread.sleep(100);
			duree = (System.currentTimeMillis() - depart)/1000;
			res.setText("Chrono :");
			time.setText(convertDuree(duree));
			float total = baignoire.getQteEauTot()/baignoire.getVolume();
			pB.setProgress(total);
		}
		duree = (System.currentTimeMillis() - depart)/1000;
		pB.setProgress(baignoire.getQteEauTot()/baignoire.getVolume());
		res.setText("Résultat :");
		time.setText("la baignoire s'est remplie en " + convertDuree(duree) + ".");
		btn.setDisable(false);
		vol.setDisable(false);
		return 1F;
	}
}
