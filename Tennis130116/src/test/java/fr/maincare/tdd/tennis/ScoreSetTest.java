package fr.maincare.tdd.tennis;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class ScoreSetTest {

    @Test
    public void leScoreInitialEstNul() throws Exception {
        // ARRANGE
        Set partie = new Set();
        
        // ACT
        String score = partie.score();

        // ASSERT
        assertThat(score).isEqualTo("0-0");
    }

    @Test
    public void leScoreAugmenteAvecUnJeu() throws Exception {
        // ARRANGE
        Set partie = new Set();
        jeuJoueur1(partie);

        String score = partie.score();
        // ASSERT
        assertThat(score).isEqualTo("1-0");
    }


    @Test
    public void leScoreAugmenteAvec2Jeux() throws Exception {
        // ARRANGE
        Set partie = new Set();
        jeuJoueur1(partie);
        jeuJoueur2(partie);

        String score = partie.score();
        // ASSERT
        assertThat(score).isEqualTo("1-1");
    }

    
    @Test(expected=IllegalStateException.class)
    public void onNePeutCommencerUnNouveauJeuSurUnJeuExistant() throws Exception {
        // ARRANGE
        Set partie = new Set();

        // ACT
        partie.nouveauJeu();
        partie.nouveauJeu();

        // ASSERT
    }

    public void sansVainqueur() throws Exception {
        // ARRANGE
        Set partie = new Set();

        // ACT
        partie.nouveauJeu();
        String vainqueur = partie.vainqueur();

        // ASSERT
        assertThat(vainqueur).isNull();
    }

    public void joueur1Vainqueur() throws Exception {
        // ARRANGE
        Set partie = new Set();

        // ACT
        jeuJoueur1(partie);
        jeuJoueur1(partie);
        jeuJoueur1(partie);
        jeuJoueur1(partie);
        jeuJoueur1(partie);
        jeuJoueur1(partie);
        String vainqueur = partie.vainqueur();

        // ASSERT
        assertThat(vainqueur).isEqualTo("joueur1");
    }

    @Test
    public void joueur2Vainqueur() throws Exception {
        // ARRANGE
        Set partie = new Set();

        // ACT
        jeuJoueur2(partie);
        jeuJoueur2(partie);
        jeuJoueur2(partie);
        jeuJoueur2(partie);
        jeuJoueur2(partie);
        jeuJoueur2(partie);
        String vainqueur = partie.vainqueur();

        // ASSERT
        assertThat(vainqueur).isEqualTo("joueur2");
    }

    @Test(expected=IllegalStateException.class)
    public void impossibleDeCreerUnNouveauJeuSiSetTermine() throws Exception {
        // ARRANGE
        Set partie = new Set();

        // ACT
        jeuJoueur1(partie);
        jeuJoueur1(partie);
        jeuJoueur1(partie);
        jeuJoueur1(partie);
        jeuJoueur1(partie);
        jeuJoueur1(partie);
        jeuJoueur1(partie);

        // ASSERT
    }


    private void jeuJoueur1(Set partie) {
        Jeu jeu = partie.nouveauJeu();
        jeu.point("joueur1");
        jeu.point("joueur1");
        jeu.point("joueur1");
        jeu.point("joueur1");
    }

    private void jeuJoueur2(Set partie) {
        Jeu jeu = partie.nouveauJeu();
        jeu.point("joueur2");
        jeu.point("joueur2");
        jeu.point("joueur2");
        jeu.point("joueur2");
    }

}
