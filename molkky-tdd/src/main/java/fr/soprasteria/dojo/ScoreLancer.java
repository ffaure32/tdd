package fr.soprasteria.dojo;

public class ScoreLancer {
	int [] quillesTombees;

	public ScoreLancer(int[] quillesTombees) {
		super();
		this.quillesTombees = quillesTombees;
	}
	
	public int scoreLancer() {
		return uneSeuleQuilleTombee() ? valeurQuilleTombee() : nombreQuillesTombees();
	}
	
	public boolean fail() {
		return this.quillesTombees.length == 0;
	}
	
	private boolean uneSeuleQuilleTombee() {
		return nombreQuillesTombees() == 1;
	}

	private int nombreQuillesTombees() {
		return quillesTombees.length;
	}

	private int valeurQuilleTombee() {
		return quillesTombees[0];
	}

}
