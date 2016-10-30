package br.com.ufrn.bti.desktop.aeroporto.dominio;

public class Portao {
	private int numeroPortao;
	private boolean disponivel;
	private Aviao aviao;
	
	public Portao(int numeroPortao, Aviao aviao) {
		super();
		this.numeroPortao = numeroPortao;
		this.aviao = aviao;
		this.disponivel = true;
	}
	
	public Portao(int numeroPortao) {
		super();
		this.numeroPortao = numeroPortao;
		this.disponivel = true;
		this.aviao = new Aviao();
	}
	
	public Portao(){
		this.numeroPortao = -1;
	}
	
	public int getNumeroPortao() {
		return numeroPortao;
	}
	
	public void setNumeroPortao(int numeroPortao) {
		this.numeroPortao = numeroPortao;
	}
	
	public Aviao getAviao() {
		return aviao;
	}
	
	public void setAviao(Aviao aviao) {
		this.aviao = aviao;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	
	public void recebePassageiros(Aviao aviao){
		this.setAviao(aviao);
		this.setDisponivel(false);
	}
	
	public void liberaPortao(){
		this.setAviao(null);
		this.setDisponivel(true);
	}
}
