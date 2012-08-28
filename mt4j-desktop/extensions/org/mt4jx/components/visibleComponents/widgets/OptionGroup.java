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
package org.mt4jx.components.visibleComponents.widgets;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class OptionGroup.
 */
public class OptionGroup {
	
	/** The associated option boxes. */
	List<MTOptionBox> optionBoxes = new ArrayList<MTOptionBox>();
	
	/**
	 * Instantiates a new option group.
	 */
	public OptionGroup() {
		
	}

	/**
	 * Gets the option boxes.
	 *
	 * @return the option boxes
	 */
	public List<MTOptionBox> getOptionBoxes() {
		return optionBoxes;
	}

	/**
	 * Sets the option boxes.
	 *
	 * @param optionBoxes the new option boxes
	 */
	public void setOptionBoxes(List<MTOptionBox> optionBoxes) {
		this.optionBoxes = optionBoxes;
	}
	
	/**
	 * Adds an option box.
	 *
	 * @param box the box
	 */
	public void addOptionBox(MTOptionBox box) {
		if (!this.optionBoxes.contains(box)) {
			this.optionBoxes.add(box);
		}
	}
	
	/**
	 * Removes an option box.
	 *
	 * @param box the box
	 */
	public void removeOptionBox(MTOptionBox box) {
		this.optionBoxes.remove(box);
	}
	
	/**
	 * Sets the specified box as enabled, disable the rest
	 *
	 * @param box the new enabled
	 */
	public void setEnabled(MTOptionBox box) {
		for (MTOptionBox ob: optionBoxes) {
			if (ob != box) {
				ob.disable();
			}
			
		}
	}
	
	/**
	 * Gets the option which is enabled (as int)
	 * From 1...n, where 1 is the first OptionBox added and n the last
	 *
	 * @return the option
	 */
	public short getOption() {
		short i=1;
		for (MTOptionBox ob: optionBoxes) {
			if (ob.getBooleanValue() == true) return i;
			i++;
		}
		return 0;
	}
	
	
}
