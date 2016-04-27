package fr.maincare.tdd.tennis;

public class ScoreJeuJoueur {
    private int indexScore = 0;

    public int getIndexScore() {
        return indexScore;
    }

    public void augmenteIndex() {
        this.indexScore++;
    }
    
    public void diminueIndex() {
        this.indexScore--;
    }
}
