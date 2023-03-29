package liste;
import java.util.Scanner;


public class CommandList {

	private Element root;
	
	
	
	// Konstruktor:
	public CommandList() {
		root = null;
	}

	// Eintrag in Liste einfügen:
	public Command add(Command c) {
		if (c != null) {
			Element elm = new Element(c);
			if (root == null) {
				root = elm;
			} 
			else {
				Element temp = root;
				while (temp.getNext() != null) {
					temp = temp.getNext();
				}
				temp.setNext(elm);
				elm.setPrev(temp);
			}
			return elm.getCommand();
		}
		return null;
	}

	// Länge der Liste:
	public int getSize() {
		
		int size = 0;
		Element temp = root;

		while (temp != null) {
			temp = temp.getNext();
			++size;
		}
		return size;
	}

	// Liste Ausgeben:
	public void printCommands() {
		
		Element temp = root;
		int i = 1;
		
		while (temp != null) {
			System.out.println(i + ". Element: " + temp.getCommand().getName());
			temp = temp.getNext();
			++i;
		}
	}
	
		
	// Element ermitteln:
	private Element getElement(int pos) {
		Element temp = root;
		
		for(int i = 1; i < pos; ++i) {
			temp = temp.getNext();
		}
		return temp;
	}
	
	
	// Command ermitteln:
	public Command getCommand(int pos) {
		Element temp = getElement(pos);
		return temp.getCommand();
	}

	
	// Command um eine pos erhöhen:
	public Command moveUp(int pos) {
		Command tempA = getCommand(pos);
		Command tempB = getCommand(pos + 1);
		Command tempC = tempA;
		tempA = tempB;
		tempB = tempC;
		return tempC;
	}

	// Command um eine pos runter:
	public Command moveDown(int pos) {
		Command tempA = getCommand(pos);
		Command tempB = getCommand(pos - 1);
		Command tempC = tempA;
		tempA = tempB;
		tempB = tempC;
		return tempC;
	}
	
	
	// Entfernen:
	public Command remove(int pos) {
		Element temp = getElement(pos);
		
		if(temp.getPrev() != null)
		temp.getPrev().setNext(temp.getNext());
		
		if(temp.getNext() != null)
		temp.getNext().setPrev(temp.getPrev());
		
		return temp.getCommand();
	}

	// Position ermitteln:
	public int getPos(Command c) {
		Element temp = root;
		int i = 0;
		while (temp.getCommand() != c && temp != null) {
			temp = temp.getNext();
			++i;
		}
		if (temp == null) {
			return -1;
		} 
		else {
			return i;
		}
	}

	// Liste Leeren:
	public void clear() {
		root = null;
	}

	// Main Funktion:
	public static void main(String[] args) {

		CommandList list = new CommandList();

		System.out.println("Was möchten Sie mit der Liste machen?" + "\n");
		System.out.println(" 1: Eintrag in die Liste einfügen");
		System.out.println(" 2: Liste ausgeben");
		System.out.println(" 3: Länge der Liste bestimmen");
		System.out.println(" 4: Eintrag  an Position x entfernen");
		System.out.println(" 5: Eintrag an Position x ermitteln");
		System.out.println(" 6: Eintrag verschieben (up)");
		System.out.println(" 7: Eintrag verschieben (down)");
		System.out.println(" 8: Position von Eintrag x ermitteln");
		System.out.println(" 9: Liste komplett leeren");
		System.out.println("10: Programm beenden und Liste löschen");
		
		boolean abbruch = true;
		while(abbruch){

		System.out.println("\nBitte wählen: ");

		Scanner scanner = new Scanner(System.in);
		int wahl = scanner.nextInt();
		scanner.nextLine();
		
		if (wahl == 1) {
			System.out.print("Bitte ein Wort eingeben: ");
			String wort = scanner.nextLine();
			
			Command einEintrag = new Command(wort);
			Command returnVal = list.add(einEintrag);
			System.out.print("'" + returnVal.getName() + "'" + " wurde in die Liste eingefügt.");
			
		} else if (wahl == 2) {
			list.printCommands();
		} else if (wahl == 3) {
			System.out.println("Die Liste hat " + list.getSize() + " Einträge");
		} else if (wahl == 4) {
			System.out.print("Nummer dessen Eintrag gelöscht werden soll eingeben: ");
			int pos = scanner.nextInt();
			Command returnVal = list.remove(pos);
			System.out.print("'" + returnVal.getName() + "'" + " wurde aus der Liste entfernt");
		} else if (wahl == 5) {
			System.out.print("Nummer des Eintrags eingeben: ");
			int pos = scanner.nextInt();
			Command returnVal = list.getCommand(pos);
			System.out.print("'" + returnVal.getName() + "'" + " steht an Position " + pos);
		} else if (wahl == 6) {
			System.out.print("Nummer des Eintrags eingeben: ");
			int pos = scanner.nextInt();
			Command returnVal = list.moveUp(pos);
			System.out.print("'" + returnVal.getName() + "'" + " wurde um eine Position nach oben verschoben");
		} else if (wahl == 7) {
			System.out.print("Nummer des Eintrags eingeben: ");
			int pos = scanner.nextInt();
			Command returnVal = list.moveDown(pos);
			System.out.print("'" + returnVal.getName() + "'" + " wurde um eine Position nach unten verschoben");
		} else if (wahl == 8) {
			
		} else if (wahl == 9) {
			list.clear();
			System.out.print("Die Liste hat nun keine Einträge mehr");
		} else if (wahl == 10) {
			list.clear();
			System.out.print("Programm beendet");
			abbruch = false;
		} else {
			
			System.out.print("Falsche Eingabe");
		}
		
		}
		
	}

}
