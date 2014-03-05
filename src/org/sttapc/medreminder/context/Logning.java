package org.sttapc.medreminder.context;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.sttapc.medreminder.util.Configurator;

public class Logning {

	public void LogForMagneticHandler(Date date, State state, int points) throws TransformerException {
		Document document = GenerateDocumentForMagneticHandler(date, state, points);
		SaveDocumentToXmlFule(document);
	}

	private void SaveDocumentToXmlFule(Document document)
			throws TransformerException {
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File("resources/MagneticLoggning.xml"));

		// Output to console for testing
		//StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		System.out.println("File saved!");
	}
	
//	private Element ReadXmlFile(DocumentBuilder docBuilder){
//        Document document;
//		try {
//			document = docBuilder.parse("resources/MagneticLoggning.xml");
//			return document.getDocumentElement();
//		} catch (Exception e) {
//			return null;
//
//		}
//	}

	private Document GenerateDocumentForMagneticHandler(Date date, State state, int points) {
		
		try {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			// root elements
//			Element rootElement = ReadXmlFile(docBuilder);
			
//			if(rootElement == null){
//				rootElement= doc.createElement("MedReminder");
//			}
			Element rootElement = doc.createElement("MedReminder");
			doc.appendChild(rootElement);

			// MagneticHandler elements
			Element MagneticHandler = doc.createElement("MagneticHandler");
			rootElement.appendChild(MagneticHandler);

//			set attribute to staff element
//			Attr attr = doc.createAttribute("id");
//			attr.setValue("1");
//			MagneticHandler.setAttributeNode(attr);

			// Date elements
			Element Date = doc.createElement("Date");
			Date.appendChild(doc.createTextNode(new java.util.Date().toString()));
			MagneticHandler.appendChild(Date);

			// State elements
			Element State = doc.createElement("State");
			State.appendChild(doc.createTextNode(state.toString()));
			MagneticHandler.appendChild(State);

			// Points elements
			Element Points = doc.createElement("Points");
			Points.appendChild(doc.createTextNode(String.valueOf(points)));
			MagneticHandler.appendChild(Points);
			return doc;
		} catch (Exception pce) {
			pce.printStackTrace();
		}
		return null;

	}
}
