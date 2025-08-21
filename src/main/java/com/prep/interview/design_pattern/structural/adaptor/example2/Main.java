package com.prep.interview.design_pattern.structural.adaptor.example2;


import com.prep.interview.design_pattern.structural.adaptor.example2.adaptee.XMLDataFormat;
import com.prep.interview.design_pattern.structural.adaptor.example2.adaptee.XMLDataFormatImpl;
import com.prep.interview.design_pattern.structural.adaptor.example2.adapter.JSONDataFormat;
import com.prep.interview.design_pattern.structural.adaptor.example2.adapter.JSONDataFormatAdapter;

public class Main {
    public static void main(String[] args) {
        XMLDataFormat xmlDataFormat = new XMLDataFormatImpl();
        JSONDataFormat jsonDataFormat = new JSONDataFormatAdapter(xmlDataFormat);
        System.out.println(jsonDataFormat.jsonData());
    }
}
