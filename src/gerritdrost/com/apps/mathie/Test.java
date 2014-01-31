package gerritdrost.com.apps.mathie;

public class Test {
	public static void main(String[] args) {
		MathieGraph graph = new MathieGraph();
		Node node = graph.addNewNode("2+3");
		Node node2 = graph.addNewNode("2+4-3");
		System.out.println(node.getValue());
		System.out.println(node2.getValue());
	}
}
