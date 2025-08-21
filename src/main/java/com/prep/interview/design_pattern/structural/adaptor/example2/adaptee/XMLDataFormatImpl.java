package com.prep.interview.design_pattern.structural.adaptor.example2.adaptee;

public class XMLDataFormatImpl implements XMLDataFormat {

    @Override
    public String xmlData() {
        return "XML";
    }
}
