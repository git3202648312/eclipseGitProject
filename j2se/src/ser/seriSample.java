package ser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class seriSample{
	
	public static void writeObject() throws FileNotFoundException, IOException {
		Person per = new Person("zs",23);
		Person per1 = new Person("ls",24);
		Person per2 = new Person("ww",25);
		List<Person> pers = new ArrayList<Person>();
		pers.add(per);
		pers.add(per1);
		pers.add(per2);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("F:\\pers.obj"));
		oos.writeObject(pers);
		oos.close();
	}
	
	public static void readObject() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("F:\\pers.obj"));
		List<Person> pers = (List<Person>) ois.readObject();
		for (Person per : pers) {
			System.out.println(per);
		}
		ois.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		writeObject();
		readObject();
	}
}
