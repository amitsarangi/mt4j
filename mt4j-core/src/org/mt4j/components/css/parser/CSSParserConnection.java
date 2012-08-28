/***********************************************************************
*   MT4j Copyright (c) 2008 - 2012, C.Ruff, Fraunhofer-Gesellschaft All rights reserved.
*
*   This file is part of MT4j.
*
*   MT4j is free software: you can redistribute it and/or modify
*   it under the terms of the GNU Lesser General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   MT4j is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
*   GNU Lesser General Public License for more details.
*
*   You should have received a copy of the GNU Lesser General Public License
*   along with MT4j.  If not, see <http://www.gnu.org/licenses/>.
*
************************************************************************/
package org.mt4j.components.css.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.batik.css.parser.Parser;
import org.mt4j.AbstractMTApplication;
import org.mt4j.components.css.style.CSSStyle;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.InputSource;


// TODO: Auto-generated Javadoc
/**
 * The Class CSSParserConnection.
 */
public class CSSParserConnection {
	
	/** The Parser. */
	Parser pa = null;
	
	/** The file reader. */
	FileReader fileReader = null;
	
	/** The CSSHandler, which contains the parsing instructions. */
	CSSHandler cssh = null;
	
	
	/**
	 * Instantiates a new CSS Parser Connection with an URL as argument.
	 *
	 * @param source the source file
	 * @param app the MTApplication
	 */
	public CSSParserConnection(String source, AbstractMTApplication app) {
		
		
		
		boolean exists_data = (new File("data" + AbstractMTApplication.separator + source)).exists();
		boolean exists_root =  (new File(source)).exists();
		boolean exists_css = (new File("css" + AbstractMTApplication.separator + source)).exists();
		
		if (exists_data && !exists_css) source = "data" + AbstractMTApplication.separator + source; 
		if (exists_css) source = "css" + AbstractMTApplication.separator + source; 
		
		if (exists_css || exists_data || exists_root) {
		
		
		try {
			fileReader = new FileReader(source);
			this.loadStyles(app, new InputSource(fileReader));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		} else {
			InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("data/css/" + source);
			InputStreamReader streamReader = new InputStreamReader(stream);

			this.loadStyles(app, new InputSource(streamReader));
		}
		
		
	}
	
	/**
	 * Load styles from InputSource
	 *
	 * @param app the app
	 * @param source the source
	 */
	private void loadStyles(AbstractMTApplication app, InputSource source) {
		List<CSSStyle> styles= new ArrayList<CSSStyle>();
		cssh = new CSSHandler(app, styles);
		pa = new Parser();
		pa.setDocumentHandler(cssh);
		try {
			pa.parseStyleSheet(source);
		} catch (CSSException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Instantiates a new CSS parser connection using an InputStream as argument.
	 *
	 * @param input the input
	 * @param app the app
	 */
	public CSSParserConnection(InputStream input, AbstractMTApplication app) {
		if (input != null) {
			InputStreamReader streamReader = new InputStreamReader(input);

			this.loadStyles(app, new InputSource(streamReader));
			
		
		} else {
			System.out.println("Stream is not valid");

		}
		
		
	}
	


	/**
	 * Gets the CSSHandler.
	 *
	 * @return the CSSHandler
	 */
	public CSSHandler getCssh() {
		return cssh;
	}


	/**
	 * Sets the CSSHandler.
	 *
	 * @param cssh the new CSSHandler
	 */
	public void setCssh(CSSHandler cssh) {
		this.cssh = cssh;
	}
	
	
}
