package br.com.ufrn.bti.desktop.aeroporto.dominio;

import java.util.ArrayList;
import java.util.List;

public class Aeroporto {

	private String nome;
	private int qtdPortoes;
	private int capacidadeEstacionamento;
	private List<Aviao> avioesDesembarcando;
	private List<Aviao> avioesEstacionados;
	private List<Portao> portoes;
	private boolean disponivel;
	private boolean existePortaoDisponivel;

	public Aeroporto(String nome, int qtdPortoes, int capacidadeEstacionamento) {

		super();

		this.nome = nome;
		this.qtdPortoes = qtdPortoes;
		this.capacidadeEstacionamento = capacidadeEstacionamento;
		this.disponivel = true;
		this.existePortaoDisponivel = true;
		this.portoes = new ArrayList<Portao>(this.qtdPortoes);
		this.avioesDesembarcando = new ArrayList<Aviao>();
		this.avioesEstacionados = new ArrayList<Aviao>(this.capacidadeEstacionamento);

		for (int i = 0; i < this.qtdPortoes; i++) {
			this.portoes.add(new Portao(i));
		}

	}

	public synchronized void realizaPousoNovo(Aviao aviao) throws InterruptedException {		

		Portao p = existePortaoDisponivel();
		
		if(p != null){
			
			p.setDisponivel(false);
			
			aviao.setPortao(p);
			aviao.setStatus(2);
			this.avioesDesembarcando.add(aviao);
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("| Avião " + aviao.getId() + " pousou. Portão de desembarque: " + aviao.getPortao().getNumeroPortao() + " |");
			System.out.println("| Portão de desembarque: " + aviao.getPortao().getNumeroPortao() + " |");
			System.out.println("| Situação: " + aviao.getSituacaoTexto() + " |");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

			if (this.avioesDesembarcando.size() + this.avioesEstacionados.size() >= qtdPortoes + capacidadeEstacionamento) {
				this.setDisponivel(false);
			}
		} else {
			
			while(this.isDisponivel() == false) {
				wait();
			}
			aviao.setStatus(1);
			this.avioesEstacionados.add(aviao);
			System.out.println(">>>>>>>>>>>>>>>>>>>  NÃO HÁ PORTÕES DISPONÍVEIS  <<<<<<<<<<<<<<<<<<<<<");
			System.out.println("| Avião " + aviao.getId() + " pousou e foi para o estacionamento. |");
			System.out.println("| Situação: " + aviao.getSituacaoTexto() + " |");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			if (this.avioesDesembarcando.size() + this.avioesEstacionados.size() >= qtdPortoes + capacidadeEstacionamento) {
				this.setDisponivel(false);
			}
		}
		
		notifyAll();
	}
	
//	public synchronized void realizaPouso(Aviao aviao) throws InterruptedException {		
//
//
//		Portao p = existePortaoDisponivel();
//		
//		while (this.isDisponivel() == false) {
//			wait();
//		}
//		
//		if(p.getNumeroPortao() >= 0){
//			
//			p.setDisponivel(false);
//			
//			aviao.setPortao(p);
//			this.avioesDesembarcando.add(aviao);
//			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//			System.out.println("| Avião " + aviao.getId() + " pousou. Portão de desembarque: " + aviao.getPortao().getNumeroPortao() + " |");
//			System.out.println("| Portão de desembarque: " + aviao.getPortao().getNumeroPortao() + " |");
//			System.out.println("| Situação: " + aviao.getSituacaoTexto() + " |");
//			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//
//			if (this.avioesDesembarcando.size() >= qtdPortoes) {
//				this.setDisponivel(false);
//			}
//			
//		} else {
//			System.out.println("Não há portões disponíveis.");
//		}
//		
//		notifyAll();
//	}
	
//	public synchronized void realizaDecolagem(Aviao aviao) {
//		this.avioesDesembarcando.remove(aviao);
//		this.portoes.get(aviao.getPortao().getNumeroPortao()).setDisponivel(true);
//		System.out.println("-----------------------------------------------------------------------------------------------");
//		System.out.println("| Avião " + aviao.getId() + " decolou." + " |");
//		System.out.println("| Portão de embarque: " + aviao.getPortao().getNumeroPortao() + " |");
//		System.out.println("-----------------------------------------------------------------------------------------------");
//		this.setDisponivel(true);
//		notifyAll();
//	}
	
	public synchronized void realizaDecolagemNova(Aviao aviao) throws InterruptedException {
		
		if(aviao.getStatus() == 2){
			this.avioesDesembarcando.remove(aviao);
			this.portoes.get(aviao.getPortao().getNumeroPortao()).setDisponivel(true);
			
			System.out.println("-----------------------------------------------------------------------------------------------");
			System.out.println("| Avião " + aviao.getId() + " decolou." + " |");
			System.out.println("| Portão de embarque: " + aviao.getPortao().getNumeroPortao() + " |");
			System.out.println("-----------------------------------------------------------------------------------------------");
			
			this.setExistePortaoDisponivel(true);
			this.setDisponivel(true);
		} else if(aviao.getStatus() == 1){
			
			while(this.existePortaoDisponivel == false){
				wait();
			}
			
			Portao p = existePortaoDisponivel();
			
			aviao.setPortao(p);
			aviao.setStatus(2);
			
			this.portoes.get(aviao.getPortao().getNumeroPortao()).setDisponivel(true);
			System.out.println("-----------------------------------------------------------------------------------------------");
			System.out.println("| Avião " + aviao.getId() + " foi para o Portão " + aviao.getPortao().getNumeroPortao() + " |");
			System.out.println("| Avião " + aviao.getId() + " decolou." + " |");
			System.out.println("| Portão de embarque: " + aviao.getPortao().getNumeroPortao() + " |");
			System.out.println("-----------------------------------------------------------------------------------------------");

			this.setExistePortaoDisponivel(true);
			this.setDisponivel(true);
		}
		notifyAll();
	}

	public Portao existePortaoDisponivel(){
		for(int i = 0 ; i < this.portoes.size() ; i++){
			if(this.portoes.get(i).isDisponivel()){
				return this.portoes.get(i);
			}
		}
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

	public int getCapacidadeEstacionamento() {
		return capacidadeEstacionamento;
	}

	public void setCapacidadeEstacionamento(int capacidadeEstacionamento) {
		this.capacidadeEstacionamento = capacidadeEstacionamento;
	}

	public List<Aviao> getAvioesEstacionados() {
		return avioesEstacionados;
	}

	public void setAvioesEstacionados(List<Aviao> avioesEstacionados) {
		this.avioesEstacionados = avioesEstacionados;
	}

	public boolean isExistePortaoDisponivel() {
		return existePortaoDisponivel;
	}

	public void setExistePortaoDisponivel(boolean existePortaoDisponivel) {
		this.existePortaoDisponivel = existePortaoDisponivel;
	}
	
}