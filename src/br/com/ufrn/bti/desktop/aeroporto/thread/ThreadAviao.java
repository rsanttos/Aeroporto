package br.com.ufrn.bti.desktop.aeroporto.thread;

import br.com.ufrn.bti.desktop.aeroporto.dominio.Aviao;

public class ThreadAviao extends Thread{
	private Aviao aviao;
	
	public ThreadAviao(Aviao aviao) {
		this.aviao = aviao;
	}
	
	public void executaFluxo() throws InterruptedException{
		aviao.pousar();
	}
	

	@Override
	public void run() {
		try {
			executaFluxo();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
