package domain;

import java.util.Date;

public class Wydarzenie implements Cloneable {
	private Date data;
	private String event;
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
