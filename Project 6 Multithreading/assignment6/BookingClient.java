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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Thread;

public class BookingClient {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("BX1", 3);
        map.put("BX3", 3);
		map.put("BX2", 4);
		map.put("BX5", 3);
		map.put("BX4", 3);
        Theater newtheater = new Theater(3, 5, "Ouija");
        BookingClient book = new BookingClient(map, newtheater);
        List<Thread> list = book.simulate();
        try{
            for(Thread t : list)
                t.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        try{
            Thread.sleep(200);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        if(book.theater.maxcap()){
            System.out.print("\n" + "Sorry, we are sold out!");
        }
    }
  /*
   * @param office maps box office id to number of customers in line
   * @param theater the theater where the show is playing
   */
  private int clientID = 1;
  private Theater theater;
  private Map<String, Integer> boxOffice;
  public BookingClient(Map<String, Integer> office, Theater theater) {
    // TODO: Implement this constructor
      boxOffice = office;
      this.theater = theater;
  }

  /*
   * Starts the box office simulation by creating (and starting) threads
   * for each box office to sell tickets for the given theater
   *
   * @return list of threads used in the simulation,
   *         should have as many threads as there are box offices
   */
	public List<Thread> simulate() {
		//TODO: Implement this method
        List<Thread> list = new ArrayList<Thread>();
        for(Map.Entry<String, Integer> entry : boxOffice.entrySet()){
            Thread t = new Thread(){
                @Override
                    public void run(){
                    for (int i = 0; i < entry.getValue(); i++) {
                        reserve(entry.getKey());
                    }
                }
            };
            t.start();
            try{
                for(Thread thr : list)
                    thr.join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            list.add(t);
        }

        return new ArrayList<Thread>();
	}

	public synchronized void reserve(String boxID){
	    int currentClient = clientID;
	    clientID++;
	    Theater.Seat seat = theater.bestAvailableSeat();
	    if(seat != null){
	        while(true){
                Theater.Ticket ticket = theater.printTicket(boxID, seat, currentClient);
                try{
                    Thread.sleep(50);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                if(ticket!= null){
                    break;
                }
                seat = theater.bestAvailableSeat();
            }
        }
    }
}
