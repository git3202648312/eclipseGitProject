package xml.parse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParseUtil {
	//����һ��xml�ļ�����·����xml/parse/dogs.xml��,���һ��List<dog>
	public static List<Dog> parseXmlToList(String fileName) throws ParserConfigurationException, FileNotFoundException, SAXException, IOException{
		List<Dog> dogs = new ArrayList<Dog>();
		//DOM��ʽ���������
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//��Ʒ
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		//׼����������Ϊparse()��׼��
		//����Ϊһ��������java�������document����
		Document document = builder.parse(new FileInputStream(fileName));
		Element element = document.getDocumentElement();//��ȡ�ĵ����нڵ�
		//nodeList�洢��3��<dog>
		NodeList nodeList = element.getElementsByTagName("dog");
		//����nodeList��3��dog��
		for (int i=0;i<nodeList.getLength();i++) {
			Dog dog = new Dog();
			//��ȡÿһ��<dog>
			Element dogElement = (Element)nodeList.item(i);//list.get(i)
			//node.getAttributes()[0];
			int id = Integer.parseInt(dogElement.getAttribute("id"));
			dog.setId(id);
			
			//��ȡdog���ӽڵ�
			NodeList childNodes = dogElement.getChildNodes();
			//����ÿһ���ӽڵ�
			for(int j=0;j<childNodes.getLength();j++) {
				//ÿһ���ӽڵ�<name>	(Ҳ�п��������֡��ո�)
				Node dogChild = childNodes.item(j);
				//ֻ��<xxxx>��ʽ���ӽڵ�
				if(dogChild.getNodeType()==Node.ELEMENT_NODE) {
					if(dogChild.getNodeName().equals("name")) {
						String name = dogChild.getFirstChild().getNodeValue();
						dog.setName(name);
					}else if(dogChild.getNodeName().equals("score")) {
						double score = Double.parseDouble(dogChild.getFirstChild().getNodeValue());
						dog.setScore(score);
					}else {
						int level = Integer.parseInt(dogChild.getFirstChild().getNodeValue());
						dog.setLevel(level);
					}
				}
			}
			dogs.add(dog);
		}
		return dogs;
	}
}
