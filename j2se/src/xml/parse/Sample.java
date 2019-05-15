package xml.parse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Sample {
	public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		//输入一个xml文件名的路径（xml/parse/dogs.xml）,输出一个List<dog>
		List<Dog> dogs = XmlParseUtil.parseXmlToList("C:\\Users\\顾海\\Desktop\\dogs.xml");
		System.out.println(dogs);
	}
}
