package com.anfengde.epub;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XmlReader {

	public List<String> getValue(String fileName, String selector) {
		List<String> list = new ArrayList<String>();
		
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document dom = docBuilder.parse(new File(fileName));
			Element root = dom.getDocumentElement();

			NodeList listOfBooks = root.getElementsByTagName(selector);
			for (int i = 0; i < listOfBooks.getLength(); i++) {
				list.add(listOfBooks.item(i).getFirstChild().getNodeValue());
			}

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}
		return list;
	}
}
