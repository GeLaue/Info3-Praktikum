package liste;
import java.util.Scanner;

/**
 * Class for a doubly linked list
 * @author Michael Sander, Gerrit von Laue
 *
 */
public class CommandList {

	private Element root;
	
	
	
	/** 
	 * Sets default value for root null
	 */
	public CommandList() {
		root = null;
	}

	/**
	 * Adds a new elemt to the list
	 * @param c type command is included in the new element
	 * @return the command contained in the new element else null
	 */
	public Command add(Command c) {
		
		if (c != null) {									// Abfragen ob Command wirklich besteht
			Element elm = new Element(c);					// Neues Element anlegen.
			if (root == null) {								// Einfügen des ersten Elements, da Anker noch auf null
				root = elm;
			} 
			else {											// Element hinten eifügen
				Element temp = root;						// Hilfszeiger
				while (temp.getNext() != null) {			// So lange durch bis am Ende der Liste
					temp = temp.getNext();
				}
				temp.setNext(elm);							// Zeiger umhängen
				elm.setPrev(temp);
			}
			return elm.getCommand();						// Command zurückgeben
		}
		return null;
	}

	/**
	 * method determine the length of the list
	 * @return returns length as int value
	 */
	public int getSize() {
		
		int size = 0;
		Element temp = root;
		
		while (temp != null) {				//Durchzählen bis Ende der Liste erreicht
			temp = temp.getNext();
			++size;
		}
		return size;
	}

	/**
	 * method to print out the list
	 */
	public void printCommands() {
		
		Element temp = root;
		int i = 1;
		if(getSize()==0)
			System.out.println( "Die Liste ist leer. ");
		else{
			while (temp != null) {							 // Alle Elemente ausgeben
				System.out.println(i + ". Element: " + temp.getCommand().getName());
				temp = temp.getNext();
				++i;
			}
		}
	}
	
		
	/**
	 * method to find an element
	 * @param pos position of the element in the list
	 * @return the Element
	 */
	private Element getElement(int pos) {
		if(pos <= getSize() && pos > 0){					// gesuchte Element muss innerhalb Bereich sein
			Element temp = root;
			for (int i = 1; i < pos; ++i) {					// durchlaufen bis Element an Position gefunden
				temp = temp.getNext();
			}
			return temp;
		}
		return null;
	}
	
	
	/**
	 * method to find the Command
	 * @param pos position of the element in the list
	 * @return the command
	 */
	public Command getCommand(int pos) {
		Element temp = getElement(pos);
		
		if(temp == null)		//Wenn das Element nicht existiert
			return null;
		return temp.getCommand();
	}

	/**
	 * method to move the element down in the list
	 * @param pos position of the element in the list
	 * @return the Command of the moved element
	 */
	public Command moveDown(int pos) {
		
		if (pos >= 1 && pos <= getSize() - 1) { //Wenn das gesuchte Element zwischen dem ersten und dem vorletzen Element ist
				
			Element tempA = getElement(pos - 1);			// Elemente aus der Liste nehmen
			Element tempB = getElement(pos);
			Element tempC = getElement(pos + 1);
			Element tempD = getElement(pos + 2);
			
			tempB.setNext(tempD);							// C und B tauschen
			tempC.setPrev(tempA);		
			tempB.setPrev(tempC);
			tempC.setNext(tempB);	

			if(tempD != null)								// Zeiger der benachbarten Elemente, der Getauchten, umhängen
				tempD.setPrev(tempB);

			if(tempA != null)
				tempA.setNext(tempC);
			else
				root = tempC;								// Anker umhängen, wenn erstes Element bewegt wird

			return tempB.getCommand();						// getauschtes Element zurückgeben
			
		}
		return null;
	}

	/**
	 * method to move the element up in the list
	 * @param pos position of the element in the list
	 * @return the Command of the moved element
	 */
	public Command moveUp(int pos) {
		
		if (pos >= 2 && pos <= getSize()) { 		//Wenn das gesuchte Element zwischen pos 2 und dem Ende der Liste ist
					
			Element tempA = getElement(pos - 2);  	//Elemente aus der Liste holen
			Element tempB = getElement(pos - 1);
			Element tempC = getElement(pos);
			Element tempD = getElement(pos + 1);
			
			tempB.setNext(tempD);		//B und C tauschen
			tempC.setNext(tempB);	
			tempC.setPrev(tempA);		
			tempB.setPrev(tempC);
			
			if(tempD != null)			//Benachbarte Elemente der zu Tauschenden anpassen
				tempD.setPrev(tempB);
			
			if(tempA != null)
				tempA.setNext(tempC);				
			else
				root = tempC;			//Anker umhängen

			return tempC.getCommand();	
			
		}
		return null;
	}
	
