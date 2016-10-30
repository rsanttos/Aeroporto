package br.com.ufrn.bti.desktop.aeroporto.main;

import br.com.ufrn.bti.desktop.aeroporto.dominio.Aeroporto;
import br.com.ufrn.bti.desktop.aeroporto.dominio.Aviao;
import br.com.ufrn.bti.desktop.aeroporto.thread.ThreadAviao;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Aeroporto aeroporto = new Aeroporto("Aero1", 5);
		
		for(int i = 0 ; i < 50 ; i++){
			double l = Math.random() * 3;
			int situacao = (int) l;
			situacao++;
			Aviao aviao = new Aviao(aeroporto, i, situacao);
			ThreadAviao tAviao = new ThreadAviao(aviao);
			tAviao.setPriority(aviao.getSituacao());
			tAviao.start();
		}
		
//		int i = 0;
//		while(true){
//			double l = Math.random() * 3;
//			int situacao = (int) l;
//			situacao++;
//			Aviao aviao = new Aviao(aeroporto, i, situacao);
//			ThreadAviao tAviao = new ThreadAviao(aviao);
//			tAviao.setPriority(aviao.getSituacao());
//			tAviao.start();
//			i++;
//		}
		
	}

}
