package br.com.ufrn.bti.desktop.aeroporto.dominio;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Portao {
	private int numeroPortao;
	private boolean disponivel;
	private Aviao aviao;
	
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
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
//	public int getNumeroPortao() {
//		lock.readLock().lock();
//		try{
//			return this.numeroPortao;
//		} finally {
//			lock.readLock().unlock();
//		}
//	}
//	
//	public void setNumeroPortao(int numeroPortao) {
//		lock.writeLock().lock();
//		try{
//			this.numeroPortao = numeroPortao;			
//		}finally {
//			lock.writeLock().unlock();
//		}
//	}
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
}
