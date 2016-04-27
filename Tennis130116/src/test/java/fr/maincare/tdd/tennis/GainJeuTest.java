package fr.maincare.tdd.tennis;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class GainJeuTest {

    @Test
    public void jeuNonTermine() throws Exception {
        // ARRANGE
        Jeu jeu = new Jeu();
        
        // ACT
        
        // ASSERT
        String vainqueurJeu = jeu.vainqueur();
        assertThat(vainqueurJeu).isNull();
    }

    @Test
    public void jeuBlanc() throws Exception {
        // ARRANGE
        Jeu jeu = new Jeu();
        
        // ACT
        jeu.pointJoueur1();
        jeu.pointJoueur1();
        jeu.pointJoueur1();
        jeu.pointJoueur1();
        
        // ASSERT
        String vainqueurJeu = jeu.vainqueur();
        assertThat(vainqueurJeu).isEqualTo("joueur1");
    }

    @Test
    public void jeuBlancReceveur() throws Exception {
        // ARRANGE
        Jeu jeu = new Jeu();
        
        // ACT
        jeu.pointJoueur2();
        jeu.pointJoueur2();
        jeu.pointJoueur2();
        jeu.pointJoueur2();
        
        // ASSERT
        String vainqueurJeu = jeu.vainqueur();
        assertThat(vainqueurJeu).isEqualTo("joueur2");
    }

    @Test
    public void aucunVainqueurSiAvantage() throws Exception {
        // ARRANGE
        Jeu jeu = new Jeu();
        
        // ACT
        jeu.pointJoueur1();
        jeu.pointJoueur2();
        jeu.pointJoueur1();
        jeu.pointJoueur2();
        jeu.pointJoueur1();
        jeu.pointJoueur2();
        jeu.pointJoueur1();
        
        // ASSERT
        String vainqueurJeu = jeu.vainqueur();
        assertThat(vainqueurJeu).isNull();
    }
}
