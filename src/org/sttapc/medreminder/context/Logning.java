package org.sttapc.medreminder.context;

import java.io.File;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Logning {

	public void LogForMagneticHandler(Date date, State state, int points)
			throws TransformerException {
		Document document = GenerateDocumentForMagneticHandler(date, state,
				points);
		SaveDocumentToXmlFule(document, "resources/MagneticLoggning.xml");
	}

	public void LogForMotionHandler(Date date, State state)
			throws TransformerException {
		Document document = GenerateDocumentForMotionHandler(date, state);
		SaveDocumentToXmlFule(document, "resources/MotionLogning.xml");
	}

	private void SaveDocumentToXmlFule(Document document, String filePath)
			throws TransformerException {
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(filePath));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		System.out.println("File saved!");
	}

	private Document ReadXmlFile(DocumentBuilder docBuilder, String filePath) {
		Document document;
		try {
			document = docBuilder.parse(filePath);
			return document;
		} catch (Exception e) {
			return null;

		}
	}

	private Document GenerateDocumentForMotionHandler(Date date, State state) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement;

			// root elements
			doc = ReadXmlFile(docBuilder, "resources/MotionLogning.xml");

			if (doc == null) {
				doc = docBuilder.newDocument();
				rootElement = doc.createElement("MedReminder");
				doc.appendChild(rootElement);
			} else {
				rootElement = doc.getDocumentElement();
			}

			// MagneticHandler elements
			Element MagneticHandler = doc.createElement("MotionHandler");
			rootElement.appendChild(MagneticHandler);

			// set attribute to staff element
			// Attr attr = doc.createAttribute("id");
			// attr.setValue("1");
			// MagneticHandler.setAttributeNode(attr);

			// Date elements
			Element Date = doc.createElement("Date");
			Date.appendChild(doc.createTextNode(new java.util.Date().toString()));
			MagneticHandler.appendChild(Date);

			// State elements
			Element State = doc.createElement("State");
			State.appendChild(doc.createTextNode(state.toString()));
			MagneticHandler.appendChild(State);

			return doc;
		} catch (Exception pce) {
			pce.printStackTrace();
		}
		return null;
	}

	private Document GenerateDocumentForMagneticHandler(Date date, State state,
			int points) {

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement;

			// root elements
			doc = ReadXmlFile(docBuilder, "resources/MagneticLoggning.xml");

			if (doc == null) {
				doc = docBuilder.newDocument();
				rootElement = doc.createElement("MedReminder");
				doc.appendChild(rootElement);
			} else {
				rootElement = doc.getDocumentElement();
			}

			// MagneticHandler elements
			Element MagneticHandler = doc.createElement("MagneticHandler");
			rootElement.appendChild(MagneticHandler);

			// set attribute to staff element
			// Attr attr = doc.createAttribute("id");
			// attr.setValue("1");
			// MagneticHandler.setAttributeNode(attr);

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
