package com.raymondgiorgi.discogs.parsers.contenthandlers;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by no-vivisimo on 3/27/2016.
 */
public class LabelContentHandler extends DiscogContentHandler {
    public class LabelXmlObject {
        private int id;
        private String name;
        private String parentLabel;
        private List<String> urls;
        private List<String> subLabels;
        private LabelXmlObject() {
            this.urls = new LinkedList<>();
            this.subLabels = new LinkedList<>();
        }

        public int getId() { return this.id; }
        public String getName() { return this.name; }
        public String getParentLabel() { return this.parentLabel; }
        public List<String> getUrls() { return this.urls; }
        public List<String> getSubLabels() { return this.subLabels; }
    }

    private LabelXmlObject lxo;

    public LabelXmlObject getLabelXmlObject() {
        return this.lxo;
    }

    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {
        lxo = new LabelXmlObject();
    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("id")) {
            lxo.id = Integer.parseInt(characterText());
        } else if (qName.equals("name")) {
            lxo.name = characterText();
        } else if (qName.equals("parentLabel")) {
            lxo.parentLabel = characterText();
        } else if (qName.equals("url")) {
            lxo.urls.add(characterText());
        } else if (qName.equals("label")) {
            lxo.subLabels.add(characterText());
        }
        wipeBuffer();
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {

    }

    @Override
    public void skippedEntity(String name) throws SAXException {

    }
}
