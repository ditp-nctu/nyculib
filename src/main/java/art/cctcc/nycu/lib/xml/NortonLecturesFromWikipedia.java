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
import java.net.ProtocolException;
import java.util.Objects;
import org.jsoup.Jsoup;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class NortonLecturesFromWikipedia {

  public static void main(String[] args) throws ProtocolException, IOException {
    var url = "https://en.wikipedia.org/wiki/Charles_Eliot_Norton_Lectures";
    var doc = Jsoup.connect(url).get();
    var table = doc.select("caption:contains(Table of lecturers and lectures held:)")
            .first();
    if (table == null || table.parent() == null)
      throw new RuntimeException("Table not exists. Possibly the website down or remodelled.");

    var lectures = table.parent().select("tbody tr");
    var iter = lectures.iterator();
    var headers = iter.next().select(".headerSort");
    System.out.println("headers = " + headers);
    while (iter.hasNext()) {
      var lecture = iter.next().select("td");
      var year = lecture.get(0).text().replace("–", "-");
      var lecturer = lecture.get(1).text();
      var title = lecture.get(2).text();
      var small = lecture.get(2).select("small").text();
      var published = lecture.size() > 3 ? lecture.get(3).text() : lecture.get(2).text();
      if (!year.matches("[0-9]{4}-[0-9]{4}")) {
        title = lecturer;
        lecturer = year;
        year = "-";
      } else {
        System.out.println("-".repeat(40));
      }
      System.out.println("year = " + year);
      System.out.println("lecturer = " + lecturer);
      System.out.println("title = " + title.replace(small, ""));
      System.out.println("published = " + published);
    }
  }
}
