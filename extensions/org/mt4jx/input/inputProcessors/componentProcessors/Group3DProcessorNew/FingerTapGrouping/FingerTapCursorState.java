package org.mt4jx.input.inputProcessors.componentProcessors.Group3DProcessorNew.FingerTapGrouping;

import org.mt4j.components.MTComponent;
import org.mt4j.input.inputData.InputCursor;

public enum FingerTapCursorState implements FingerTapCursorMethods {

	OBJECTWITHNOTAP
	{

		@Override
		public void tapPress(FingerTapSelectionManager selManager,MTComponent comp,InputCursor c) {
			selManager.setLockedCursorForComponent(comp, c);
			selManager.setCursorStateForComponent(comp, OBJECTWITHLOCKEDCURSOR);
		}

		@Override
		public void tapRelease(FingerTapSelectionManager selManager,MTComponent comp,InputCursor c) {
			//cannot be			
		}

	
	},
	
	OBJECTWITHLOCKEDCURSOR
	{

		@Override
		public void tapPress(FingerTapSelectionManager selManager,MTComponent comp,InputCursor c) {
			selManager.addUnUsedCursorsForComponent(comp, c);
			selManager.setCursorStateForComponent(comp, OBJECTWITHONEUNUSEDCURSOR);
		}

		@Override
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

		@Override
		public void tapPress(FingerTapSelectionManager selManager,
				MTComponent comp, InputCursor c) {
			selManager.addUnUsedCursorsForComponent(comp, c);
			selManager.setCursorStateForComponent(comp, OBJECTWITHMANYUNUSEDCURSORS);
			
		}

		@Override
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

		@Override
		public void tapPress(FingerTapSelectionManager selManager,
				MTComponent comp, InputCursor c) {
			selManager.addUnUsedCursorsForComponent(comp, c);			
		}

		@Override
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
