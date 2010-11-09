package org.mt4jx.input.inputProcessors.componentProcessors.Group3DProcessorNew.GroupVisualizations;

import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4jx.input.inputProcessors.componentProcessors.Group3DProcessorNew.Cluster;
import org.mt4jx.input.inputProcessors.componentProcessors.Group3DProcessorNew.IVisualizeMethodProvider;

public class ActivateVisualizationAction implements IGestureEventListener {

	private Cluster cluster;
	
	private IVisualizeMethodProvider methodProvider;
	
	public ActivateVisualizationAction(Cluster cluster,IVisualizeMethodProvider methodProvider)
	{
		this.cluster = cluster;
		this.methodProvider = methodProvider;
		this.cluster.setVisualizeProvider(null);
	}
	@Override
	public boolean processGestureEvent(MTGestureEvent ge) {
		if(ge instanceof TapEvent)
		{
			TapEvent tapEv = (TapEvent)ge;
			switch(tapEv.getId())
			{
			case TapEvent.GESTURE_DETECTED:
				cluster.setVisualizeProvider(methodProvider);
				break;
			case TapEvent.GESTURE_ENDED:
				
				cluster.setVisualizeProvider(null);
				break;
			}
		}
		return false;
	}

}
