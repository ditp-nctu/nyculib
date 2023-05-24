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
package art.cctcc.nycu.lib;

import static art.cctcc.nycu.lib.Settings.NS;
import static art.cctcc.nycu.lib.Settings.ONT_PREFIX_MAP;
import java.nio.file.Path;
import java.util.Map.Entry;
import java.util.function.Consumer;
import org.apache.jena.query.QuerySolution;
import tech.metacontext.utils.SparqlQuery;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class NYCULibrary {

  private static String queryString
          = """
            PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
            PREFIX owl: <http://www.w3.org/2002/07/owl#>
            
            PREFIX : <%s>
            
            SELECT ?s ?p
            WHERE {
              ?s a rdfs:Class;
                ?p []
            }
            """;

  private static Consumer<QuerySolution> processor = t -> {
    var ite = t.varNames();
    while (ite.hasNext()) {
      var name = ite.next();
      var res = t.getResource(name).toString();
      for (Entry<String, String> entry : ONT_PREFIX_MAP.entrySet()) {
        res = res.replace(entry.getValue(), entry.getKey() + ":");
      }
      System.out.print(res + "\t");
    }
    System.out.println();
  };

  public static void main(String[] args) {

    var source_folder = Path.of("nyculib");
    var sq = new SparqlQuery(source_folder, String.format(queryString, NS));
    ONT_PREFIX_MAP.put("", NS);
    sq.processResults(processor);
  }
}
