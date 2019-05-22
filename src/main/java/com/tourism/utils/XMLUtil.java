/**
 * 
 */
package com.tourism.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Sang-Hyup Lee
 * @since 2012.06.21
 *
 */
public class XMLUtil {


	/**
	 * 
	 * @param currentNode
	 * @param tagName
	 * @return
	 */
	public static String getTextContentByTagName(Node currentNode, String tagName) {
		String result = "";
		
		NodeList childNodeList = currentNode.getChildNodes();
		for ( int i=0; i < childNodeList.getLength(); i++ ) {
			Node childNode = childNodeList.item(i);
			if ( childNode.getNodeName().equals(tagName) ) {
				result = childNode.getTextContent();
				break;
			}
		}
		return result;
	}
	
	public static ArrayList<HashMap<String,String>> getWhoList(Node currentNode, String tagName) {
		String result = "";
		ArrayList<HashMap<String,String>> whoArrayList  = new ArrayList<HashMap<String,String>>();
		NodeList childNodeList = currentNode.getChildNodes();
		for ( int i=0; i < childNodeList.getLength(); i++ ) {
			Node childNode = childNodeList.item(i);
			if ( childNode.getNodeName().equals(tagName) ) {
				HashMap<String,String> whoMap = new HashMap<String,String>();
				NamedNodeMap attrNodeMap = childNode.getAttributes();
				Node activity = attrNodeMap.getNamedItem("activity");
				Node email = attrNodeMap.getNamedItem("email");
				Node name = attrNodeMap.getNamedItem("name");
				if (activity != null) {
					whoMap.put("activity", activity.getNodeValue());
				}
				if (email != null) {
					whoMap.put("email", email.getNodeValue());
				}
				if (name != null) {
					whoMap.put("name", name.getNodeValue());
				}
				whoArrayList.add(whoMap);
			}
		}
		return whoArrayList;
	}
	
	
	
	/**
	 * 
	 * @param currentNode
	 * @param tagName
	 * @param attributeValue
	 * @return
	 */
	public static String getTextContentByElementNameANDAttributeValue(Node currentNode,
																	String tagName,
																	String attributeValue) {
		String result = "";
		
		NodeList childNodeList = currentNode.getChildNodes();
		for ( int i=0; i < childNodeList.getLength(); i++ ) {
			Node childNode = childNodeList.item(i);
			
			switch ( childNode.getNodeType() ) {
				case Node.DOCUMENT_NODE : 
					break;
				case Node.ELEMENT_NODE :
					Element childElement = (Element)childNodeList.item(i);
					// logger.debug("childElement name : " + childElement.getTagName());
					if ( childElement != null && childElement.getNodeName().equals(tagName) ) {
						NamedNodeMap attributes = childElement.getAttributes();
						for ( int j=0; j < attributes.getLength(); j++ ) {
							Node current = attributes.item(j);
					
							if ( current.getNodeName().equals("type") && current.getNodeValue().equals(attributeValue) ) {
								result = childElement.getTextContent();
								break;
							}
						}
					}
				case Node.TEXT_NODE :
				// logger.debug("textElement name : " + currentNode.getNodeValue());
					break;
				case Node.COMMENT_NODE :
					break;
				case Node.PROCESSING_INSTRUCTION_NODE :
					break;
				case Node.ENTITY_REFERENCE_NODE :
					break;
				case Node.DOCUMENT_TYPE_NODE :
					break;
			}
		}

		return result;
	}
	
	/**
	 * 
	 * @param currentNode
	 * @param tagName
	 * @param attributeName
	 * @return
	 */
	public static String getAttributeValueByTagNameANDAttributeName(Node currentNode,
																String tagName,
																String attributeName) {
		String result = "";
		
		if ( currentNode != null && currentNode.getNodeName().equals(tagName) ) {
			NamedNodeMap attributes = currentNode.getAttributes();
			for ( int j=0; j < attributes.getLength(); j++ ) {
				Node current = attributes.item(j);
		
				if ( current.getNodeName().equals(attributeName) ) {
					result = current.getTextContent();
					break;
				}
			}
		}
		
		/*
		NodeList childNodeList = currentNode.getChildNodes();
		for ( int i=0; i < childNodeList.getLength(); i++ ) {
			Node childNode = childNodeList.item(i);
			
			if ( childNode != null && childNode.getNodeName().equals(tagName) ) {
				NamedNodeMap attributes = childNode.getAttributes();
				for ( int j=0; j < attributes.getLength(); j++ ) {
					Node current = attributes.item(j);
			
					if ( current.getNodeName().equals(attributeName) ) {
						result = current.getTextContent();
						break;
					}
				}
			}
			
			switch ( childNode.getNodeType() ) {
				case Node.DOCUMENT_NODE : 
					break;
				case Node.ELEMENT_NODE :
					Element childElement = (Element)childNodeList.item(i);
					// logger.debug("childElement name : " + childElement.getTagName());
					if ( childElement != null && childElement.getNodeName().equals(tagName) ) {
						NamedNodeMap attributes = childElement.getAttributes();
						for ( int j=0; j < attributes.getLength(); j++ ) {
							Node current = attributes.item(j);
					
							if ( current.getNodeName().equals(attributeName) ) {
								result = current.getTextContent();
								break;
							}
						}
					}
				case Node.TEXT_NODE :
				// logger.debug("textElement name : " + currentNode.getNodeValue());
					break;
				case Node.COMMENT_NODE :
					break;
				case Node.PROCESSING_INSTRUCTION_NODE :
					break;
				case Node.ENTITY_REFERENCE_NODE :
					break;
				case Node.DOCUMENT_TYPE_NODE :
					break;
			}
		}
		*/
		
		return result;
	}
}
