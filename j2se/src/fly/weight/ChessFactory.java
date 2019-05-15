package fly.weight;

import java.util.HashMap;
import java.util.Map;

//��Ԫ�����ࣺͨ������ķ�����ȡ������������
public class ChessFactory {
	//{ [��ɫ, �������],[��ɫ, �������] }
	private static Map<String,ChessFlyWeight> map = new HashMap<String,ChessFlyWeight>();//String -> ��
	
	//getChessByColor(String color)��������ɫ�����ظ���ɫ��Ӧ����󣨵�����
	public static ChessFlyWeight getChessByColor(String color) {
		//������ɫȡ�壺1.���ڣ�2.������
		if(map.get(color)!=null) {//����
			return map.get(color);
		}else {//������
			ConcreteChess chess = new ConcreteChess();
			chess.setColor(color);
			//���빲���
			map.put(color, chess);
			return chess;
		}
	}
}
