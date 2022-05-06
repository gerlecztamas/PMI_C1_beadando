package beadando;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class xmlReader {
    public static ArrayList<Good> readGoodsFromXml(String filepath) {
        ArrayList<Good> goods = new ArrayList<>();
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filepath);

            Element rootElement = document.getDocumentElement();
            NodeList childNodesList = rootElement.getChildNodes();
            Node node;

            for (int i = 0; i < childNodesList.getLength(); i++) {
                node = childNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList childNodesOfUserTag = node.getChildNodes();
                    String name = "", price = "", id = "", category = "";
                    for (int j = 0; j < childNodesOfUserTag.getLength(); j++) {
                        if (childNodesOfUserTag.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            switch (childNodesOfUserTag.item(j).getNodeName()) {
                                case "name" : name = childNodesOfUserTag.item(j).getTextContent();
                                case "price" : price = childNodesOfUserTag.item(j).getTextContent();
                                case "id" : id = childNodesOfUserTag.item(j).getTextContent();
                                case "category" : category = childNodesOfUserTag.item(j).getTextContent();
                            }
                        }
                    }
                    goods.add(new Good(name, Integer.parseInt(price), Integer.parseInt(id),category));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return goods;
    }
}

