package test;

import com.alibaba.fastjson.JSON;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import utils.XmlUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class XMLParseTest {
    public static void main(String[] args) {
        Document doc = null;
        try {
            FileInputStream fis = new FileInputStream("/Users/wangyuliang/Desktop/ac-menu.xml");
            byte[] b = new byte[fis.available()];
            fis.read(b);
            String str = new String(b);
            doc = DocumentHelper.parseText(str);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

        final Map<String, Object> map = XmlUtils.Dom2Map(doc);

        final Set<String> keys = map.keySet();

        for (String key : keys) {
            System.out.println(key+"\t"+map.get(key));
        }

    }
}
