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

import java.util.Map;
import org.apache.jena.rdf.model.ModelFactory;

/**
 *
 * @author Jonathan Chang, Chun-yien <ccy@musicapoetica.org>
 */
public class Settings {

  public static final String NS = "http://lib.nycu.edu.tw/ontology#";

  public static final Map<String, String> ONT_PREFIX_MAP
          = ModelFactory.createOntologyModel().getNsPrefixMap();
}
