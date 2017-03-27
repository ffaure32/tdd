package fr.soprasteria.dojo;

public class ScoreJoueur {
	private static final int SCORE_GAGNANT = 50;
	private static final int SCORE_RESET = 25;

	public final String nomJoueur;
	private int score;
	
	public ScoreJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	public ScoreJoueur(int indexJoueur) {
		this.nomJoueur = "Joueur "+indexJoueur;
	}

	public void miseAJourScore(int... quillesTombees) {
		if(quillesTombees.length == 1) {
			score += quillesTombees[0];
		} else {
			score += quillesTombees.length;
		}
		if(score > SCORE_GAGNANT) {
			score = SCORE_RESET;
		}

	}
	
	public boolean partieTerminee() {
		return score == SCORE_GAGNANT;
	}

	@Override
	public String toString() {
		return nomJoueur+":"+score;
	}
}
