package fr.ul.miage;

import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;


public class Controller {
	
	private Baignoire baignoire;
	
	@FXML
	private Button start;
	
	@FXML 
	private ProgressBar progB;
	
	@FXML
	private Slider vider;
	
	@FXML
	private Slider remplir;
	
	@FXML
	private Slider vol;
	
	@FXML
	private Text res;
	
	@FXML
	private Text time;
	
	@FXML
	private Text txtR;
	
	@FXML
	private Text txtV;
	
	@FXML 
	public void initialize()
	{				
		time.setDisable(true);
		res.setDisable(true);
		
		res.setText("Message :");
		time.setText("la baignoire est vide !");
		progB.setProgress(0);
	}
	
	@FXML
	void start(ActionEvent e) throws Exception
	{
		baignoire = new Baignoire((float) vol.getValue(), 0, (float)vider.getValue(), (float)remplir.getValue());
		
		txtV.setText(String.valueOf((float)vider.getValue()));
		txtR.setText(String.valueOf((float)remplir.getValue()));
		
		Executor executor = Executors.newFixedThreadPool(3);
		CompletionService<Float> pool = new ExecutorCompletionService<>(executor);
		pool.submit(new RemplissageBaignoire(this.baignoire));
		pool.submit(new VidageBaignoire(this.baignoire));
		pool.submit(new TableauDeBord(this.baignoire, this.start, this.progB, this.vol, this.res, this.time));
	}
	
	@FXML
	void vider() throws InterruptedException
	{
		if(this.baignoire != null && !this.baignoire.estPlein() || !this.baignoire.estVide()) {
			this.baignoire.setQteFuite((float)vider.getValue());
			txtV.setText(String.valueOf((float)vider.getValue()));
		}
	}
	
	@FXML
	void remplir() throws InterruptedException
	{
		if(this.baignoire != null && !this.baignoire.estPlein()) {
			this.baignoire.setQteVerse((float)remplir.getValue());
			txtR.setText(String.valueOf((float)remplir.getValue()));
		}
	}
}