	/**
	 * method to remove an element
	 * @param pos position of the element in the list
	 * @return the Command of the removed element
	 */
	public Command remove(int pos) {
		Element temp = getElement(pos);
		//Wenn das Element nicht vorhanden ist
		if(temp == null)	
			return null;

		//Erstes Element
		if(temp.getPrev() == null)
		root = temp.getNext();
		
		//nicht das erste Element
		if(temp.getPrev() != null)
		temp.getPrev().setNext(temp.getNext());
		
		//nicht das letzte ELement
		if(temp.getNext() != null)
		temp.getNext().setPrev(temp.getPrev());
		
		return temp.getCommand();
	}

	/**
	 * method to get the position of an element
	 * @param c the content of the element
	 * @return the position of the element
	 */
	public int getPos(Command c) {
		Element temp = root;
		int i = 1;
		//während nicht das Ende der Liste erreicht ist und die Namen nicht gleich sind
		while (temp != null && !(temp.getCommand().equals(c))) { 
			temp = temp.getNext();
			++i;
		}
		//Wenn das Element nicht existiert
		if (temp == null) {
			return -1;
		} 
		else {
			return i;
		}
	}

	/**
	 * method to delete all elements in the list
	 */
	public void clear() {
		root = null;
	}

	/**
	 * main function for the user interface
	 * @param args
	 */
	public static void main(String[] args) {

		CommandList list = new CommandList();

		//Konsolenausgabe für die Benutzerkommunikation
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
		
		//Abfrage was getan werden soll
		boolean abbruch = true;
		Scanner scanner = new Scanner(System.in);
		
		while(abbruch){

		System.out.println("\nBitte wählen: ");
		int wahl = scanner.nextInt();
		scanner.nextLine();
		
		//Auswahl der Funktionen
		//Eintrag anlegen
		if (wahl == 1) {
			
			System.out.print("Bitte ein Wort eingeben: ");
			String wort = scanner.nextLine();
			
			Command einEintrag = new Command(wort);
			Command returnVal = list.add(einEintrag);
			System.out.print("'" + returnVal.getName() + "'" + " wurde in die Liste eingefügt.");
			
		} 
		//Liste ausgeben
		else if (wahl == 2) {
			list.printCommands();
		}
		//länge der Liste ausgeben
		else if (wahl == 3) {
			System.out.println("Die Liste hat " + list.getSize() + " Einträge");
		}
		//Element löschen
		else if (wahl == 4) {
			System.out.print("Nummer dessen Eintrag gelöscht werden soll eingeben: ");
			int pos = scanner.nextInt();
			Command returnVal = list.remove(pos);
			if(returnVal != null)
			System.out.print("'" + returnVal.getName() + "'" + " wurde aus der Liste entfernt");
			else
				System.out.print("Kein Element an dieser Position");
		} 
		//Element an pos suchen
		else if (wahl == 5) {
			System.out.print("Nummer des Eintrags eingeben: ");
			int pos = scanner.nextInt();
			Command returnVal = list.getCommand(pos);
			if(returnVal != null)
			System.out.print("'" + returnVal.getName() + "'" + " steht an Position " + pos);
			else
				System.out.print("Kein Element an dieser Position");
		} 
		//Element an pos nach oben verschieben
		else if (wahl == 6) {
			System.out.print("Nummer des Eintrags eingeben: ");
			int pos = scanner.nextInt();
			Command returnVal = list.moveUp(pos);
			if(returnVal != null)
			System.out.print("'" + returnVal.getName() + "'" + " wurde um eine Position nach oben verschoben");
			else
				System.out.print("Erstes Element kann nicht weiter hoch bewegt werden");
		} 
		//Element an pos nach unten verschieben
		else if (wahl == 7) {
			System.out.print("Nummer des Eintrags eingeben: ");
			int pos = scanner.nextInt();
			Command returnVal = list.moveDown(pos);
			if(returnVal != null)
				System.out.print("'" + returnVal.getName() + "'" + " wurde um eine Position nach unten verschoben");
			else
				System.out.print("Letztes Element kann nicht weiter runter bewegt werden.");
		} 
		//Pos des Elements mit Eintrag c suchen
		else if (wahl == 8) {
			System.out.print("Inhalt des Eintrags eingeben: ");
            int pos = scanner.nextInt();
            Command c = list.getCommand(pos);
            System.out.print("Das Element hat die " + list.getPos(c) + ". Position in der Liste.");
		} 
		//Liste leeren
		else if (wahl == 9) {
			list.clear();
			System.out.print("Die Liste hat nun keine Einträge mehr");
		} 
		//Programm beenden
		else if (wahl == 10) {
			list.clear();
			System.out.print("Programm beendet");
			abbruch = false;
		} 
		//Ungültige Eingabe
		else {
			
			System.out.print("Falsche Eingabe");
		}
		
		}
		scanner.close();
	}
}
