/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package art.cctcc.nycu.lib;

import static art.cctcc.nycu.lib.Settings.NS;
import java.nio.file.Path;
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
              ?s a rdfs:Class; #owl:Class ;
                ?p []
            }
            """;
  private static Consumer<QuerySolution> processor = t -> {
    System.out.println(t);
  };

  public static void main(String[] args) {

    var source_folder = Path.of("nyculib");
    var sq = new SparqlQuery(source_folder,
            String.format(queryString, NS));
    sq.processResults(processor);
  }
}
