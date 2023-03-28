/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OTD;

import java.sql.Date;

/**
 *
 * @author 20101
 */
public class OTD_Friends extends OTD{
    Date date;
    String username;
    String email;
    String notified;
    String status;

    public OTD_Friends(Date date, String username, String email, int id) {
        super(id);
        this.date = date;
        this.username = username;
        this.email = email;
    }

    public OTD_Friends(Date date, String username, String email, String notified, String status, int id) {
        super(id);
        this.date = date;
        this.username = username;
        this.email = email;
        this.notified = notified;
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotified() {
        return notified;
    }

    public void setNotified(String notified) {
        this.notified = notified;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
