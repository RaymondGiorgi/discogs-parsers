package com.raymondgiorgi.discogs.parsers.contenthandlers;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.util.*;

/**
 * Created by no-vivisimo on 3/26/2016.
 */
public class ArtistContentHandler extends DiscogContentHandler {
    public class ArtistXmlObject {
        private int id;
        private String name;
        private String realName;
        private List<String> urls;
        private List<String> nameVariations;
        private List<String> aliases;
        private List<String> groups;
        private Map<Integer, String> members;
        private ArtistXmlObject() {
            urls = new LinkedList<>();
            nameVariations = new LinkedList<>();
            aliases = new LinkedList<>();
            groups = new LinkedList<>();
            members = new HashMap<>();
        }
        public int getId() { return this.id; }
        public String getName() { return this.name; }
        public String getRealName() { return this.realName; }
        public List<String> getUrls() { return this.urls; }
        public List<String> getNameVariations() { return this.nameVariations; }
        public List<String> getAliases() { return this.aliases; }
        public List<String> getGroups() { return this.groups; }
        public Map<Integer, String> getMembers() { return this.members; }
    }

    private enum State {INIT, NAME_VARIATIONS, ALIASES, MEMBERS, GROUPS};
    public Set<String> tagSet = new HashSet<String>();
    public StringBuilder builder = new StringBuilder();
    private State state = State.INIT;
    private ArtistXmlObject axo;
    private int currentId = -1;

    public ArtistXmlObject getArtistXmlObject() {
        return this.axo;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        switch (state) {
            case INIT:
                if (qName.equals("namevariations")) {
                    state = State.NAME_VARIATIONS;
                } else if (qName.equals("aliases")) {
                    state = State.ALIASES;
                } else if (qName.equals("members")) {
                    state = State.MEMBERS;
                } else if (qName.equals("groups")) {
                    state = State.GROUPS;
                }
                break;
            case NAME_VARIATIONS:
                break;
            case ALIASES:
                break;
            case MEMBERS:
                break;
            case GROUPS:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (state) {
            case INIT:
                if (qName.equals("id")) {
                    this.axo.id = Integer.parseInt(this.characterText());
                } else if (qName.equals("name")) {
                    this.axo.name = this.characterText();
                } else if (qName.equals("url")) {
                    this.axo.urls.add(this.characterText());
                } else if (qName.equals("realname")) {
                    this.axo.realName = this.characterText();
                }
                break;
            case NAME_VARIATIONS:
                if (qName.equals("name")) {
                    this.axo.nameVariations.add(this.characterText());
                } else if (qName.equals("namevariations")) {
                    state = State.INIT;
                }
                break;
            case ALIASES:
                if (qName.equals("name")) {
                    this.axo.aliases.add(this.characterText());
                } else if (qName.equals("aliases")) {
                    state = State.INIT;
                }
                break;
            case MEMBERS:
                if (qName.equals("name")) {
                    String name = this.characterText();
                    if (this.currentId != -1) {
                        this.axo.members.put(this.currentId, name);
                    }
                    this.currentId = -1;
                } else if (qName.equals("members")) {
                    state = State.INIT;
                } else if (qName.equals("id")) {
                    this.currentId = Integer.parseInt(this.characterText());
                }
                break;
            case GROUPS:
                if (qName.equals("name")) {
                    this.axo.groups.add(this.characterText());
                } else if (qName.equals("groups")) {
                    state = State.INIT;
                }
                break;
        }
        wipeBuffer();
    }


    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {
        this.axo = new ArtistXmlObject();
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
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {

    }

    @Override
    public void skippedEntity(String name) throws SAXException {

    }
}
