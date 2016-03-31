package com.raymondgiorgi.discogs.parsers;


import com.raymondgiorgi.discogs.parsers.contenthandlers.ArtistContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by no-vivisimo on 3/26/2016.
 */
public class ArtistParser {

    public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException {
        Map<String, ArtistContentHandler.ArtistXmlObject> map = new HashMap<>();
        SAXParser factory = SAXParserFactory.newInstance().newSAXParser();
        XMLReader xmlReader = factory.getXMLReader();
        ArtistContentHandler ach = new ArtistContentHandler();
        xmlReader.setContentHandler(ach);
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\no-vivisimo\\Downloads\\discogs_20160301_artists.xml"));
        reader.readLine();
        StringBuilder builder = new StringBuilder();
        while (reader.ready()) {
            String line = reader.readLine();
            builder.append(line);
            if (line.endsWith("</artist>")) {
                String total = builder.toString();
//                System.out.println(total);
                builder = new StringBuilder();
                xmlReader.parse(new InputSource(new ByteArrayInputStream(total.getBytes("UTF-8"))));
                map.put(ach.getArtistXmlObject().getName(), ach.getArtistXmlObject());
                if (ach.getArtistXmlObject().getName().equals("Nine Inch Nails")) {
                    System.currentTimeMillis();
                }
            }
        }

    }
}
