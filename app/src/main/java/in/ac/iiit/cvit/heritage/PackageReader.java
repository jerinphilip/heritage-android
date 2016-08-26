package in.ac.iiit.cvit.heritage;

import android.os.Environment;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class PackageReader {

    public String _packageName;
    InterestPoint IP;
    ArrayList<InterestPoint> InterestPoints;

    public static final String LOGTAG = "Heritage";

    PackageReader(String packageName){
        _packageName = packageName;
        InterestPoints = new ArrayList<InterestPoint>();
    }

    ArrayList<InterestPoint> getContents(){
        return InterestPoints;
    }

    void readContentsFromString(String xml){
        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            /*
                Following snippet taken from:
                http://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
             */
            builder = factory.newDocumentBuilder();
            StringBuilder xmlStringBuilder = new StringBuilder();
            xmlStringBuilder.append(xml);
            ByteArrayInputStream xmlfile = null;
            try {
                xmlfile = new ByteArrayInputStream(
                        xmlStringBuilder.toString().getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            Document doc = null;
            try {
                doc = builder.parse(xmlfile);
                Element root = doc.getDocumentElement();
                // Log.e("PackageReader", "Let's see");
                // Log.d("PackageReader", root.getNodeName());
                NodeList ips = root.getChildNodes();
                for(int i=0; i<ips.getLength(); i++){
                    // If to remove random #text nodes appearing.
                    if(ips.item(i).getNodeType() == Node.ELEMENT_NODE){
                        // Log.d("PackageReader", ips.item(i).getNodeName());
                        IP = new InterestPoint();
                        NodeList keys = ips.item(i).getChildNodes();
                        for(int j=0; j<keys.getLength(); j++){
                            if(keys.item(j).getNodeType() == Node.ELEMENT_NODE){
                                Element key = (Element)keys.item(j);
                                // Log.d("PackageReader:ip", "("+key.getNodeName()+","+key.getTextContent()+")");
                                IP.set(key.getNodeName(), key.getTextContent());
                            }
                        }
                        InterestPoints.add(IP);
                    }
                }
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    void readFromFile(){
        File baseLocal = Environment.getExternalStorageDirectory();

        File xmlfile = new File(baseLocal, "heritage/extracted/" + _packageName + "d.xml");
        try {
            FileInputStream xmlStream = new FileInputStream(xmlfile);
            String contents = readTextFile(xmlStream);
            readContentsFromString(contents);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }
}
