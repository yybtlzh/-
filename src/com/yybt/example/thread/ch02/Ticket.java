/**
 * @Title: Ticket.java
 * @Package com.yybt.example.thread.ch02
 */
package com.yybt.example.thread.ch02;

/**
  * @ClassName: Ticket
  * @Description: Ʊ
  * @author liuzehong
 **/
public class Ticket {
	
	private String ticketName;
	
	private int ticketId;

	public Ticket(String ticketName, int ticketId) {
		super();
		this.ticketName = ticketName;
		this.ticketId = ticketId;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	@Override
	public String toString() {
		return "ticketName:" + ticketName + ", ticketId:" + ticketId;
	}
	
	
	
	

}
