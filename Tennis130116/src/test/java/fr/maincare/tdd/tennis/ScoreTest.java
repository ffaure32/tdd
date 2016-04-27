package fr.maincare.tdd.tennis;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class ScoreTest {

    @Test
    public void leScoreInitialEstNul() throws Exception {
        // ARRANGE
        Jeu partie = new Jeu();
        
        // ACT
        String score = partie.score();

        // ASSERT
        assertThat(score).isEqualTo("0-0");
    }
    
    @Test
    public void lePremierJoueurMarqueUnPoint() throws Exception {
        // ARRANGE
        Jeu partie = new Jeu();
        
        // ACT
        partie.pointJoueur1();
        
        // ASSERT
        String score = partie.score();
        assertThat(score).isEqualTo("15-0");
    }

    @Test
    public void leDeuxiemeJoueurMarqueUnPoint() throws Exception {
        // ARRANGE
        Jeu partie = new Jeu();
        
        // ACT
        partie.pointJoueur2();
        
        // ASSERT
        String score = partie.score();
        assertThat(score).isEqualTo("0-15");
    }

    @Test
    public void lePremierJoueurMarque2Points() throws Exception {
        // ARRANGE
        Jeu partie = new Jeu();
        
        // ACT
        partie.pointJoueur1();
        partie.pointJoueur1();
        
        // ASSERT
        String score = partie.score();
        assertThat(score).isEqualTo("30-0");
    }

    @Test
    public void lePremierJoueurMarque3Points() throws Exception {
        // ARRANGE
        Jeu partie = new Jeu();
        
        // ACT
        partie.pointJoueur1();
        partie.pointJoueur1();
        partie.pointJoueur1();
        
        // ASSERT
        String score = partie.score();
        assertThat(score).isEqualTo("40-0");
    }

    @Test
    public void les2JoueursSontAEgalite() throws Exception {
        // ARRANGE
        Jeu partie = new Jeu();
        
        // ACT
        partie.pointJoueur1();
        partie.pointJoueur1();
        partie.pointJoueur1();
        partie.pointJoueur2();
        partie.pointJoueur2();
        partie.pointJoueur2();
        
        // ASSERT
        String score = partie.score();
        assertThat(score).isEqualTo("40-40");
    }

    @Test
    public void leJoueur1PrendLAvantage() throws Exception {
        // ARRANGE
        Jeu partie = new Jeu();
        
        // ACT
        partie.pointJoueur1();
        partie.pointJoueur1();
        partie.pointJoueur1();
        partie.pointJoueur2();
        partie.pointJoueur2();
        partie.pointJoueur2();
        partie.pointJoueur1();
        
        // ASSERT
        String score = partie.score();
        assertThat(score).isEqualTo("A-40");
    }

    @Test
    public void leJoueur1rerendLAvantage() throws Exception {
        // ARRANGE
        Jeu partie = new Jeu();
        
        // ACT
        partie.pointJoueur1();
        partie.pointJoueur1();
        partie.pointJoueur1();
        partie.pointJoueur2();
        partie.pointJoueur2();
        partie.pointJoueur2();
        partie.pointJoueur1();
        partie.pointJoueur2();
        partie.pointJoueur1();
        
        // ASSERT
        String score = partie.score();
        assertThat(score).isEqualTo("A-40");
    }

    @Test
    public void leJoueur2rerendLAvantage() throws Exception {
        // ARRANGE
        Jeu partie = new Jeu();
        
        // ACT
        partie.pointJoueur1();
        partie.pointJoueur1();
        partie.pointJoueur1();
        partie.pointJoueur2();
        partie.pointJoueur2();
        partie.pointJoueur2();
        partie.pointJoueur1();
        partie.pointJoueur2();
        partie.pointJoueur2();
        
        // ASSERT
        String score = partie.score();
        assertThat(score).isEqualTo("40-A");
    }

    @Test
    public void leJoueur2RevientAEgalite() throws Exception {
        // ARRANGE
        Jeu partie = new Jeu();
        
        // ACT
        partie.pointJoueur1();
        partie.pointJoueur1();
        partie.pointJoueur1();
        partie.pointJoueur2();
        partie.pointJoueur2();
        partie.pointJoueur2();
        partie.pointJoueur1();
        partie.pointJoueur2();
        
        // ASSERT
        String score = partie.score();
        assertThat(score).isEqualTo("40-40");
    }


}
