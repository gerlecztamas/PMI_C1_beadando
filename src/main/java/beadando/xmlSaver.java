package beadando;

import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class xmlSaver {
    public static void saveUsersToXml(ArrayList<Good> goods, String filepath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("goods");
            document.appendChild(rootElement);


            for (Good good : goods) {
                Element goodElement = document.createElement("good");
                rootElement.appendChild(goodElement);

                createChildElement(document, goodElement, "name", good.getName());
                createChildElement(document, goodElement, "price",String.valueOf(good.getPrice()));
                createChildElement(document, goodElement, "id",String.valueOf(good.getId()));
                createChildElement(document, goodElement, "category", good.getCategory());
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createChildElement(Document document, Element parent, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }
}
