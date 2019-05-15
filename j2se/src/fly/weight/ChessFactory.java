package fly.weight;

import java.util.HashMap;
import java.util.Map;

//享元工厂类：通过此类的方法获取白棋黑棋的引用
public class ChessFactory {
	//{ [白色, 白棋对象],[黑色, 黑棋对象] }
	private static Map<String,ChessFlyWeight> map = new HashMap<String,ChessFlyWeight>();//String -> 棋
	
	//getChessByColor(String color)：传入颜色，返回改颜色对应棋对象（单例）
	public static ChessFlyWeight getChessByColor(String color) {
		//根据颜色取棋：1.存在，2.不存在
		if(map.get(color)!=null) {//存在
			return map.get(color);
		}else {//不存在
			ConcreteChess chess = new ConcreteChess();
			chess.setColor(color);
			//加入共享池
			map.put(color, chess);
			return chess;
		}
	}
}
