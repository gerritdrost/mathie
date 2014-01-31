package gerritdrost.com.apps.mathie;

import java.util.HashSet;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class Node {

	@Getter
	protected double value;
	protected Node[] children = new Node[0];
	protected HashSet<Node> parents = new HashSet<Node>();

	public Node(Node[] children) {

		this.children = children;

		for (Node child : children)
			child.parents.add(this);

		update();
	}


	public abstract void update();
}
