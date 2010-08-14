package org.mt4j.test.css;

import java.util.List;


import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.junit.Test;
import org.mt4j.MTApplication;
import org.mt4j.components.MTCanvas;
import org.mt4j.components.MTComponent;
import org.mt4j.css.parser.CSSParserConnection;
import org.mt4j.css.style.CSSSelector;
import org.mt4j.css.style.CSSStyle;
import org.mt4j.css.util.CSSKeywords.CSSSelectorType;
import org.mt4j.css.util.CSSStyleManager;
import org.mt4j.sceneManagement.Iscene;
import org.mt4j.test.AbstractWindowTestcase;
import org.mt4j.test.testUtil.DummyScene;
import org.mt4j.util.MTColor;

public class BackgroundImageTest  extends AbstractWindowTestcase {
	private MTComponent parent;
	private MTApplication app;
	private Iscene scene;
	CSSStyleManager cssm;
	Logger logger = Logger.getLogger("MT4J Extensions");
	SimpleLayout l = new SimpleLayout();
	ConsoleAppender ca = new ConsoleAppender(l);
	CSSParserConnection pc;
	List<CSSStyle> styles;
	MTColor w = new MTColor(255,255,255,255);
	
	@Override
	public void inStartUp(MTApplication app) {
		this.app = app;
		//Add a scene to the mt application
		this.scene = new DummyScene(app, "Dummy Scene");
		app.addScene(scene);
		
		//Set up components
		parent = new MTComponent(app);
		getCanvas().addChild(parent);
		
		logger.addAppender(ca);
		
		pc = new CSSParserConnection("backgroundimagetest.css", app);
		styles= pc.getCssh().getStyles();
		cssm = new CSSStyleManager(styles,app);
	}
	
	public MTCanvas getCanvas(){
		return this.scene.getCanvas();
	}
	
	@Test
	public void testParser() {
		logger.debug(cssm.getStyles().size() + " Styles");
		CSSStyle sty;
		CSSSelector s1 = new CSSSelector("id0", CSSSelectorType.ID);
		sty = cssm.getFirstStyleForSelector(s1);
		assertTrue(sty.getBackgroundImage().width == 256);
		
	}

}
