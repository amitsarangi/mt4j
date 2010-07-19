package org.mt4j.css.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.batik.css.parser.Parser;
import org.mt4j.MTApplication;
import org.mt4j.css.style.CSSStyle;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.InputSource;


/**
 * The Class CSSParserConnection.
 */
public class CSSParserConnection {
	
	/** The Parser. */
	Parser pa = null;
	
	/** The file reader. */
	FileReader fileReader = null;
	
	/** The CSSHandler, which contains the parsing instructions */
	CSSHandler cssh = null;
	
	
	/**
	 * Instantiates a new CSS Parser Connections
	 *
	 * @param source the source file
	 * @param app the MTApplication
	 */
	public CSSParserConnection(String source, MTApplication app) {
		
		List<CSSStyle> styles= new ArrayList<CSSStyle>();
		cssh = new CSSHandler(app, styles);
		
		pa = new Parser();
		try {
			fileReader = new FileReader(source);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pa.setDocumentHandler(cssh);
		try {
			pa.parseStyleSheet(new InputSource(fileReader));
		} catch (CSSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	 * Sets the CSSHandler
	 *
	 * @param cssh the new CSSHandler
	 */
	public void setCssh(CSSHandler cssh) {
		this.cssh = cssh;
	}
	
	
}
