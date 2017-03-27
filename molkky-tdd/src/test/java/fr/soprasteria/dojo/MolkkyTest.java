package fr.soprasteria.dojo;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class MolkkyTest {

	private Molkky molky;
	
	@Before
	public void init() {
		molky = new Molkky();
	}

	@Test(expected=IllegalArgumentException.class)
	public void minimumUnJoueur() {
		// Arrange
		
		// Act
		molky.demarrerPartie(0);
		
		// Assert
	}

	@Test
	public void scoreInitialNul() {
		// Arrange
		
		// Act
		molky.demarrerPartie(1);
		
		// Assert
		String score = molky.score();
		Assertions.assertThat(score).isEqualTo("Joueur 1:0");
	}

	@Test
	public void scoreInitial2Joueurs() {
		// Arrange
		
		// Act
		molky.demarrerPartie(2);
		
		// Assert
		String score = molky.score();
		Assertions.assertThat(score).isEqualTo("Joueur 1:0 - Joueur 2:0");
	}

	@Test(expected=IllegalStateException.class)
	public void scoreInitialSansJoueurs() {
		// Arrange
		
		// Act
		molky.score();
		
		// Assert
	}

	@Test
	public void scorePremierLancerUneQuille() {
		// Arrange
		molky.demarrerPartie(1);
		
		// Act
		molky.lancer(5);
		
		// Assert
		String score = molky.score();
		Assertions.assertThat(score).isEqualTo("Joueur 1:5");
		
	}

	@Test
	public void scorePremierLancer2Quilles() {
		// Arrange
		molky.demarrerPartie(1);
		
		// Act
		molky.lancer(5, 6);
		
		// Assert
		String score = molky.score();
		Assertions.assertThat(score).isEqualTo("Joueur 1:2");
		
	}

	@Test
	public void scorePremierLancer4Quilles() {
		// Arrange
		molky.demarrerPartie(1);
		
		// Act
		molky.lancer(5, 6, 1, 8);
		
		// Assert
		String score = molky.score();
		Assertions.assertThat(score).isEqualTo("Joueur 1:4");
		
	}

	@Test
	public void scoreLancer2Joueurs() {
		// Arrange
		molky.demarrerPartie(2);
		
		// Act
		molky.lancer(5);
		molky.lancer(6);
		
		// Assert
		String score = molky.score();
		Assertions.assertThat(score).isEqualTo("Joueur 1:5 - Joueur 2:6");
	}

	@Test
	public void scoreTropImportant() {
		// Arrange
		molky.demarrerPartie(1);
		
		// Act
		lancersConsecutifs(12, 12, 12, 12, 12);
		
		// Assert
		String score = molky.score();
		Assertions.assertThat(score).isEqualTo("Joueur 1:25");
		
	}

	@Test
	public void scoreGagnant() {
		// Arrange
		molky.demarrerPartie(1);
		lancersConsecutifs(12, 12, 12, 12, 2);
		
		// Act
		String classement = molky.classement();
		
		// Assert
		Assertions.assertThat(classement).isEqualTo("Joueur 1");
	}


	@Test
	public void vainqueurIndetermine() {
		// Arrange
		molky.demarrerPartie(1);
		molky.lancer(12);
		
		// Act
		String classement = molky.classement();
		
		// Assert
		Assertions.assertThat(classement).isNullOrEmpty();
	}

	@Test
	public void scoreGagnantJoueur2() {
		// Arrange
		molky.demarrerPartie(2);
		lancersConsecutifs(1, 12, 1, 12, 1, 12, 1, 12, 1, 2);
		
		// Act
		String indexVainqueur = molky.classement();
		
		// Assert
		Assertions.assertThat(indexVainqueur).isEqualTo("Joueur 2");
	}

	@Test(expected=IllegalArgumentException.class)
	public void impossibleDeFaireTomber2FoisLaMemeQuille() {
		// Arrange
		molky.demarrerPartie(1);
		
		// Act
		molky.lancer(5, 5);
		
		// Assert
		
	}

	@Test(expected=IllegalArgumentException.class)
	public void laQuilleMaxAUnScore12() {
		// Arrange
		molky.demarrerPartie(1);
		
		// Act
		molky.lancer(13);
		
		// Assert
		
	}

	@Test(expected=IllegalArgumentException.class)
	public void laQuilleMinAUnScore1() {
		// Arrange
		molky.demarrerPartie(1);
		
		// Act
		molky.lancer(0);
		
		// Assert
		
	}


	@Test 
	public void nommerJoueurs() {
		// Arrange
		
		// Act
		demarrerPartieDalton();
		
		// Assert
		String score = molky.score();
		Assertions.assertThat(score).isEqualTo("John:0 - William:0 - Jack:0 - Averel:0");
		
		
	}

	@Test 
	public void vainqueurAvecNom() {
		// Arrange
		molky.demarrerPartie("John", "William");
		
		// Act
		lancersConsecutifs(12, 1, 12, 1, 12, 1, 12, 1, 2, 1);

		
		// Assert
		String vainqueur = molky.classement();
		Assertions.assertThat(vainqueur).isEqualTo("John");
	}

	@Test 
	public void premierLanceur() {
		// Arrange
		
		// Act
		demarrerPartieDalton();
		
		// Assert
		String prochainLanceur = molky.nomProchainLanceur();
		Assertions.assertThat(prochainLanceur).isEqualTo("John");
		
		
	}

	@Test 
	public void prochainLanceur() {
		// Arrange
		demarrerPartieDalton();
		
		// Act
		molky.lancer();
		
		// Assert
		String prochainLanceur = molky.nomProchainLanceur();
		Assertions.assertThat(prochainLanceur).isEqualTo("William");
	}

	@Test 
	public void prochainLanceurAvecVainqueur() {
		// Arrange
		demarrerPartieDalton();
		
		// Act
		lancersConsecutifs(12, 1, 1, 1, 12, 1, 1, 1, 12, 1, 1, 1, 12, 1, 1, 1, 2, 1, 1, 1);
		
		String prochainLanceur = molky.nomProchainLanceur();
		
		// Assert
		Assertions.assertThat(prochainLanceur).isEqualTo("William");
	}

	@Test(expected=IllegalStateException.class)
	public void lancerSurPartieTerminee() {
		// Arrange
		molky.demarrerPartie(1);
		lancersConsecutifs(12, 12, 12, 12, 2);
		
		// Act
		molky.lancer(2);
		
		// Assert
	}

	@Test 
	public void partieComplete() {
		// Arrange
		demarrerPartieDalton();
		
		// Act
		lancersConsecutifs(12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 2, 2, 2, 2);
		
		String prochainLanceur = molky.classement();
		
		// Assert
		Assertions.assertThat(prochainLanceur).isEqualTo("John - William - Jack - Averel");
	}

	private void lancersConsecutifs(int...lancers) {
		for(int lancersUnitaires : lancers) {
			molky.lancer(lancersUnitaires);	
		}
	}

	private void demarrerPartieDalton() {
		molky.demarrerPartie("John", "William", "Jack", "Averel");
	}


}
