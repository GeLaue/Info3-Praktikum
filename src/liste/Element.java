package liste;

public class Element {

	private Element prev;
	private Element next;
	private Command cmd;

	// Konstruktor:
	public Element(Command cmd) {
		this.cmd = cmd;
		this.next = null;
		this.prev = null;
	}

	// Getter Element Prev:
	public Element getPrev() {
		return prev;
	}

	// Setter Element Prev:
	public void setPrev(Element prev) {
		this.prev = prev;
	}

	// Getter Element Next:
	public Element getNext() {
		return next;
	}

	// Setter Element Next:
	public void setNext(Element next) {
		this.next = next;
	}
	
	// Getter für den Command
	public Command getCommand() {
		return cmd;
	}

}
