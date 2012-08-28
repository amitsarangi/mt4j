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
package org.mt4jx.input.inputProcessors.componentProcessors.Group3DProcessorNew;

import org.mt4j.input.MTEvent;
import org.mt4j.input.inputProcessors.componentProcessors.rotate3DProcessor.Cluster3DExt;

public class MTClusterEvent extends MTEvent {

	public static final int CLUSTER_CREATED = 1;
	
	public static final int CLUSTER_UPDATED = 2;
	
	public static final int CLUSTER_DELETED = 3;
	
	public static final int CLUSTER_SELECTED = 4;
	
	private Cluster3DExt cluster;
	
	private int id;
	
	public MTClusterEvent(Object source,int id,Cluster3DExt cluster) {
		super(source);
		this.cluster = cluster;
		this.id = id;
	}

	public void setCluster(Cluster3DExt cluster) {
		this.cluster = cluster;
	}

	public Cluster3DExt getCluster() {
		return cluster;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
