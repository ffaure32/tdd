package fr.soprasteria.dojo;

import java.util.function.ObjIntConsumer;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;

public class ControleurValiditeLancer {
	private static final int QUILLE_MIN = 1;
	private static final int QUILLE_MAX = 12;
	
	private ObjIntConsumer<Integer> verificateurLancers = new ObjIntConsumer<Integer>() {
		public void accept(Integer scoreUnitaire, int count) {
			Preconditions.checkArgument(scoreUnitaire <= QUILLE_MAX,"La quille la plus grosse a 12 points");
			Preconditions.checkArgument(scoreUnitaire >= QUILLE_MIN,"La quille la plus petite a 1 points");
			Preconditions.checkArgument(count == 1,"Impossible de faire tomber 2 fois la même quille");
		}
	};

	public void verifierValiditeLancer(int[] quillesTombees) {
		Multiset<Integer> quillesParScore = HashMultiset.create();
		quillesParScore.addAll(Ints.asList(quillesTombees));
		quillesParScore.forEachEntry(verificateurLancers);
	}

}
