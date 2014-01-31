package gerritdrost.com.apps.mathie;

import gerritdrost.com.apps.mathie.util.ExpressionUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MathieGraph {
	private Set<Node> nodes = new HashSet<Node>();
	private HashMap<String, Node> nodeMap = new HashMap<String, Node>();

	public Node getNode(String expression) {

		expression = normalizeString(expression);

		if (nodeMap.containsKey(expression))
			return nodeMap.get(expression);
		else
			return null;

	}

	protected Node getOrConstructNode(String expression) {

		expression = normalizeString(expression);

		if (nodeMap.containsKey(expression))
			return nodeMap.get(expression);
		else
			return constructNode(expression);
	}

	protected Node addNewNode(String expression) {

		expression = normalizeString(expression);

		Node newNode = constructNode(expression);

		nodes.add(newNode);
		nodeMap.put(expression, newNode);

		return newNode;
	}

	protected Node constructNode(String expression) {

		int bracketsOpen = 0;

		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);

			if (c == '(')
				bracketsOpen++;
			else if (c == ')')
				bracketsOpen--;

			if (bracketsOpen > 0)
				continue;

			switch (c) {
			case '+':
				return NodeFactory.PLUS.createNode(	getOrConstructNode(expression.substring(0, i)),
													getOrConstructNode(expression.substring(i + 1, expression.length())));
			case '-':
				return NodeFactory.MINUS.createNode(getOrConstructNode(expression.substring(0, i)),
													getOrConstructNode(expression.substring(i + 1, expression.length())));
			case '*':
				return NodeFactory.TIMES.createNode(getOrConstructNode(expression.substring(0, i)),
													getOrConstructNode(expression.substring(i + 1, expression.length())));
			case '/':
				return NodeFactory.DIVIDED_BY.createNode(	getOrConstructNode(expression.substring(0, i)),
															getOrConstructNode(expression.substring(i + 1, expression.length())));
			case '^':
				return NodeFactory.POWER.createNode(getOrConstructNode(expression.substring(0, i)),
													getOrConstructNode(expression.substring(i + 1, expression.length())));
			}
		}

		if (expression.matches("^sin(.*)$"))
			return NodeFactory.SIN.createNode(getOrConstructNode(expression.substring(4, expression.length() - 1)));

		try {
			double value = Double.parseDouble(expression);
			return new ValueNode(value);
		} catch (NumberFormatException e) {

		}

		// Must be a variable
		return new VariableNode(expression);
	}


	public static String normalizeString(String expression) {
		return ExpressionUtils.removeUnusedBrackets(expression.replace(" ", ""));
	}

}
