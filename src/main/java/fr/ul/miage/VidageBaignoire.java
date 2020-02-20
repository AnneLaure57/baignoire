package fr.ul.miage;

import java.util.concurrent.Callable;

public class VidageBaignoire implements Callable<Float>{
	
	private Baignoire baignoire;
	
	public VidageBaignoire(Baignoire baignoire) 
	{
		this.baignoire = baignoire;
	}

	public Float call() throws Exception {
		while(!this.baignoire.estPlein() && !this.baignoire.estVide())
		{
			Thread.sleep(10);
			baignoire.vider();
		}
		return 1F;
	}
}
