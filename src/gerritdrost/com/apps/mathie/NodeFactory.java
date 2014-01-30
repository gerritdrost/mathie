package gerritdrost.com.apps.mathie;


public enum NodeFactory {

	PLUS {
		@Override
		public Node createNode(Node... children) {
			return new Node(children) {

				@Override
				public void update() {
					this.value = children[0].value + children[1].value;
				}

			};
		}

	},
	MINUS {
		@Override
		public Node createNode(Node... children) {
			return new Node(children) {

				@Override
				public void update() {
					this.value = children[0].value - children[1].value;
				}

			};
		}
	},
	TIMES {
		@Override
		public Node createNode(Node... children) {
			return new Node(children) {

				@Override
				public void update() {
					this.value = children[0].value * children[1].value;
				}

			};
		}
	},
	DIVIDED_BY {
		@Override
		public Node createNode(Node... children) {
			return new Node(children) {

				@Override
				public void update() {
					this.value = children[0].value / children[1].value;
				}

			};
		}
	},
	POWER {
		@Override
		public Node createNode(Node... children) {
			return new Node(children) {

				@Override
				public void update() {
					this.value = Math.pow(children[0].value, children[1].value);
				}

			};
		}
	};

	public abstract Node createNode(Node... children);

}
