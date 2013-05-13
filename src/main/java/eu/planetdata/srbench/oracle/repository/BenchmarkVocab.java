/*******************************************************************************
 * Copyright 2013 Politecnico di Milano, Universidad Politécnica de Madrid
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *       http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *  
 * Authors: Daniele Dell'Aglio, Jean-Paul Calbimonte, Marco Balduini,
 * 			Oscar Corcho, Emanuele Della Valle
 ******************************************************************************/
package eu.planetdata.srbench.oracle.repository;

import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;

public class BenchmarkVocab  {
	private static final String baseURI = "http://www.streamreasoning.org/schema/benchmark#";
	private static final String baseGraphURI = baseURI + "timestampedGraph";
	public static final URI graphList = new URIImpl(baseURI+"graphsList");
	public static final URI hasTimestamp = new URIImpl(baseURI+"hasTimestamp");
	
	public static URI getGraphURI(long timestamp){
		return new URIImpl(baseGraphURI+timestamp);
	}
}
