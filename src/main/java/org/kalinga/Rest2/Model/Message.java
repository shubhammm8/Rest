package org.kalinga.Rest2.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="msgg")
@XmlRootElement
public class Message {

	
	private int id;
	private String to;
	private String from;
	private String msg;
	//private String date;
	
	public Message(){
		
	}
	@Id
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="too")
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	@Column(name="fromm")

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	@Column(name="mg")

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
