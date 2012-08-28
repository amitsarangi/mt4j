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
package org.mt4jx.input.inputProcessors.componentProcessors.Group3DProcessorNew.FingerTapGrouping;

import org.mt4j.components.MTComponent;
import org.mt4j.input.inputData.InputCursor;

public enum FingerTapCursorState implements FingerTapCursorMethods {

	OBJECTWITHNOTAP
	{

		public void tapPress(FingerTapSelectionManager selManager,MTComponent comp,InputCursor c) {
			
			selManager.setLockedCursorForComponent(comp, c);
			selManager.setCursorStateForComponent(comp, OBJECTWITHLOCKEDCURSOR);
		}

		public void tapRelease(FingerTapSelectionManager selManager,MTComponent comp,InputCursor c) {
			//cannot be			
		}

	
	},
	
	OBJECTWITHLOCKEDCURSOR
	{

		public void tapPress(FingerTapSelectionManager selManager,MTComponent comp,InputCursor c) {
			selManager.addUnUsedCursorsForComponent(comp, c);
			selManager.setCursorStateForComponent(comp, OBJECTWITHONEUNUSEDCURSOR);
		}

		public void tapRelease(FingerTapSelectionManager selManager,MTComponent comp,InputCursor c) {
			if(selManager.getLockedCursorForComponent(comp)==c)
			{
				selManager.setLockedCursorForComponent(comp, null);
				selManager.setCursorStateForComponent(comp, OBJECTWITHNOTAP);
			}else
			{
				//should not happen
			}
		}		
	},
	
	OBJECTWITHONEUNUSEDCURSOR
	{

		public void tapPress(FingerTapSelectionManager selManager,
				MTComponent comp, InputCursor c) {
			selManager.addUnUsedCursorsForComponent(comp, c);
			selManager.setCursorStateForComponent(comp, OBJECTWITHMANYUNUSEDCURSORS);
			
		}

		public void tapRelease(FingerTapSelectionManager selManager,
				MTComponent comp, InputCursor c) {
			if(selManager.getLockedCursorForComponent(comp)==c)
			{
				selManager.setLockedCursorForComponent(comp, selManager.getUnUsedCursorsForComponent(comp).get(0));
				selManager.removeUnUsedCursorsForComponent(comp, selManager.getUnUsedCursorsForComponent(comp).get(0));
				selManager.setCursorStateForComponent(comp, OBJECTWITHLOCKEDCURSOR);
			}else
			{
				selManager.removeUnUsedCursorsForComponent(comp, c);
				selManager.setCursorStateForComponent(comp, OBJECTWITHLOCKEDCURSOR);				
			}
			
		}
		
	},
	
	OBJECTWITHMANYUNUSEDCURSORS
	{

		public void tapPress(FingerTapSelectionManager selManager,
				MTComponent comp, InputCursor c) {
			selManager.addUnUsedCursorsForComponent(comp, c);			
		}

		public void tapRelease(FingerTapSelectionManager selManager,
				MTComponent comp, InputCursor c) {
			selManager.removeUnUsedCursorsForComponent(comp, c);
			if(selManager.getUnUsedCursorsForComponent(comp).size()==1)
			{
				selManager.setCursorStateForComponent(comp, OBJECTWITHONEUNUSEDCURSOR);		
			}			
		}
		
	}
	
	
}
