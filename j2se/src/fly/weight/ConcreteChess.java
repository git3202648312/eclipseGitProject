package fly.weight;

public class ConcreteChess implements ChessFlyWeight{

	private String color;
	
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public void display(Location loc) {
		System.out.println("��ɫ��"+getColor()+",λ�ã�"+loc.getX()+","+loc.getY());
	}

}
