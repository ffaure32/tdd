package fr.maincare.tdd.tennis;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class JeuTermineTest {

    @Test(expected=IllegalStateException.class)
    public void jeuBlanc() throws Exception {
        // ARRANGE
        Jeu jeu = new Jeu();
        
        // ACT
        jeu.pointJoueur1();
        jeu.pointJoueur1();
        jeu.pointJoueur1();
        jeu.pointJoueur1();
        jeu.pointJoueur2();
        
        // ASSERT
    }
}
