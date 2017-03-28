package fr.soprasteria.dojo;

public class ScoreJoueur {
	private static final int NB_FAILS_TO_RESET = 3;
	private static final int SCORE_GAGNANT = 50;
	private static final int SCORE_RESET = 25;

	public final String nomJoueur;
	private int score;
	private int nbFails = 0;

	public ScoreJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	public void miseAJourScore(int... quillesTombees) {
		if (quillesTombees.length == 0) {
			miseAJourSurFail();
		} else {
			miseAJourAvecQuilles(quillesTombees);
		}
		resetScoreSiMaxDepasse();

	}

	private void resetScoreSiMaxDepasse() {
		if (score > SCORE_GAGNANT) {
			score = SCORE_RESET;
		}
	}

	private void miseAJourAvecQuilles(int... quillesTombees) {
		nbFails = 0;
		if (quillesTombees.length == 1) {
			score += quillesTombees[0];
		} else {
			score += quillesTombees.length;
		}
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
