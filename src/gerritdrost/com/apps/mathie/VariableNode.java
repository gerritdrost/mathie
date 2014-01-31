package gerritdrost.com.apps.mathie;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @author gerrit
 * 
 */
@AllArgsConstructor
public class VariableNode
		extends Node {

	@Getter
	protected String variableName;

	public VariableNode() {
		this.value = 1.0;
	}

	@Override
	public void update() {

	}

	public void set(double value) {
		this.value = value;
	}

}
