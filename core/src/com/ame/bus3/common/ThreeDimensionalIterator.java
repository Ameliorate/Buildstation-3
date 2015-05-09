package com.ame.bus3.common;

/**
 * @author Amelorate
 * Allows iterating over three dimentions with the gamemap utility method.
 */
@FunctionalInterface
public interface ThreeDimensionalIterator {
	void invoke(Coordinate location);
}
