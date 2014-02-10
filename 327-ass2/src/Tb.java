import java.util.concurrent.locks.*;

public class Tb extends Thread {
	Lock lockA; 
	Condition cA , cB, c1; 
	boolean AB0turn[]; 
	public Tb (Lock lA, Condition ca, Condition cb, 
			Condition c1, boolean abo[]) { 
		lockA = lA; this.cA=ca; cB=cb; this.c1=c1; AB0turn = abo; 
	} 
	public void run() { 
		for (int k = 0; k<5;k++){ 
			lockA.lock(); 
			try{ 
				if (!AB0turn[1]) { 
					cB.await(); 
				} 
				System.out.print("B"); 
				AB0turn[1]=false; 
				AB0turn[2]=true; 
				c1.signal(); 
			} catch (InterruptedException e) { 
				System.exit(-1); 
			} finally { 
				lockA.unlock(); 
			} 
		} 
	}
}
