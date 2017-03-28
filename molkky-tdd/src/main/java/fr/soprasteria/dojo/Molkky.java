package fr.soprasteria.dojo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class Molkky {
	private ControleurValiditeLancer controleLancer = new ControleurValiditeLancer();
	
	private List<ScoreJoueur> scores;
	private int indexLanceur = 0;
	private List<String> classement;
	
	public void demarrerPartie(int nombreJoueurs) {
		List<String> nomJoueurs = generationAutoNomsJoueurs(nombreJoueurs);
		demarrerPartie(Iterables.toArray(nomJoueurs, String.class));
	}

	private List<String> generationAutoNomsJoueurs(int nbJoueurs) {
		return Stream.iterate(1, i -> i + 1).limit(nbJoueurs).map(i -> "Joueur "+i).collect(Collectors.toList());
		// return IntStream.rangeClosed(1, nbJoueurs).boxed().map(i -> "Joueur "+i).collect(Collectors.toList());
	}

	public void demarrerPartie(String ... nomsJoueurs) {
		Preconditions.checkArgument(nomsJoueurs.length >0, "Il faut au moins un joueur pour démarrer une partie");
		initScores(nomsJoueurs);
		initClassement(nomsJoueurs.length);
	}

	private void initClassement(int nombreJoueurs) {
		classement = Lists.newArrayListWithExpectedSize(nombreJoueurs);
	}

	private void initScores(String ... nomJoueurs) {
		this.scores = Lists.newArrayListWithExpectedSize(nomJoueurs.length);
		for(String nomJoueur : nomJoueurs) {
			this.scores.add(new ScoreJoueur(nomJoueur));
		}
	}

	public String score() {
		Preconditions.checkState(scores != null, "La partie doit être démarrée pour demander le score");
		return Joiner.on(" - ").join(scores);
	}

	public void lancer(int... quillesTombees) {
		verifierEtatPartie();
		verifierValiditeLancer(quillesTombees);
		miseAJourScoreLanceur(quillesTombees);
		miseAJourClassement();
		miseAJourIndexLanceur();
	}
	
	private void verifierEtatPartie() {
		Preconditions.checkState(!partieTerminee(), "Impossible d'effectuer un lancer sur une partie terminée");
	}

	private void verifierValiditeLancer(int[] quillesTombees) {
		controleLancer.verifierValiditeLancer(quillesTombees);
	}

	public String classement() {
		return Joiner.on(" - ").join(classement);
	}

	private void miseAJourClassement() {
		ScoreJoueur scoreJoueur = scores.get(indexLanceur);
		if(scoreJoueur.partieTerminee()) {
			classement.add(scoreJoueur.nomJoueur);
		}
	}

	private void miseAJourScoreLanceur(int... quille) {
		scores.get(indexLanceur).miseAJourScore(quille);
	}

	private boolean partieTerminee() {
		return classement.size() == scores.size();
	}
	
	private void miseAJourIndexLanceur() {
		if(!partieTerminee()) {
			miseAJourReelleIndexLanceur();
			if(scores.get(indexLanceur).partieTerminee()) {
				miseAJourIndexLanceur();
			}
		}
	}

	private void miseAJourReelleIndexLanceur() {
		if(dernierLanceur()) {
			indexLanceur = 0;
		} else {
			indexLanceur++;
		}
	}

	private boolean dernierLanceur() {
		return indexLanceur == scores.size()-1;
	}

	public String nomProchainLanceur() {
		return scores.get(indexLanceur).nomJoueur;
	}
}
