package org.mt4j.components.visibleComponents.widgets.menus;


import java.util.ArrayList;
import java.util.List;

import org.mt4j.MTApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.clipping.Clip;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.css.style.CSSFont;
import org.mt4j.css.style.CSSStyle;
import org.mt4j.css.util.CSSFontManager;
import org.mt4j.css.util.CSSKeywords.CSSFontWeight;
import org.mt4j.css.util.CSSStylableComponent;
import org.mt4j.input.IMTInputEventListener;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.AbstractComponentProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.rotateProcessor.RotateProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.scaleProcessor.ScaleProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;

import processing.core.PImage;

public class MTSquareMenu extends MTRectangle implements CSSStylableComponent {

	MTApplication app;
	List<MTRectangle> menuContents = new ArrayList<MTRectangle>();
	List<ArrayList<MTRectangle>> layout = new ArrayList<ArrayList<MTRectangle>>();
	float size;
	int current = 0;
	int maxPerLine = 0;
	float bezel = 10f;

	public MTSquareMenu(MTApplication app, Vector3D position,
			List<MenuItem> menuItems, float size) {
		super(position.x, position.y, (float) (int) Math
				.sqrt(menuItems.size() + 1) * size, (float) (int) Math
				.sqrt(menuItems.size() + 1) * size, app);
		this.app = app;
		this.size = size;
		this.setCssForceDisable(true);

		this.setNoFill(true);
		this.setNoStroke(true);

		gestureListener gl = new gestureListener(this);

		for (MenuItem s : menuItems) {
			
			if (s != null && s.getType() == MenuItem.TEXT) {
				MTRectangle container = new MTRectangle(0, 0, size, size, app);
				this.addChild(container);
				container.removeAllGestureEventListeners(DragProcessor.class);

				container.setGestureAllowance(TapProcessor.class, true);
				container.registerInputProcessor(new TapProcessor(app));
				container.addGestureListener(TapProcessor.class,
						s.getGestureListener());

				for (String t : s.getMenuText().split("\n")) {
					MTTextArea menuItem = new MTTextArea(app);
					menuItem.setText(t);
					menuItem.setCssForceDisable(true);
					menuItem.setFillColor(new MTColor(0, 0, 0, 0));
					menuItem.setStrokeColor(new MTColor(0, 0, 0, 0));
					menuItem.setPickable(false);
					container.addChild(menuItem);

				}
				container.setGestureAllowance(DragProcessor.class, true);
				container.registerInputProcessor(new DragProcessor(app));
				container.addGestureListener(DragProcessor.class, gl);
				container.setChildClip(new Clip(container));

				container.setGestureAllowance(RotateProcessor.class, false);
				container.setGestureAllowance(ScaleProcessor.class, false);
				menuContents.add(container);
			} else if (s != null && s.getType() == MenuItem.PICTURE) {

				if (s.getMenuImage() != null) {
					PImage texture = null;
					if (s.getMenuImage().width != (int) size
							|| s.getMenuImage().height != (int) size) {
						texture = cropImage(s.getMenuImage(), (int) size, true);
					} else {
						texture = s.getMenuImage();
					}

					MTRectangle container = new MTRectangle(0, 0, size, size,
							app);
					this.addChild(container);
					container.setTexture(texture);
					container
							.removeAllGestureEventListeners(DragProcessor.class);

					container.setGestureAllowance(TapProcessor.class, true);
					container.registerInputProcessor(new TapProcessor(app));
					container.addGestureListener(TapProcessor.class,
							s.getGestureListener());

					container.setGestureAllowance(DragProcessor.class, true);
					container.registerInputProcessor(new DragProcessor(app));
					container.addGestureListener(DragProcessor.class, gl);
					container.setChildClip(new Clip(container));

					container.setGestureAllowance(RotateProcessor.class, false);
					container.setGestureAllowance(ScaleProcessor.class, false);
					menuContents.add(container);

				}

			}

		}
		this.setCssForceDisable(true);
		this.styleChildren(getNecessaryFontSize(menuItems, size));

	}

	private PImage cropImage(PImage image, int size, boolean resize) {
		PImage returnImage = app.createImage(size, size, app.RGB);
		if (resize || image.width < size || image.height < size) {
			if (image.width < image.height) {
				image.resize(
						size,
						(int) ((float) image.height / ((float) image.width / (float) size)));
			} else {
				image.resize(
						(int) ((float) image.width / ((float) image.height / (float) size)),
						size);
			}

		}
		int x = (image.width / 2) - (size / 2);
		int y = (image.height / 2) - (size / 2);

		if (x + size > image.width)
			x = image.width - size;
		if (x < 0)
			x = 0;
		if (x + size > image.width)
			size = image.width - x;
		if (y + size > image.height)
			x = image.height - size;
		if (y < 0)
			y = 0;
		if (y + size > image.height)
			size = image.height - y;

		returnImage.copy(image, x, y, size, size, 0, 0, size, size);

		return returnImage;
	}

