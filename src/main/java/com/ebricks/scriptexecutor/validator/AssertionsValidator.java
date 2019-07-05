package com.ebricks.scriptexecutor.validator;

import com.ebricks.scriptexecutor.model.Event;
import com.ebricks.scriptexecutor.model.UIElement;
import com.ebricks.scriptexecutor.model.uda.BaseResult;
import com.ebricks.scriptexecutor.model.uda.Result;
import com.ebricks.scriptexecutor.model.uda.Uda;
import com.ebricks.scriptexecutor.resource.MobileDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class AssertionsValidator {

    public static Logger logger = LogManager.getLogger(AssertionsValidator.class);

    public static Uda validateTextEquals(Uda uda, UIElement uiElement) {

        if(uiElement != null){

            String bounds = uiElement.getBounds();
            bounds = bounds.replaceAll("\\D", ",");
            String[] splitted = bounds.split(",");

            int boundX1 = Integer.parseInt(splitted[1]);
            int boundY1 = Integer.parseInt(splitted[2]);
            int boundX2 = Integer.parseInt(splitted[4]);
            int boundY2 = Integer.parseInt(splitted[5]);

            if(uda.getValue().equals(uiElement.getText())){
                logger.info("Asserted text matched");

                //-----------creating uda---------------------------------------
                uda.setResult(new Result());
                uda.getResult().setSuccess(true);
                uda.getResult().setStatus("SUCCESS");
                uda.getResult().setValue("");

                uda.getResult().setBase(new BaseResult());
                uda.getResult().getBase().setPosition(new ArrayList<>());
                uda.getResult().getBase().getPosition().add(boundX1);
                uda.getResult().getBase().getPosition().add(boundY1);
                uda.getResult().getBase().setSize(new ArrayList<>());
                uda.getResult().getBase().getSize().add(boundX2-boundX1);
                uda.getResult().getBase().getSize().add(boundY2-boundY1);
                uda.getResult().getBase().setValue(uda.getValue());
                uda.getResult().getBase().setDisplay(true);

                uda.getResult().setResult(new BaseResult());
                uda.getResult().getResult().setPosition(new ArrayList<>());
                uda.getResult().getResult().getPosition().add(boundX1);
                uda.getResult().getResult().getPosition().add(boundY1);
                uda.getResult().getResult().setSize(new ArrayList<>());
                uda.getResult().getResult().getSize().add(boundX2-boundX1);
                uda.getResult().getResult().getSize().add(boundY2-boundY1);
                uda.getResult().getResult().setValue(uiElement.getText());
                uda.getResult().getResult().setDisplay(true);
                //--------------------------------------------------------------

            }
            else{
                logger.error("Asserted text did not match");

                //---------------creating uda-----------------------------------
                uda.setResult(new Result());
                uda.getResult().setSuccess(false);
                uda.getResult().setStatus("FAILED");
                uda.getResult().setValue("This should equal to " + uda.getValue());

                uda.getResult().setBase(new BaseResult());
                uda.getResult().getBase().setPosition(new ArrayList<>());
                uda.getResult().getBase().getPosition().add(boundX1);
                uda.getResult().getBase().getPosition().add(boundY1);
                uda.getResult().getBase().setSize(new ArrayList<>());
                uda.getResult().getBase().getSize().add(boundX2-boundX1);
                uda.getResult().getBase().getSize().add(boundY2-boundY1);
                uda.getResult().getBase().setValue(uda.getValue());
                uda.getResult().getBase().setDisplay(true);

                uda.getResult().setResult(new BaseResult());
                uda.getResult().getResult().setPosition(new ArrayList<>());
                uda.getResult().getResult().getPosition().add(boundX1);
                uda.getResult().getResult().getPosition().add(boundY1);
                uda.getResult().getResult().setSize(new ArrayList<>());
                uda.getResult().getResult().getSize().add(boundX2-boundX1);
                uda.getResult().getResult().getSize().add(boundY2-boundY1);
                uda.getResult().getResult().setValue(uiElement.getText());
                uda.getResult().getResult().setDisplay(true);
                //--------------------------------------------------------------
            }

            return uda;
        }
        else{
            logger.error("Element with the specified udaId was not found");
            return null;
        }
    }

    public static Uda validateXPathExistence(Uda uda) throws IOException {
        if( MobileDriver.getInstance().getDriver().findElementsByXPath(uda.getValue()) != null ){
            logger.info("Asserted XPATH exists");

            //-----------creating uda---------------------------------------
            uda.setResult(new Result());
            uda.getResult().setSuccess(true);
            uda.getResult().setStatus("SUCCESS");
            uda.getResult().setValue("");
            uda.getResult().setBase(null);
            uda.getResult().setResult(null);
            //--------------------------------------------------------------
        }
        else{
            logger.error("Asserted XPATH does not exist");

            //-----------creating uda---------------------------------------
            uda.setResult(new Result());
            uda.getResult().setSuccess(false);
            uda.getResult().setStatus("FAILED");
            uda.getResult().setValue("XPATH should exist " + uda.getValue());
            uda.getResult().setBase(null);
            uda.getResult().setResult(null);
            //--------------------------------------------------------------
        }
        return uda;
    }

    public static Uda validateContainVariable(Uda uda, UIElement uiElement){

        String bounds = uiElement.getBounds();
        bounds = bounds.replaceAll("\\D", ",");
        String[] splitted = bounds.split(",");

        int boundX1 = Integer.parseInt(splitted[1]);
        int boundY1 = Integer.parseInt(splitted[2]);
        int boundX2 = Integer.parseInt(splitted[4]);
        int boundY2 = Integer.parseInt(splitted[5]);

        if(uda.getName().equals(uiElement.getText())){
            logger.info("Dynamically asserted variable element's text matched in replay");

            //-----------creating uda---------------------------------------
            uda.setResult(new Result());
            uda.getResult().setSuccess(true);
            uda.getResult().setStatus("SUCCESS");
            uda.getResult().setValue("");

            uda.getResult().setBase(new BaseResult());
            uda.getResult().getBase().setPosition(new ArrayList<>());
            uda.getResult().getBase().getPosition().add(boundX1);
            uda.getResult().getBase().getPosition().add(boundY1);
            uda.getResult().getBase().setSize(new ArrayList<>());
            uda.getResult().getBase().getSize().add(boundX2-boundX1);
            uda.getResult().getBase().getSize().add(boundY2-boundY1);
            uda.getResult().getBase().setValue(uda.getValue());
            uda.getResult().getBase().setDisplay(true);

            uda.getResult().setResult(new BaseResult());
            uda.getResult().getResult().setPosition(new ArrayList<>());
            uda.getResult().getResult().getPosition().add(boundX1);
            uda.getResult().getResult().getPosition().add(boundY1);
            uda.getResult().getResult().setSize(new ArrayList<>());
            uda.getResult().getResult().getSize().add(boundX2-boundX1);
            uda.getResult().getResult().getSize().add(boundY2-boundY1);
            uda.getResult().getResult().setValue(uiElement.getText());
            uda.getResult().getResult().setDisplay(true);
        }
        else{
            logger.error("Element's, with dynamically asserted variable, text did not match in replay");

            //---------------creating uda-----------------------------------
            uda.setResult(new Result());
            uda.getResult().setSuccess(false);
            uda.getResult().setStatus("FAILED");
            uda.getResult().setValue("This should equal to " + uda.getName());

            uda.getResult().setBase(new BaseResult());
            uda.getResult().getBase().setPosition(new ArrayList<>());
            uda.getResult().getBase().getPosition().add(boundX1);
            uda.getResult().getBase().getPosition().add(boundY1);
            uda.getResult().getBase().setSize(new ArrayList<>());
            uda.getResult().getBase().getSize().add(boundX2-boundX1);
            uda.getResult().getBase().getSize().add(boundY2-boundY1);
            uda.getResult().getBase().setValue(uda.getValue());
            uda.getResult().getBase().setDisplay(true);

            uda.getResult().setResult(new BaseResult());
            uda.getResult().getResult().setPosition(new ArrayList<>());
            uda.getResult().getResult().getPosition().add(boundX1);
            uda.getResult().getResult().getPosition().add(boundY1);
            uda.getResult().getResult().setSize(new ArrayList<>());
            uda.getResult().getResult().getSize().add(boundX2-boundX1);
            uda.getResult().getResult().getSize().add(boundY2-boundY1);
            uda.getResult().getResult().setValue(uiElement.getText());
            uda.getResult().getResult().setDisplay(true);
            //--------------------------------------------------------------
        }
        return uda;
    }
}