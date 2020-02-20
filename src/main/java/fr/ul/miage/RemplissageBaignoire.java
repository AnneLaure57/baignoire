package fr.ul.miage;

import java.util.concurrent.Callable;

public class RemplissageBaignoire implements Callable<Float>{
	
	private Baignoire baignoire;
	
	public RemplissageBaignoire(Baignoire baignoire) 
	{
		this.baignoire = baignoire;
	}

	public Float call() throws Exception {
		while(!this.baignoire.estPlein())
		{
			Thread.sleep(10);
			baignoire.remplir();
		}
		return 1F;
	}
}
