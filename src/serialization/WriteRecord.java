package serialization;

/**
 * Started by M. Moussavi
 * March 2015
 * Completed by: STUDENT(S) NAME
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class WriteRecord {

	ObjectOutputStream objectOut = null;
	MusicRecord record = null;
	ArrayList<MusicRecord> arr = new ArrayList<MusicRecord>();
	Scanner stdin = null;
	Scanner textFileIn = null;

	/**
	 * Creates an blank MusicRecord object
	 */
	public WriteRecord() {
		record = new MusicRecord();
	}

	/**
	 * Initializes the data fields of a record object
	 * 
	 * @param year       - year that song was purchased
	 * @param songName   - name of the song
	 * @param singerName - singer's name
	 * @param price      - CD price
	 */
	public void setRecord(int year, String songName, String singerName, double price) {
		record.setSongName(songName);
		record.setSingerName(singerName);
		record.setYear(year);
		record.setPrice(price);
	}

	/**
	 * Opens a file input stream, using the data field textFileIn
	 * 
	 * @param textFileName name of text file to open
	 */
	public void openFileInputStream(String textFileName) {
		try {
			FileInputStream inputFile = new FileInputStream(textFileName);
			textFileIn = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Opens an ObjectOutputStream using objectOut data field
	 * 
	 * @param objectFileName name of the object file to be created
	 */
	public void openObjectOutputStream(String objectFileName) {
		FileOutputStream outputFile;
		try {
			outputFile = new FileOutputStream(objectFileName);
			objectOut = new ObjectOutputStream(outputFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Reads records from given text file, fills the blank MusicRecord created by
	 * the constructor with the existing data in the text file and serializes each
	 * record object into a binary file
	 */
	public void createObjectFile() {

		while (textFileIn.hasNext()) // loop until end of text file is reached
		{
			int year = Integer.parseInt(textFileIn.nextLine());
			System.out.print(year + "  "); // echo data read from text file

			String songName = textFileIn.nextLine();
			System.out.print(songName + "  "); // echo data read from text file

			String singerName = textFileIn.nextLine();
			System.out.print(singerName + "  "); // echo data read from text file

			double price = Double.parseDouble(textFileIn.nextLine());
			System.out.println(price + "  "); // echo data read from text file

			setRecord(year, songName, singerName, price);
			textFileIn.nextLine(); // read the dashed lines and do nothing
			
			try {
				objectOut.writeObject(record);
				objectOut.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws IOException {

		WriteRecord d = new WriteRecord();

		String textFileName = "someSongs.txt"; // Name of a text file that contains
												// song records

		String objectFileName = "mySongs.ser"; // Name of the binary file to
												// serialize record objects

		d.openFileInputStream(textFileName); // open the text file to read from

		d.openObjectOutputStream(objectFileName); // open the object file to
													// write music records into it

		d.createObjectFile(); // read records from opened text file, and write
								// them into the object file.
	}
}