	public class gestureListener implements IGestureEventListener {
		MTRectangle r;
		boolean react;

		public gestureListener(MTRectangle r) {
			super();
			this.r = r;
			System.out.println("Initialized gestureListener");
		}

		@Override
		public boolean processGestureEvent(MTGestureEvent ge) {
			// TODO: Remove Dirty Hack
			if (ge instanceof DragEvent && react) {
				DragEvent de = (DragEvent) ge;
				r.translate(de.getTranslationVect());
			}
			react = !react;
			return true;
		}
	}

	private void styleChildren(int fontsize) {
		organizeRectangles();
		CSSStyle vss = this.getCssHelper().getVirtualStyleSheet();
		CSSFont cf = this.getCssHelper().getVirtualStyleSheet().getCssfont();
		cf.setFontsize(fontsize);
		cf.setWeight(CSSFontWeight.BOLD);
		CSSFontManager cfm = new CSSFontManager(app);
		IFont font = cfm.selectFont(cf);
		int i = 0;
		// System.out.println("Fill Color: " + vss.getBackgroundColor());
		for (MTRectangle c : menuContents) {

			MTRectangle rect = c;

			c.setWidthLocal(size);
			c.setHeightLocal(size);

			rect.setStrokeColor(vss.getBorderColor());
			// System.out.println("Border Color: " + vss.getBorderColor());
			if (((MTRectangle) c).getTexture() == null) {
				rect.setFillColor(vss.getBackgroundColor());
				for (MTComponent d : c.getChildren()) {
					if (d instanceof MTTextArea) {
						MTTextArea ta = (MTTextArea) d;
						// System.out.println("Setting Font for Part " +
						// ta.getText());
						ta.setFont(font);
					}
				}

				float height = calcTotalHeight(c.getChildren());
				float ypos = size / 2f - height / 2f;
				for (MTComponent d : c.getChildren()) {
					if (d instanceof MTTextArea) {
						MTTextArea ta = (MTTextArea) d;

						ta.setPositionRelativeToParent(new Vector3D(size / 2f,
								ypos + ta.getHeightXY(TransformSpace.LOCAL)
										/ 2f));
						ypos += ta.getHeightXY(TransformSpace.LOCAL);

					}

				}
			} else {
				rect.setFillColor(MTColor.WHITE);
			}

		}

		int currentRow = 0;
		for (List<MTRectangle> lr : layout) {
			int currentColumn = 0;
			for (MTRectangle r : lr) {
				r.setPositionRelativeToParent((new Vector3D(this
						.getVerticesLocal()[0].x
						+ (size / 2f)
						+ (bezel / 2f)
						+ currentColumn++
						* (size + bezel)
						+ (maxPerLine - lr.size()) * (size / 2f + bezel / 2f),
						this.getVerticesLocal()[0].x + (size / 2 + bezel / 2f)
								+ currentRow * (size + bezel))));
			}
			currentRow++;
		}

	}

