package org.kalinga.Rest2.Model;

public class ErrorMessage {
private String errormsg;
private int errorcode;

public ErrorMessage() {
	super();

}


public ErrorMessage(String errormsg, int errorcode) {
	super();
	this.errormsg = errormsg;
	this.errorcode = errorcode;
}
public String getErrormsg() {
	return errormsg;
}
public void setErrormsg(String errormsg) {
	this.errormsg = errormsg;
}
public int getErrorcode() {
	return errorcode;
}
public void setErrorcode(int errorcode) {
	this.errorcode = errorcode;
}
}
