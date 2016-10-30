package br.com.ufrn.bti.desktop.aeroporto.dominio;

public class Aviao {

	// Identificação única de um avião
	private int id;

	/*
	 * Situação do avião. Caso 0: Normal Caso 1: Falta de combustível Caso 2:
	 * Problema mecânico *
	 */
	private int situacao;

	private String situacaoTexto;

	private long tempoEmTerra;

	private Aeroporto aeroporto;

	private Portao portao;

	public Aviao(Aeroporto aeroporto, int id, int situacao) {
		super();
		this.aeroporto = aeroporto;
		this.id = id;
		this.situacao = situacao;
		this.tempoEmTerra = (long) Math.random() * 1000;
		this.situacaoTexto = defineSituacaoTexto();
	}

	public Aviao() {
	}

	public void decolar() {
		this.aeroporto.realizaDecolagem(this);
	}

	public void pousar() throws InterruptedException {
		this.aeroporto.realizaPouso(this);
		this.setTempoEmTerra((long) (Math.random() * 100000));
		Thread.sleep(this.getTempoEmTerra());
		decolar();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public long getTempoEmTerra() {
		return tempoEmTerra;
	}

	public void setTempoEmTerra(long tempoEmTerra) {
		this.tempoEmTerra = tempoEmTerra;
	}

	public Aeroporto getAeroporto() {
		return aeroporto;
	}

	public void setAeroporto(Aeroporto aeroporto) {
		this.aeroporto = aeroporto;
	}

	public Portao getPortao() {
		return portao;
	}

	public void setPortao(Portao portao) {
		this.portao = portao;
	}

	public String defineSituacaoTexto() {
		switch (this.situacao) {
		case 1:
			return "Normal";
		case 2:
			return "Aeronave com pouco combustível.";
		case 3:
			return "Aeronave quebrada.";
		default:
			return null;
		}
	}

	public String getSituacaoTexto() {
		return situacaoTexto;
	}

	public void setSituacaoTexto(String situacaoTexto) {
		this.situacaoTexto = situacaoTexto;
	}

}
