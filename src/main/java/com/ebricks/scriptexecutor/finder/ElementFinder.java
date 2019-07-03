package com.ebricks.scriptexecutor.finder;

import com.ebricks.scriptexecutor.calculator.WeightCalculator;
import com.ebricks.scriptexecutor.generator.ElementGenerator;
import com.ebricks.scriptexecutor.main.Driver;
import com.ebricks.scriptexecutor.model.Step;
import com.ebricks.scriptexecutor.model.TapEvent;
import com.ebricks.scriptexecutor.model.UIElement;
import com.ebricks.scriptexecutor.resource.MobileDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ElementFinder {

    public static UIElement findReplayUIElement(Step step, String pageSource) throws ParserConfigurationException, IOException, SAXException {

        UIElement uiElement = step.getUiElement();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        StringReader stringReader = new StringReader(pageSource);
        InputSource inputSource = new InputSource(stringReader);
        Document document = documentBuilder.parse(inputSource);

        WeightCalculator weightCalculator = new WeightCalculator(document);
        double textWeight = weightCalculator.getWeight("text", uiElement.getType());
        double resourceIdWeight = weightCalculator.getWeight("resource-id", uiElement.getType());
        double packageWeight = weightCalculator.getWeight("package", uiElement.getType());
        double contentDescWeight = weightCalculator.getWeight("content-desc", uiElement.getType());
        double checkableWeight = weightCalculator.getWeight("checkable", uiElement.getType());
        double checkedWeight = weightCalculator.getWeight("checked", uiElement.getType());
        double clickableWeight = weightCalculator.getWeight("clickable", uiElement.getType());
        double enabledWeight = weightCalculator.getWeight("enabled", uiElement.getType());
        double focusableWeight = weightCalculator.getWeight("focusable", uiElement.getType());
        double focusedWeight = weightCalculator.getWeight("focused", uiElement.getType());
        double scrollableWeight = weightCalculator.getWeight("scrollable", uiElement.getType());
        double longClickableWeight = weightCalculator.getWeight("long-clickable", uiElement.getType());
        double passwordWeight = weightCalculator.getWeight("password", uiElement.getType());
        double selectedWeight = weightCalculator.getWeight("selected", uiElement.getType());
        double boundsWeight = weightCalculator.getWeight("bounds", uiElement.getType());

        List<Element> elements = new ArrayList<>();
        List<Double> weights = new ArrayList<>();
        double weightOfElement;
        double largestWeight = 0.0;
        double largestTextWeightage = 0.0;
        int elementWithLargestWeight = 0;
        int countOfFoundElements = 0;
        NodeList nodeList = document.getElementsByTagName("*");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeName().equals(uiElement.getType())) {
                Element element = (Element) node;
                weightOfElement = 0.0;

                double textSimilarity = jaroDistance(element.getAttribute("text"), uiElement.getText());
                weightOfElement += textSimilarity * textWeight;

                if (textSimilarity * textWeight > largestTextWeightage) {
                    largestTextWeightage = textSimilarity * textWeight;
                }

                if (element.getAttribute("resource-id").equals(uiElement.getResourceId())) {
                    weightOfElement += resourceIdWeight;
                }

                if (element.getAttribute("package").equals(uiElement.getPkg())) {
                    weightOfElement += packageWeight;
                }

                double contentDescSimilarity = jaroDistance(element.getAttribute("content-desc"), uiElement.getContentDesc());
                weightOfElement += contentDescSimilarity * contentDescWeight;

                if (element.getAttribute("checkable").equals(uiElement.isCheckable())) {
                    weightOfElement += checkableWeight;
                }

                if (element.getAttribute("checked").equals(uiElement.isChecked())) {
                    weightOfElement += checkedWeight;
                }

                if (element.getAttribute("clickable").equals(uiElement.isClickable())) {
                    weightOfElement += clickableWeight;

                }
                if (element.getAttribute("enabled").equals(uiElement.isEnabled())) {
                    weightOfElement += enabledWeight;

                }
                if (element.getAttribute("focusable").equals(uiElement.isFocusable())) {
                    weightOfElement += focusableWeight;

                }
                if (element.getAttribute("focused").equals(uiElement.isFocused())) {
                    weightOfElement += focusedWeight;

                }
                if (element.getAttribute("scrollable").equals(uiElement.isScrollable())) {
                    weightOfElement += scrollableWeight;

                }
                if (element.getAttribute("long-clickable").equals(uiElement.isLongClickable())) {
                    weightOfElement += longClickableWeight;

                }
                if (element.getAttribute("password").equals(uiElement.isPassword())) {
                    weightOfElement += passwordWeight;
                }
                if (element.getAttribute("selected").equals(uiElement.isSelected())) {
                    weightOfElement += selectedWeight;
                }

                elements.add(element);
                weights.add(weightOfElement);

            }
        }

        for (int i = 0; i < elements.size(); i++) {

            Point p1 = new Point(
                    Integer.parseInt(elements.get(i).getAttribute("bounds").replaceAll("\\D", ",").split(",")[1]),
                    Integer.parseInt(elements.get(i).getAttribute("bounds").replaceAll("\\D", ",").split(",")[2])
            );

            Point p2 = new Point(
                    (int) ((TapEvent) step.getEvent()).getStartX(),
                    (int) ((TapEvent) step.getEvent()).getStartY()

            );
            double distance = distanceBetweenTwoPoints(p1, p2);
            double positionWeight = 1 - distance / MobileDriver.getInstance().getScreenSize();

            double positionWeightage = (positionWeight * boundsWeight) / largestTextWeightage;

            weights.set(i, weights.get(i) + positionWeightage);

            countOfFoundElements++;

            if (weights.get(i) > largestWeight) {
                largestWeight = weights.get(i);
                elementWithLargestWeight = countOfFoundElements;

            }

        }

        if (elements.size() > 0)
            return ElementGenerator.generateFromDomElement(elements.get(elementWithLargestWeight - 1));
        else
            return null;
    }

    public static double jaroDistance(String s, String t) {
        int s_len = s.length();
        int t_len = t.length();

        if (s_len == 0 && t_len == 0) return 1;

        int match_distance = Integer.max(s_len, t_len) / 2 - 1;

        boolean[] s_matches = new boolean[s_len];
        boolean[] t_matches = new boolean[t_len];

        int matches = 0;
        int transpositions = 0;

        for (int i = 0; i < s_len; i++) {
            int start = Integer.max(0, i - match_distance);
            int end = Integer.min(i + match_distance + 1, t_len);

            for (int j = start; j < end; j++) {
                if (t_matches[j]) continue;
                if (s.charAt(i) != t.charAt(j)) continue;
                s_matches[i] = true;
                t_matches[j] = true;
                matches++;
                break;
            }
        }

        if (matches == 0) return 0;

        int k = 0;
        for (int i = 0; i < s_len; i++) {
            if (!s_matches[i]) continue;
            while (!t_matches[k]) k++;
            if (s.charAt(i) != t.charAt(k)) transpositions++;
            k++;
        }

        return (((double) matches / s_len) +
                ((double) matches / t_len) +
                (((double) matches - transpositions / 2.0) / matches)) / 3.0;
    }

    public static double distanceBetweenTwoPoints(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    public static UIElement findByXandYCoordinates(double x, double y, String pageSource) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        StringReader stringReader = new StringReader(pageSource);
        InputSource inputSource = new InputSource(stringReader);
        Document document = documentBuilder.parse(inputSource);

        // this list will contain all the elements in the ranges of which, the x and y coordinates fall
        System.out.println("Screen coordinates (height): " + MobileDriver.getInstance().getDriver().manage().window().getSize().height);
        System.out.println("Screen coordinates (height): " + MobileDriver.getInstance().getDriver().manage().window().getSize().width);
        List<Element> foundElements = findAllWithXandYCoordinates(x, y, document);

        // here we will find the element with smallest width and height in the found elements:
        // ------------------------------------------------------------------------------------

        // getting the width and height of first element in the list to compare with the next ones
        Element requiredElement = foundElements.get(0);
        String bounds[] = requiredElement.getAttribute("bounds").replaceAll("\\D", ",").split(",");
        int smallestWidht = Integer.parseInt(bounds[4]);
        int smallestHeight = Integer.parseInt(bounds[5]);

        for (Element element : foundElements) {
            int boundWidth = Integer.parseInt(element.getAttribute("bounds").replaceAll("\\D", ",").split(",")[4]);
            int boundHeight = Integer.parseInt(element.getAttribute("bounds").replaceAll("\\D", ",").split(",")[5]);

            // checking if we found a smaller element than the previous one
            if (boundWidth <= smallestWidht && boundHeight <= smallestHeight) {
                smallestWidht = boundWidth;
                smallestHeight = boundHeight;
                requiredElement = element;
            }
        }

        // now the requied element is found
        // now we need to convert it into a UIElement and return that UIElement
        return ElementGenerator.generateFromDomElement(requiredElement);
    }

    public static List<Element> findAllWithXandYCoordinates(double x, double y, Document document) {
        List<Element> foundElements = new ArrayList<Element>();

        NodeList nodeList = document.getElementsByTagName("*");
        System.out.println("Length of nodelist: " + nodeList.getLength());
        for (int i = 1; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            String bounds = element.getAttribute("bounds");
            bounds = bounds.replaceAll("\\D", ",");
            String[] splitted = bounds.split(",");

            int boundX1 = Integer.parseInt(splitted[1]);
            int boundY1 = Integer.parseInt(splitted[2]);
            int boundX2 = Integer.parseInt(splitted[4]);
            int boundY2 = Integer.parseInt(splitted[5]);
            if (x >= boundX1 && y >= boundY1 && x <= boundX2 && y <= boundY2) {
                // adding an element in the list as x and y coordinates are found to be in its range
                foundElements.add(element);
            }
        }
        System.out.println("length of found elements: " + foundElements.size());
        return foundElements;
    }

    public static Point2D.Double relativeXandY(Step step) {
        String bounds = step.getUiElement().getBounds();
        int relativeX = (int) ((TapEvent) step.getEvent()).getStartX() - Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[1]);
        int relativeY = (int) ((TapEvent) step.getEvent()).getStartY() - Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[2]);

        int width = Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[4]) - Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[1]);
        int height = Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[5]) - Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[2]);

        double percetageX = ((double) relativeX / width) * 100;
        double percetageY = ((double) relativeX / width) * 100;

        return new Point2D.Double(percetageX, percetageY);
    }

    public static String findBoundsInReplayUIElement(UIElement uiElement, String pageSource) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        StringReader stringReader = new StringReader(pageSource);
        InputSource inputSource = new InputSource(stringReader);
        Document document = documentBuilder.parse(inputSource);

        NodeList nodeList = document.getElementsByTagName("*");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeName().toString().equals(uiElement.getType())) {
                Element element = (Element) node;
                if (element.getAttribute("text").equals(uiElement.getText())
                        && element.getAttribute("package").equals(uiElement.getPkg())
                        && element.getAttribute("resource-id").equals(uiElement.getResourceId())) {

                    return element.getAttribute("bounds");
                }
            }
        }
        return null;
    }

    public static Point findValuesFromPercentage(Point2D.Double point, String bounds) {
        int width = Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[4]) - Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[1]);
        int height = Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[5]) - Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[2]);

        int relativeX = (int) (width * (point.getX() / 100));
        int relativeY = (int) (height * (point.getY() / 100));

        int boundX = Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[1]);
        int boundY = Integer.parseInt(bounds.replaceAll("\\D", ",").split(",")[2]);
        return new Point(boundX + relativeX, boundY + relativeY);
    }
}
