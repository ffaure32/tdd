package fr.maincare.tdd.tennis;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class JoueursPersonnalisesTest {

    @Test 
    public void jeuAvecJoueurs() throws Exception {
        // ARRANGE
        Jeu jeu = new Jeu("Nadal", "Federer");
        
        // ACT
        jeu.point("Nadal");
        
        // ASSERT
        assertThat(jeu.score()).isEqualTo("15-0");
    }

    @Test(expected=IllegalArgumentException.class)
    public void jeuAvecJoueurInconnu() throws Exception {
        // ARRANGE
        Jeu jeu = new Jeu("Nadal", "Federer");
        
        // ACT
        jeu.point("Forget");
        
        // ASSERT
    }

}
