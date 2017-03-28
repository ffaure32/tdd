package fr.soprasteria.dojo;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class MolkkyTest {

	private Molkky molkky;
	
	@Before
	public void init() {
		molkky = new Molkky();
	}

	@Test(expected=IllegalArgumentException.class)
	public void minimumUnJoueur() {
		// Arrange
		
		// Act
		molkky.demarrerPartie(0);
		
		// Assert
	}

	@Test
	public void scoreInitialNul() {
		// Arrange
		
		// Act
		molkky.demarrerPartie(1);
		
		// Assert
		String score = molkky.score();
		Assertions.assertThat(score).isEqualTo("Joueur 1:0");
	}

	@Test
	public void scoreInitial2Joueurs() {
		// Arrange
		
		// Act
		molkky.demarrerPartie(2);
		
		// Assert
		String score = molkky.score();
		Assertions.assertThat(score).isEqualTo("Joueur 1:0 - Joueur 2:0");
	}

	@Test(expected=IllegalStateException.class)
	public void scoreInitialSansJoueurs() {
		// Arrange
		
		// Act
		molkky.score();
		
		// Assert
	}

	@Test
	public void scorePremierLancerUneQuille() {
		// Arrange
		molkky.demarrerPartie(1);
		
		// Act
		molkky.lancer(5);
		
		// Assert
		String score = molkky.score();
		Assertions.assertThat(score).isEqualTo("Joueur 1:5");
		
	}

	@Test
	public void scorePremierLancer2Quilles() {
		// Arrange
		molkky.demarrerPartie(1);
		
		// Act
		molkky.lancer(5, 6);
		
		// Assert
		String score = molkky.score();
		Assertions.assertThat(score).isEqualTo("Joueur 1:2");
		
	}

	@Test
	public void scorePremierLancer4Quilles() {
		// Arrange
		molkky.demarrerPartie(1);
		
		// Act
		molkky.lancer(5, 6, 1, 8);
		
		// Assert
		String score = molkky.score();
		Assertions.assertThat(score).isEqualTo("Joueur 1:4");
		
	}

	@Test
	public void scoreLancer2Joueurs() {
		// Arrange
		molkky.demarrerPartie(2);
		
		// Act
		molkky.lancer(5);
		molkky.lancer(6);
		
		// Assert
		String score = molkky.score();
		Assertions.assertThat(score).isEqualTo("Joueur 1:5 - Joueur 2:6");
	}

	@Test
	public void scoreTropImportant() {
		// Arrange
		molkky.demarrerPartie(1);
		
		// Act
		lancersConsecutifs(12, 12, 12, 12, 12);
		
		// Assert
		String score = molkky.score();
		Assertions.assertThat(score).isEqualTo("Joueur 1:25");
		
	}

	@Test
	public void scoreGagnant() {
		// Arrange
		molkky.demarrerPartie(1);
		lancersConsecutifs(12, 12, 12, 12, 2);
		
		// Act
		String classement = molkky.classement();
		
		// Assert
		Assertions.assertThat(classement).isEqualTo("Joueur 1");
	}


	@Test
	public void vainqueurIndetermine() {
		// Arrange
		molkky.demarrerPartie(1);
		molkky.lancer(12);
		
		// Act
		String classement = molkky.classement();
		
		// Assert
		Assertions.assertThat(classement).isNullOrEmpty();
	}

	@Test
	public void scoreGagnantJoueur2() {
		// Arrange
		molkky.demarrerPartie(2);
		lancersConsecutifs(1, 12, 1, 12, 1, 12, 1, 12, 1, 2);
		
		// Act
		String indexVainqueur = molkky.classement();
		
		// Assert
		Assertions.assertThat(indexVainqueur).isEqualTo("Joueur 2");
	}

	@Test(expected=IllegalArgumentException.class)
	public void impossibleDeFaireTomber2FoisLaMemeQuille() {
		// Arrange
		molkky.demarrerPartie(1);
		
		// Act
		molkky.lancer(5, 5);
		
		// Assert
		
	}

	@Test(expected=IllegalArgumentException.class)
	public void laQuilleMaxAUnScore12() {
		// Arrange
		molkky.demarrerPartie(1);
		
		// Act
		molkky.lancer(13);
		
		// Assert
		
	}

	@Test(expected=IllegalArgumentException.class)
	public void laQuilleMinAUnScore1() {
		// Arrange
		molkky.demarrerPartie(1);
		
		// Act
		molkky.lancer(0);
		
		// Assert
		
	}


	@Test 
	public void nommerJoueurs() {
		// Arrange
		
		// Act
		demarrerPartieDalton();
		
		// Assert
		String score = molkky.score();
		Assertions.assertThat(score).isEqualTo("John:0 - William:0 - Jack:0 - Averel:0");
		
		
	}

	@Test 
	public void vainqueurAvecNom() {
		// Arrange
		molkky.demarrerPartie("John", "William");
		
		// Act
		lancersConsecutifs(12, 1, 12, 1, 12, 1, 12, 1, 2, 1);

		
		// Assert
		String vainqueur = molkky.classement();
		Assertions.assertThat(vainqueur).isEqualTo("John");
	}

	@Test 
	public void premierLanceur() {
		// Arrange
		
		// Act
		demarrerPartieDalton();
		
		// Assert
		String prochainLanceur = molkky.nomProchainLanceur();
		Assertions.assertThat(prochainLanceur).isEqualTo("John");
		
		
	}

	@Test 
	public void prochainLanceur() {
		// Arrange
		demarrerPartieDalton();
		
		// Act
		molkky.lancer();
		
		// Assert
		String prochainLanceur = molkky.nomProchainLanceur();
		Assertions.assertThat(prochainLanceur).isEqualTo("William");
	}

	@Test 
	public void prochainLanceurAvecVainqueur() {
		// Arrange
		demarrerPartieDalton();
		
		// Act
		lancersConsecutifs(12, 1, 1, 1, 12, 1, 1, 1, 12, 1, 1, 1, 12, 1, 1, 1, 2, 1, 1, 1);
		
		String prochainLanceur = molkky.nomProchainLanceur();
		
		// Assert
		Assertions.assertThat(prochainLanceur).isEqualTo("William");
	}

	@Test(expected=IllegalStateException.class)
	public void lancerSurPartieTerminee() {
		// Arrange
		molkky.demarrerPartie(1);
		lancersConsecutifs(12, 12, 12, 12, 2);
		
		// Act
		molkky.lancer(2);
		
		// Assert
	}

	@Test 
	public void partieComplete() {
		// Arrange
		demarrerPartieDalton();
		
		// Act
		lancersConsecutifs(12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 2, 2, 2, 2);
		
		String prochainLanceur = molkky.classement();
		
		// Assert
		Assertions.assertThat(prochainLanceur).isEqualTo("John - William - Jack - Averel");
	}

	@Test 
	public void razSur3LancersSansQuille() {
		// Arrange
		molkky.demarrerPartie(1);
		molkky.lancer(2);
		
		// Act
		molkky.lancer();
		molkky.lancer();
		molkky.lancer();
		
		// Assert
		String score = molkky.score();
		Assertions.assertThat(score).isEqualTo("Joueur 1:0");
	}

	@Test 
	public void razNbFailsSiQuilleTombee() {
		// Arrange
		molkky.demarrerPartie(1);
		
		// Act
		molkky.lancer();
		molkky.lancer(2);
		molkky.lancer();
		molkky.lancer();
		
		// Assert
		String score = molkky.score();
		Assertions.assertThat(score).isEqualTo("Joueur 1:2");
	}

	private void lancersConsecutifs(int...lancers) {
		for(int lancersUnitaires : lancers) {
			molkky.lancer(lancersUnitaires);	
		}
	}

	private void demarrerPartieDalton() {
		molkky.demarrerPartie("John", "William", "Jack", "Averel");
	}

}
