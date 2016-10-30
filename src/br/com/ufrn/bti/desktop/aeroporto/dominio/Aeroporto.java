package br.com.ufrn.bti.desktop.aeroporto.dominio;

import java.util.ArrayList;
import java.util.List;

public class Aeroporto {

	private String nome;
	private int qtdPortoes;
	private List<Aviao> avioesDesembarcando;
	private List<Portao> portoes;
	private boolean disponivel;

	public Aeroporto(String nome, int qtdPortoes) {

		super();

		this.nome = nome;
		this.qtdPortoes = qtdPortoes;
		this.disponivel = true;
		this.portoes = new ArrayList<Portao>(this.qtdPortoes);
		this.avioesDesembarcando = new ArrayList<Aviao>();

		for (int i = 0; i < this.qtdPortoes; i++) {
			this.portoes.add(new Portao(i));
		}

	}

	public synchronized void realizaPouso(Aviao aviao) throws InterruptedException {		
		
		
		while (this.isDisponivel() == false) {
			wait();
		}

		Portao p = existePortaoDisponivel();
		
		if(p != null){
			
			p.setDisponivel(false);
			
			aviao.setPortao(p);
			this.avioesDesembarcando.add(aviao);
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("| Avião " + aviao.getId() + " pousou. Portão de desembarque: " + aviao.getPortao().getNumeroPortao() + " |");
			System.out.println("| Portão de desembarque: " + aviao.getPortao().getNumeroPortao() + " |");
			System.out.println("| Situação: " + aviao.getSituacaoTexto() + " |");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

			if (this.avioesDesembarcando.size() >= qtdPortoes) {
				this.setDisponivel(false);
			}
			
		} else {
			System.out.println("Não há portões disponíveis.");
		}
		
		notifyAll();
	}

	public synchronized void realizaDecolagem(Aviao aviao) {
		this.avioesDesembarcando.remove(aviao);
		this.portoes.get(aviao.getPortao().getNumeroPortao()).setDisponivel(true);
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("| Avião " + aviao.getId() + " decolou." + " |");
		System.out.println("| Portão de embarque: " + aviao.getPortao().getNumeroPortao() + " |");
		System.out.println("-----------------------------------------------------------------------------------------------");
		this.setDisponivel(true);
		notifyAll();
	}

	public Portao existePortaoDisponivel(){
		for(int i = 0 ; i < this.portoes.size() ; i++){
			if(this.portoes.get(i).isDisponivel()){
				return this.portoes.get(i);
			}
		}
		System.out.println("Não há portões disponíveis.");
		return null;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtdPortoes() {
		return qtdPortoes;
	}

	public void setQtdPortoes(int qtdPortoes) {
		this.qtdPortoes = qtdPortoes;
	}

	public List<Portao> getPortoes() {
		return portoes;
	}

	public void setPortoes(List<Portao> portoes) {
		this.portoes = portoes;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public List<Aviao> getAvioesDesembarcando() {
		return avioesDesembarcando;
	}

	public void setAvioesDesembarcando(List<Aviao> avioesDesembarcando) {
		this.avioesDesembarcando = avioesDesembarcando;
	}
}
