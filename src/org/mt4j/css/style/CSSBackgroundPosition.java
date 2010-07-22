package org.mt4j.css.style;

import org.mt4j.css.util.CSSKeywords.Position;
import org.mt4j.css.util.CSSKeywords.PositionType;

public class CSSBackgroundPosition {
	
	float xPos = 0;
	float yPos = 0;
	
	PositionType xType = PositionType.KEYWORD;
	PositionType yType = PositionType.KEYWORD;
	
	Position xKeywordPosition = Position.CENTER;
	Position yKeywordPosition = Position.CENTER;
	
	boolean unchanged = true;
	
	public CSSBackgroundPosition() {
		
	}
	
	public CSSBackgroundPosition(float x, float y, boolean isRelativeX, boolean isRelativeY) {
		xPos = x;
		yPos = y;
		
		if (isRelativeX) xType = PositionType.RELATIVE;
		else xType = PositionType.ABSOLUTE;
		
		if (isRelativeY) yType = PositionType.RELATIVE;
		else yType = PositionType.ABSOLUTE;
		unchanged = false;
	}
	
	public CSSBackgroundPosition(Position x, float y, boolean isRelativeY) {
		xKeywordPosition = x;
		xType = PositionType.KEYWORD;
		
		if (isRelativeY) yType = PositionType.RELATIVE;
		else yType = PositionType.ABSOLUTE;
		unchanged = false;
	}
	
	public CSSBackgroundPosition(float x, Position y, boolean isRelativeX) {
		yKeywordPosition = y;
		yType = PositionType.KEYWORD;
		
		if (isRelativeX) xType = PositionType.RELATIVE;
		else xType = PositionType.ABSOLUTE;
		unchanged = false;
	}
	
	public CSSBackgroundPosition(Position x, Position y) {
		xKeywordPosition = x;
		xType = PositionType.KEYWORD;
		
		yKeywordPosition = y;
		yType = PositionType.KEYWORD;
		unchanged = false;
	}

	public float getxPos() {
		return xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public PositionType getxType() {
		return xType;
	}

	public PositionType getyType() {
		return yType;
	}

	public Position getxKeywordPosition() {
		return xKeywordPosition;
	}

	public Position getyKeywordPosition() {
		return yKeywordPosition;
	}

	public boolean isUnchanged() {
		return unchanged;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	public void setxType(PositionType xType) {
		this.xType = xType;
	}

	public void setyType(PositionType yType) {
		this.yType = yType;
	}

	public void setxKeywordPosition(Position xKeywordPosition) {
		this.xKeywordPosition = xKeywordPosition;
	}

	public void setyKeywordPosition(Position yKeywordPosition) {
		this.yKeywordPosition = yKeywordPosition;
	}

	public void setUnchanged(boolean unchanged) {
		this.unchanged = unchanged;
	}

	
}
