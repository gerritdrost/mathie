package gerritdrost.com.apps.mathie;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MathieGraph {
	private Set<Node> nodes = new HashSet<Node>();
	private HashMap<String, Node> nodeMap = new HashMap<String, Node>();

	public Node createNode(String expression) {

		expression = normalizeString(expression);

		if (nodeMap.containsKey(expression))
			return nodeMap.get(expression);
		else
			return constructNode(expression);
	}

	protected Node constructNode(String expression) {

	}

	protected String removeUnusedBrackets() {

	}

	private String normalizeString(String expression) {
		return expression.replace(" ", "");
	}

}
