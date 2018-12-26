package test.entityAudit;

import java.util.Date;

public class Message {

    private String name;
    private String json;
    private Date dateTime;

    public Message() {
    }

    public Message(String name, String json, Date dateTime) {
        this.name = name;
        this.json = json;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
