package com.raymondgiorgi.discogs.parsers;

import com.raymondgiorgi.discogs.parsers.contenthandlers.ArtistContentHandler;
import com.raymondgiorgi.discogs.parsers.contenthandlers.LabelContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by no-vivisimo on 3/28/2016.
 */
public class LabelParser {
    public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException {
        Map<String, LabelContentHandler.LabelXmlObject> map = new HashMap<>();
        SAXParser factory = SAXParserFactory.newInstance().newSAXParser();
        XMLReader xmlReader = factory.getXMLReader();
        LabelContentHandler ach = new LabelContentHandler();
        xmlReader.setContentHandler(ach);
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\no-vivisimo\\Downloads\\discogs_20160301_labels.xml"));
        reader.readLine();
        StringBuilder builder = new StringBuilder();
        while (reader.ready()) {
            String line = reader.readLine();
            builder.append(line);
            if (line.endsWith("</label>")) {
                String total = builder.toString();
//                System.out.println(total);
                builder = new StringBuilder();
                xmlReader.parse(new InputSource(new ByteArrayInputStream(total.getBytes("UTF-8"))));
                map.put(ach.getLabelXmlObject().getName(), ach.getLabelXmlObject());
                if (ach.getLabelXmlObject().getName().equals("Atlantic")) {
                    System.currentTimeMillis();
                }
            }
        }

    }
}
