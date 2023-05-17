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

import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.ProtocolException;
import java.util.Arrays;
import org.jsoup.Jsoup;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class NortonLecturesFromHarvardUPress {

    public static void main(String[] args) throws ProtocolException, IOException {
        var url = "https://www.hup.harvard.edu/collection.php?cpk=1033";
        Document doc = Jsoup.connect(url).get();
        Elements books = doc.select(".bookinfo");
        for (Element book : books) {
            System.out.println("-".repeat(40));
            var title = book.select(".title").text();
            System.out.println(title);
            
            var authors = book.select(".author").html()
                    .transform(t -> Arrays.stream(t.split("<br>")).map(String::strip).toList());
            for (String author : authors) {
                System.out.println(author);
            }
            
            var desc = book.select(".desc").text();
            System.out.println("desc = " + desc);
        }
    }
}
