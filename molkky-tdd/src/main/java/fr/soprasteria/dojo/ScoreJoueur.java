package fr.soprasteria.dojo;

public class ScoreJoueur {
	private static final int NB_FAILS_TO_RESET = 3;
	private static final int SCORE_GAGNANT = 50;
	private static final int SCORE_SUR_DEPASSEMENT = 25;

	public final String nomJoueur;
	private int score;
	private int nbFails = 0;

	public ScoreJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	public void miseAJourScore(int... quillesTombees) {
		ScoreLancer lancer = new ScoreLancer(quillesTombees);
		if (lancer.fail()) {
			miseAJourSurFail();
		} else {
			miseAJourAvecQuilles(lancer);
		}
		resetScoreSiMaxDepasse();
	}

	private void resetScoreSiMaxDepasse() {
		if (score > SCORE_GAGNANT) {
			score = SCORE_SUR_DEPASSEMENT;
		}
	}

	private void miseAJourAvecQuilles(ScoreLancer lancer) {
		nbFails = 0;
		score += lancer.scoreLancer();
	}

	private void miseAJourSurFail() {
		nbFails++;
		if (nbFails == NB_FAILS_TO_RESET) {
			score = 0;
			nbFails = 0;
		}
	}

	public boolean partieTerminee() {
		return score == SCORE_GAGNANT;
	}

	@Override
	public String toString() {
		return String.format("%s:%s", nomJoueur, score);
	}
}
