
/** 
 * Started by M. Moussavi
 * March 2015
 * Completed by: Sonia Islam and Scott Tianhan Jiang
 */

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadRecord {

	private ObjectInputStream input;

	/**
	 * opens an ObjectInputStream using a FileInputStream
	 */
	private void readObjectsFromFile(String name) {
		MusicRecord record;

		try {
			input = new ObjectInputStream(new FileInputStream(name));
		} catch (IOException ioException) {
			System.err.println("Error opening file.");
		}

		/**
		 * The following loop is supposed to use readObject method of ObjectInputSteam
		 * to read a MusicRecord object from a binary file that contains several
		 * records. Loop should terminate when an EOFException is thrown.
		 */
		try {
			while (true) {
				try {
					record = (MusicRecord) input.readObject();
					System.out.println(record.getYear() + " " + record.getSongName() + " " + record.getSingerName()
							+ " " + record.getPurchasePrice());
				} catch (EOFException e) {
					break;
				}
			} // END OF WHILE
		} catch (ClassNotFoundException e) {
			System.err.println("This casted class do not exist.");
		} catch (IOException e) {
			System.err.println("File do not exist or reading error.");
		}

	}

	public static void main(String[] args) {
		ReadRecord d = new ReadRecord();
		d.readObjectsFromFile("allSongs.ser");
	}
}