	private void organizeRectangles() {
		layout.clear();
		layout.add(new ArrayList<MTRectangle>());
		layout.add(new ArrayList<MTRectangle>());
		layout.add(new ArrayList<MTRectangle>());
		layout.add(new ArrayList<MTRectangle>());
		current = 0;
		switch (menuContents.size()) {
		case 0:
		case -1:
			break;

		case 1:
			layout.get(0).addAll(next(1));
			maxPerLine = 1;
			break;
		case 2:
			layout.get(0).addAll(next(2));
			maxPerLine = 2;
			break;
		case 3:
			layout.get(0).addAll(next(1));
			layout.get(1).addAll(next(2));
			maxPerLine = 2;
			break;
		case 4:
			layout.get(0).addAll(next(2));
			layout.get(1).addAll(next(2));
			maxPerLine = 2;
			break;
		case 5:
			layout.get(0).addAll(next(2));
			layout.get(1).addAll(next(3));
			maxPerLine = 3;
			break;
		case 6:
			layout.get(0).addAll(next(3));
			layout.get(1).addAll(next(3));
			maxPerLine = 3;
			break;
		case 7:
			layout.get(0).addAll(next(2));
			layout.get(1).addAll(next(3));
			layout.get(2).addAll(next(2));
			maxPerLine = 3;
			break;
		case 8:
			layout.get(0).addAll(next(3));
			layout.get(1).addAll(next(2));
			layout.get(2).addAll(next(3));
			maxPerLine = 3;
			break;
		case 9:
			layout.get(0).addAll(next(3));
			layout.get(1).addAll(next(3));
			layout.get(2).addAll(next(3));
			maxPerLine = 3;
			break;
		case 10:
			layout.get(0).addAll(next(3));
			layout.get(1).addAll(next(4));
			layout.get(2).addAll(next(3));
			maxPerLine = 4;
			break;
		case 11:
			layout.get(0).addAll(next(4));
			layout.get(1).addAll(next(3));
			layout.get(2).addAll(next(4));
			maxPerLine = 4;
			break;
		case 12:
			layout.get(0).addAll(next(4));
			layout.get(1).addAll(next(4));
			layout.get(2).addAll(next(4));
			maxPerLine = 4;
			break;
		case 13:
			layout.get(0).addAll(next(4));
			layout.get(1).addAll(next(5));
			layout.get(2).addAll(next(4));
			maxPerLine = 5;
			break;
		case 14:
			layout.get(0).addAll(next(3));
			layout.get(1).addAll(next(4));
			layout.get(2).addAll(next(4));
			layout.get(3).addAll(next(3));
			maxPerLine = 4;
			break;
		case 15:
			layout.get(0).addAll(next(5));
			layout.get(1).addAll(next(5));
			layout.get(2).addAll(next(5));
			maxPerLine = 5;
			break;
		case 16:
			layout.get(0).addAll(next(4));
			layout.get(1).addAll(next(4));
			layout.get(2).addAll(next(4));
			layout.get(3).addAll(next(4));
			maxPerLine = 4;
			break;

		}

	}

	private List<MTRectangle> next(int next) {
		List<MTRectangle> returnValues = new ArrayList<MTRectangle>();
		for (int i = 0; i < next; i++) {
			returnValues.add(menuContents.get(current++));
		}

		return returnValues;
	}

	private float calcTotalHeight(MTComponent[] components) {
		float height = 0;
		for (MTComponent c : components) {
			if (c instanceof MTTextArea)
				height += ((MTTextArea) c).getHeightXY(TransformSpace.LOCAL);
		}

		return height;
	}

	private int getNecessaryFontSize(List<MenuItem> strings, float size) {
		int maxNumberCharacters = 0;

		for (MenuItem s : strings) {

			if (s.getType() == MenuItem.TEXT) {
				if (s.getMenuText().contains("\n")) {
					for (String t : s.getMenuText().split("\n")) {

						if (t.length() > maxNumberCharacters)
							maxNumberCharacters = t.length();

					}
				} else {

					if (s.getMenuText().length() > maxNumberCharacters)
						maxNumberCharacters = s.getMenuText().length();

				}
			}
		}

		float spc = size / (float) maxNumberCharacters; // Space Per Character

		System.out.println("Max Characters " + maxNumberCharacters
				+ ", Space per Character: " + spc);

		// 16 = 2x Padding

		if (spc < 4) {
			return 6;
		} else if (spc < 4.5384617) {
			return 7;
		} else if (spc < 4.8653846) {
			return 8;
		} else if (spc < 5.769231) {
			return 9;
		} else if (spc < 6.3461537) {
			return 10;
		} else if (spc < 6.5) {
			return 11;
		} else if (spc < 7.25) {
			return 12;
		} else if (spc < 7.7884617) {
			return 13;
		} else if (spc < 8.269231) {
			return 14;
		} else if (spc < 8.769231) {
			return 15;
		} else if (spc < 9.384615) {
			return 16;
		} else if (spc < 9.692307) {
			return 17;
		} else if (spc < 10.692307) {
			return 18;
		} else if (spc < 11.442307) {
			return 19;
		} else if (spc < 11.711538) {
			return 20;
		} else if (spc < 12.307693) {
			return 21;
		} else if (spc < 13.019231) {
			return 22;
		} else if (spc < 13.5) {
			return 23;
		} else if (spc < 14.269231) {
			return 24;
		} else if (spc < 14.942307) {
			return 25;
		} else if (spc < 15.384615) {
			return 26;
		} else if (spc < 16.038462) {
			return 27;
		} else if (spc < 16.615385) {
			return 28;
		} else if (spc < 17.192308) {
			return 29;
		} else if (spc < 17.75) {
			return 30;
		} else {
			return 30;
		}

	}
}

