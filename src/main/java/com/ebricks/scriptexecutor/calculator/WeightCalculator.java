package com.ebricks.scriptexecutor.calculator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;

public class WeightCalculator {

    private Document document;

    public WeightCalculator(Document document) {
        this.document = document;
    }

    public double getWeight(String attribute, String type) {
        NodeList nodeList = document.getElementsByTagName("*");
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeName().equals(type)) {
                Element element = (Element) node;
                hashMap.put(element.getAttribute(attribute), 1);
            }
        }
        return hashMap.size();
    }
}
