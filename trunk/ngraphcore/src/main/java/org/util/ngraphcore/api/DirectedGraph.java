package org.util.ngraphcore.api;

import java.util.Collection;

public interface DirectedGraph<T> extends Graph<T> {

	public Collection<Edge<T>> getIncomingEdges(T a);
	
	public Collection<Edge<T>> getOutgoingEdges(T a);
	
}
