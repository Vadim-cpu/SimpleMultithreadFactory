import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

class CakeFactory {
	static Queue<Cake> shelf  = new ArrayDeque<Cake>(); // max 7
	public static void main(String[] args) throws InterruptedException {
		MomMakeFactory mom = new MomMakeFactory();
		SonCakeConsumer son = new SonCakeConsumer();
		mom.start();
		//Thread.sleep(10);
		son.start();
		
	//	mom.join();
	//	son.join();
		
	//	System.out.println( shelf);
		
		
		
		
	}

}
//Producer//////////////////
class MomMakeFactory extends Thread {
	public static Cake getCake() {
		Cake c = new Cake();
		System.out.println("COOKED >>>\n\t " + c);
		try {
			sleep(700);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
		
	}

	@Override
	public void run() {
		while(true) {
				if (CakeFactory.shelf.size() < 7) {
					CakeFactory.shelf.add(getCake() );
				}	
		}
	}
}
//Consumer/Subscriber/////////////////
class SonCakeConsumer extends Thread {
	public void consumeCake  (Cake cake) {
		System.out.println("I've just ate <<<\n\t " + cake);
		try {
			sleep(700);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void run() {
		while (true) {
			if(CakeFactory.shelf.size() > 0) {
			consumeCake(CakeFactory.shelf.poll());
			}
		}	
	}
}

///Resources////////////////
class Cake{
	static int count;
	private int weight;
	private int order;
	
	public Cake() {
		
		this.weight = 90 + new Random().nextInt(20);
		this.order = ++ count;
	}

	
	public String toString() {
		return "Cake [order=" + order + ", weight=" + weight + "g]\n";
	}
	
}