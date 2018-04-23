/*
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Troy Stidd
 * tvs266
 * 15495
 * Slip days used: <0>
 * Spring 2018
 */
package assignment6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Theater {
	/*
	 * Represents a seat in the theater
	 * A1, A2, A3, ... B1, B2, B3 ...
	 */
	static class Seat {
		private int rowNum;
		private int seatNum;

		public Seat(int rowNum, int seatNum) {
			this.rowNum = rowNum;
			this.seatNum = seatNum;
		}

		public int getSeatNum() {
			return seatNum;
		}

		public int getRowNum() {
			return rowNum;
		}

		@Override
		public String toString() {
			// TODO: Implement this method to return the full Seat location ex: A1
			return toLetter(rowNum) + seatNum;
		}

		private String toLetter(int i){
			int q = i/26;
			int r = i%26;
			char letter = (char) ((int) 'A' + r - 1);
			if(r==0){
				q--;
				letter='Z';
			}
			if(q==0)
				return ""+letter;
			else
				return toLetter(q) + letter;
		}
	}

  /*
	 * Represents a ticket purchased by a client
	 */
	static class Ticket {
		private String show;
		private String boxOfficeId;
		private Seat seat;
	  private int client;

		public Ticket(String show, String boxOfficeId, Seat seat, int client) {
			this.show = show;
			this.boxOfficeId = boxOfficeId;
			this.seat = seat;
			this.client = client;
		}

		public Seat getSeat() {
			return seat;
		}

		public String getShow() {
			return show;
		}

		public String getBoxOfficeId() {
			return boxOfficeId;
		}

		public int getClient() {
			return client;
		}

		@Override
		public String toString() {
			// TODO: Implement this method to return a string that resembles a ticket
			//System.out.println("tostring called");
			/*
			String result = "";
			result += "-------------------------------\n";
			result+= String.format("| Show: %31s |\n", show);
			result+= String.format("| Box Office ID: %31s |\n", boxOfficeId);
			result+= String.format("| Seat: %31s |\n", seat);
			result+= String.format("| Client: %31s |\n", client);
			result += "-------------------------------\n";*/

			StringBuilder result = new StringBuilder();
			for (int i = 0; i < 31; i++) {
				result.append('-');
			}
			result.append("\n");

			result.append("| Show: " + show);
			for (int i = 0; i < 23-show.length(); i++) {
				result.append(' ');
			}
			result.append("|" + "\n");

			result.append("| Box Office ID: " + boxOfficeId);
			for (int i = 0; i < 14-boxOfficeId.length(); i++) {
				result.append(' ');
			}
			result.append("|" + "\n");

			result.append("| Seat: " +  seat.toString());
			for (int i = 0; i < 23-seat.toString().length(); i++) {
				result.append(' ');
			}
			result.append("|"  + "\n");

			result.append("| Client: " + Integer.toString(client));
			for (int i = 0; i < 21-Integer.toString(client).length(); i++) {
				result.append(' ');
			}
			result.append("|" + "\n");

			for (int i = 0; i < 31; i++) {
				result.append('-');
			}
			result.append("\n");

			return result.toString();
		}
	}

	private int numRows;
	private int numSeats;
	private String name;
	int currentRow = 1;
	int currentSeat = 1;
	boolean fullcheck;
	private List<Ticket> tickList = new ArrayList<Ticket>();
	private Set<String> checkList = new HashSet<String>();


	public Theater(int numRows, int seatsPerRow, String show) {
		// TODO: Implement this constructor
		this.numRows = numRows;
		numSeats = seatsPerRow;
		name = show;

	}

	/*
	 * Calculates the best seat not yet reserved
	 *
 	 * @return the best seat or null if theater is full
   */
	public Seat bestAvailableSeat() {
		//TODO: Implement this method
		Seat seat = new Seat(currentRow, currentSeat);
		if(currentRow > numRows){
			//System.out.println("fullcheck set");
			fullcheck = true;
			return null;
		}
		return seat;
	}

	/*
	 * Prints a ticket for the client after they reserve a seat
   * Also prints the ticket to the console
	 *
   * @param seat a particular seat in the theater
   * @return a ticket or null if a box office failed to reserve the seat
   */
	public Ticket printTicket(String boxOfficeId, Seat seat, int client) {
		//TODO: Implement this method
		Ticket t = new Ticket(name, boxOfficeId, seat, client);
		if (checkList.contains(seat.toString()))
			return null;
		checkList.add(tickList.toString());
		tickList.add(t);
		System.out.println(t.toString());
		int prevseat = currentSeat;
		currentSeat = (currentSeat + 1) % numSeats;
		if (currentSeat == 0)
			currentSeat = numSeats;
		if (prevseat > currentSeat)
			currentRow++;
		if (numSeats == 1)
			currentRow++;
		return t;
	}

	/*
	 * Lists all tickets sold for this theater in order of purchase
	 *
   * @return list of tickets sold
   */
	public List<Ticket> getTransactionLog() {
		//TODO: Implement this method
		return tickList;
	}

	public boolean maxcap(){
		//System.out.println("maxcap called");
		return fullcheck;
	}
}
