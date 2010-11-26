package org.mt4j.input.inputProcessors;

public interface ICollisionAction {
	public boolean gestureAborted();
	
	public void setGestureAborted(boolean aborted);
	
	public MTGestureEvent getLastEvent();
}
