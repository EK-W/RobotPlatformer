package debug;

import java.util.List;

public interface Debuggable {
	/**
	 * gets the debug info of the object
	 * @param addTo The list to which the debug info should be added.
	 */
	void addDebugInfo(List<String> addTo);
}
