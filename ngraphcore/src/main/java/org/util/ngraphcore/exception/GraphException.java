package org.util.ngraphcore.exception;

public class GraphException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GraphException(String name) {
		super(name);
	}
	
	public GraphException(String name, Throwable cause) {
		super(name, cause);
	}
	
}
