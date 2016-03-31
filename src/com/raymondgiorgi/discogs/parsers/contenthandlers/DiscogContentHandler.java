package com.raymondgiorgi.discogs.parsers.contenthandlers;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.UnsupportedEncodingException;

/**
 * Created by no-vivisimo on 3/26/2016.
 */
public abstract class DiscogContentHandler implements ContentHandler {
    private String buffer = "";

    protected void wipeBuffer(){
        buffer = "";
    }

    protected String characterText() {
        StringBuilder builder = new StringBuilder();
        boolean foundChar = false;
        for (int i = 0; i < buffer.length(); i++) {
            char ch = buffer.charAt(i);
            if (!foundChar) {
                if (ch != ' ' && ch != '\n' && ch != '\r') {
                    foundChar = true;
                    builder.append(ch);
                }
            } else {
                builder.append(ch);
            }
        }
        return buffer.toString().trim();
    }

    @Override
    public void characters(char[] arg0, int arg1, int arg2)
            throws SAXException {
        char buf[] = new char[arg2];
        for(int i = 0; i < arg2; i++){
            buf[i] = arg0[arg1 + i];
        }
        byte bs[] = new String(buf).getBytes();
        try {
            buffer += new String(bs, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
