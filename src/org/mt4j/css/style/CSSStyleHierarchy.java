package org.mt4j.css.style;

public class CSSStyleHierarchy implements Comparable{
	
	public final short NA = 0;
	public final short POS1 = 1;
	public final short POS2 = 2;
	public final short POS3 = 3;
	public final short POS4 = 4;

	public CSSStyleHierarchy(CSSStyle style) {
		this.style = style;
		this.priority = 0;
		this.type = NA;
		
	}
	
	public CSSStyleHierarchy(CSSStyle style, short type) {
		this.style = style;
		this.priority = 0;
		this.type = type;
	}
	
	public CSSStyleHierarchy(CSSStyleHierarchy sh, int priority, short type) {
		this.style = sh.getStyle();
		this.priority = priority;
		this.type = type;
		
		
	}
	
	int priority;
	CSSStyle style;
	short type;
	
	public CSSStyleHierarchy(CSSStyle style, int priority) {
		this.style = style;
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public CSSStyle getStyle() {
		return style;
	}

	public void setStyle(CSSStyle style) {
		this.style = style;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	@Override
	public int compareTo(Object arg0) {
		if (arg0 instanceof CSSStyleHierarchy) {
			CSSStyleHierarchy cs = (CSSStyleHierarchy) arg0;
			if (cs.getType() != (this.getType())) {
				if ((this.getType()) < (cs.getType())) {
					return -1;
				} else if ((this.getType()) > (cs.getType())) {
					return 1;
				} else {
					return 0;
				}
			} else {
				if (this.getPriority() < cs.getPriority()) {
					return -1;
				} else if (this.getPriority() > cs.getPriority()) {
					return 1;
				} else {
					return 0;
				}
			}
			
			
		}
		return 0;
	}


	
	
}
