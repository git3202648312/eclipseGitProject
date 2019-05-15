package fly.weight;

public class Test {
	public static void main(String[] args) {
		ChessFlyWeight chess1 = ChessFactory.getChessByColor("白色");
		ChessFlyWeight chess2 = ChessFactory.getChessByColor("白色");
		System.out.println(chess1==chess2);
		System.out.println(chess1);
		System.out.println(chess2);
		
		ChessFlyWeight chess3 = ChessFactory.getChessByColor("黑色");
		ChessFlyWeight chess4 = ChessFactory.getChessByColor("黑色");
		System.out.println(chess3==chess4);
		System.out.println(chess3);
		System.out.println(chess4);
		
		
		
		System.out.println("-----------------");
		
		Location loc1 = new Location(1,1);
		Location loc2 = new Location(2,2);
		Location loc3 = new Location(3,3);
		Location loc4 = new Location(4,4);
		chess1.display(loc1);
		chess2.display(loc2);
		chess3.display(loc3);
		chess4.display(loc4);
		
		
	}
}
