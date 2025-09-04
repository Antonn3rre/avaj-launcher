package simulation;

import simulation.flyable.Flyable;
import java.util.*; // List

public class Tower {

	private List<Flyable> observers;

	public void	register(Flyable p_flyable) {
		if (observers == null)
			observers = new ArrayList<>();
		observers.add(p_flyable);
	}

	public void	unregister(Flyable p_flyable) {
		observers.remove(p_flyable);
	}

	protected void conditionChanged() {

		// Copie pour eviter les problemes quand unregister
		for (Flyable fly : new ArrayList<>(observers)) {
			fly.updateConditions();
		}
	}
}
