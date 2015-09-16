package insert;

/**
 * Created by shailesh on 11/9/15.
 */
import java.lang.*;
import java.net.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
public class image_download {
    public static void main(String args[]) throws Exception {

        String webUrl = "http://www.hdwallpapers.in/";
        URL url = new URL(webUrl);
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        HTMLEditorKit htmlKit = new HTMLEditorKit();
        HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
        HTMLEditorKit.Parser parser = new ParserDelegator();
        HTMLEditorKit.ParserCallback callback = htmlDoc.getReader(0);
        parser.parse(br, callback, true);

        for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.IMG); iterator.isValid(); iterator.next()) {
            AttributeSet attributes = iterator.getAttributes();
            String imgSrc = (String) attributes.getAttribute(HTML.Attribute.SRC);

            if (imgSrc != null && (imgSrc.endsWith(".jpg") || (imgSrc.endsWith(".png")) || (imgSrc.endsWith(".jpeg")) || (imgSrc.endsWith(".bmp")) || (imgSrc.endsWith(".ico")))) {
                try {
                    downloadImage(webUrl, imgSrc);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
    private static void downloadImage(String url, String imgSrc) throws IOException {
        BufferedImage image = null;
        try {
            if (!(imgSrc.startsWith("http"))) {
                url = url + imgSrc;
            } else {
                url = imgSrc;
            }
            imgSrc = imgSrc.substring(imgSrc.lastIndexOf("/") + 1);
            String imageFormat = null;
            imageFormat = imgSrc.substring(imgSrc.lastIndexOf(".") + 1);
            String imgPath = null;
            imgPath = "/home/shailesh/imagesz/" + imgSrc + "";
            URL imageUrl = new URL(url);
            image = ImageIO.read(imageUrl);
            if (image != null) {
                File file = new File(imgPath);
                ImageIO.write(image, imageFormat, file);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}