package fr.maincare.tdd.tennis;

public class Jeu {
    private static final String SEPARATEUR_SCORE = "-";
    private static final int SCORE_AVANTAGE = 4;
    private static final int ECART_MINIMUM_GAIN_JEU = 2;
    private static final int NOMBRE_POINTS_GAIN_JEU = 4;
    private static final String[] SCORES_POSSIBLES = {"0", "15", "30", "40", "A"};
    private ScoreJeuJoueur scoreJoueur1 = new ScoreJeuJoueur();
    private ScoreJeuJoueur scoreJoueur2 = new ScoreJeuJoueur();
    public String score() {
        return SCORES_POSSIBLES[indexScoreJoueur1()]+SEPARATEUR_SCORE+SCORES_POSSIBLES[indexScoreJoueur2()];
    }

    public void pointJoueur1() {
        majScore(scoreJoueur1, scoreJoueur2);
    }

    public void pointJoueur2() {
        majScore(scoreJoueur2, scoreJoueur1);
    }

    private void majScore(ScoreJeuJoueur marqueur, ScoreJeuJoueur victime) {
        if(jeuTermine()) {
            throw new IllegalStateException("Impossible de marquer un point sur un jeu terminé");
        }
        if(victime.getIndexScore()==SCORE_AVANTAGE) {
            victime.diminueIndex();
        } else {
            marqueur.augmenteIndex();
        }
    }

    private boolean jeuTermine() {
        return vainqueur() != null;
    }
    
    public String vainqueur() {
        boolean ecartSuffisant = Math.abs(indexScoreJoueur2()-indexScoreJoueur1()) >= ECART_MINIMUM_GAIN_JEU;
        if(ecartSuffisant) {
            if(indexScoreJoueur1()>=NOMBRE_POINTS_GAIN_JEU) {
                return "joueur1";
            } else if(indexScoreJoueur2()>=NOMBRE_POINTS_GAIN_JEU) {
                return "joueur2";
            }
        }
        return null;
    }

    private int indexScoreJoueur1() {
        return scoreJoueur1.getIndexScore();
    }

    private int indexScoreJoueur2() {
        return scoreJoueur2.getIndexScore();
    }

}
