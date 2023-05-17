/*
 * Copyright 2023 Jonathan Chang, Chun-yien <ccy@musicapoetica.org>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package art.cctcc.nycu.lib.xml;

import java.util.Arrays;
import static java.util.function.Predicate.not;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class XMLReader {

  public static void read_xml() {

    var source = "norton lectures.xml";
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(source);

      doc.getDocumentElement().normalize();
      System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
      NodeList nodeList = doc.getElementsByTagName("tr");
      for (int i = 0; i < nodeList.getLength(); i++) {
        System.out.println("----------------------------");
        System.out.println("No." + i);
        Element element = (Element) nodeList.item(i);
        var bookinfo = (Element) element.getElementsByTagName("td").item(2);

        var title = bookinfo.getElementsByTagName("p").item(0).getTextContent().trim();
        System.out.println("title = " + title);

        var authors = Arrays.stream(bookinfo.getElementsByTagName("p").item(1).getTextContent().split("\n"))
                .map(String::trim)
                .filter(not(String::isBlank))
                .toList();
        System.out.println("author = " + authors.stream().collect(Collectors.joining("; ")));

        var description = bookinfo.getElementsByTagName("div").item(0).getTextContent().trim();
        System.out.println("description = " + description);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
