package org.mt4jx.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.mt4j.components.MTComponent;
import org.mt4j.components.StateChange;
import org.mt4j.components.StateChangeEvent;
import org.mt4j.components.StateChangeListener;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.bounds.IBoundingShape;
import org.mt4jx.input.inputProcessors.componentProcessors.Group3DProcessorNew.Cluster;

public class MergeHelper implements StateChangeListener {

	HashMap<MTComponent,IBoundingShape> boundingShapes = new HashMap<MTComponent,IBoundingShape>();
	
	private static MergeHelper helperSingleton;
	
	private MergeHelper()
	{
		
	}
	
	public static MergeHelper getInstance()
	{
		if(helperSingleton==null)
		{
			helperSingleton = new MergeHelper();
			return helperSingleton;
		}else
		{
			return helperSingleton;
		}
	}
	
	/**
	 * return the merged Bounds of the component with all children
	 * @param comp, the component which should be merged
	 * @param dirty if the passed component has changed its matrix
	 *  	  true in case of calling this method after a statechange	     	 
	 * @return
	 */	
	private IBoundingShape mergeBoundsWithChildren(MTComponent comp,boolean dirty)
	{	
		if (isMergedOfChildrenBounds(comp)==true&&!dirty) return getMergedBoundsForComponent(comp);
		
		if(comp.getChildren().length==0)
		{			
			return comp.getBounds();
		}
		
		ArrayList<IBoundingShape> shapesToMerge = new ArrayList<IBoundingShape>();
		
		if(comp.hasBounds())
		{
			shapesToMerge.add(comp.getBounds());
		}
		
		for(int i=0;i < comp.getChildren().length;i++)
		{				
			MTComponent children = comp.getChildren()[i];
			IBoundingShape shape1 = mergeBoundsWithChildren(children,false);
			IBoundingShape shape = shape1.getBoundsTransformed(TransformSpace.RELATIVE_TO_PARENT);
			shapesToMerge.add(shape);
		}
		
		for(int i=shapesToMerge.size()-1;i>0;i--)
		{
			IBoundingShape mergedShape = shapesToMerge.get(i).merge(shapesToMerge.get(i-1));
			shapesToMerge.set(i-1,mergedShape);			
		}
			
		if(shapesToMerge.size()>0)
		{	
			addMTComponentWithMergedBounding(comp,shapesToMerge.get(0));			
			return shapesToMerge.get(0);
		}else
		{
			return null;
		}
	}
	
	private void addMTComponentWithMergedBounding(MTComponent comp,IBoundingShape shape)
	{
		boundingShapes.put(comp,shape);		
	}
	
	private void removeMTComponentWithMergedBounding(MTComponent comp)
	{
		boundingShapes.remove(comp);
	}
	
	public IBoundingShape getMergedBoundsForComponent(MTComponent comp)
	{
		if(boundingShapes.containsKey(comp))
		{
			return boundingShapes.get(comp);
		}else
		{
			IBoundingShape shape =  mergeBoundsWithChildren(comp,true);
			updateParentAfterMerge(comp);
			return shape;
		}		
	}
	
	/**
	 * 
	 * @param comp
	 */
	private void updateParentAfterMerge(MTComponent comp)
	{		
		if(comp.getParent()!=null&&boundingShapes.containsKey(comp.getParent())==true)
		{
			mergeBoundsWithChildren(comp.getParent(),true);		
		}
	}
	
	public boolean isMergedOfChildrenBounds(MTComponent comp)
	{
		return boundingShapes.containsKey(comp);
	}

	@Override
	public void stateChanged(StateChangeEvent evt) {
		
		if(evt.getSource() instanceof MTComponent&&evt.getState()==StateChange.GLOBAL_TRANSFORM_CHANGED)
		{
			
			MTComponent comp = (MTComponent)evt.getSource();
			mergeBoundsWithChildren(comp,true);	
			updateParentAfterMerge(comp);
		}
	}

}
