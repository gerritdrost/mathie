package gerritdrost.com.apps.mathie;

import java.util.Set;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Node {

	@Getter
	protected double value;

	@NonNull
	protected Node[] children;

	protected Set<Node> parents;

	public abstract void update();
}
