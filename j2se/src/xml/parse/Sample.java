package xml.parse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Sample {
	public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		//����һ��xml�ļ�����·����xml/parse/dogs.xml��,���һ��List<dog>
		List<Dog> dogs = XmlParseUtil.parseXmlToList("C:\\Users\\�˺�\\Desktop\\dogs.xml");
		System.out.println(dogs);
	}
}
