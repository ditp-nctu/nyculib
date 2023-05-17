/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package art.cctcc.nycu.lib;

import static art.cctcc.nycu.lib.Settings.NS;
import java.nio.file.Path;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import tech.metacontext.utils.SparqlQuery;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class NYCULibrary {

  private static final Map<String, String> ONT_PREFIX_MAP
          = ModelFactory.createOntologyModel().getNsPrefixMap();
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
