package gerritdrost.com.libs.mathie.util;

import java.util.Iterator;

import lombok.Getter;

/**
 * Iterates over a string while keeping track of the number of opened brackets.
 * 
 * @author Gerrit Drost <mail@gerritdrost.com>
 * 
 */
public class BracketStringIterator
		implements Iterator<Character> {

	@Getter
	private String string;
	private Direction direction;
	int i;
	int step;

	@Getter
	int openedBrackets = 0;

	protected BracketStringIterator(String string, Direction direction) {
		this.string = string;
		this.direction = direction;
		this.i = (direction == Direction.LEFT_TO_RIGHT) ? 0 : string.length() - 1;
		this.step = (direction == Direction.LEFT_TO_RIGHT) ? 1 : (-1);
	}

	public static BracketStringIterator create(String iterableString, Direction direction) {
		return new BracketStringIterator(iterableString, direction);
	}

	@Override
	public boolean hasNext() {
		return (direction == Direction.LEFT_TO_RIGHT ? i < string.length() : i > 0);
	}

	@Override
	public Character next() {

		char c = string.charAt(i);

		if ((c == '(' && direction == Direction.LEFT_TO_RIGHT) || (c == ')' && direction == Direction.RIGHT_TO_LEFT))
			openedBrackets++;
		else if ((c == ')' && direction == Direction.LEFT_TO_RIGHT) || (c == '(' && direction == Direction.RIGHT_TO_LEFT))
			openedBrackets--;

		i += step;

		return c;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("BracketIterator does not support removal of elements.");
	}

	public static enum Direction {
		RIGHT_TO_LEFT,
		LEFT_TO_RIGHT
	}

	public int getCurrentIndex() {
		return i - step;
	}

	public int getNextIndex() {
		return i;
	}

}
