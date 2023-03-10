/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTD;

/**
 *
 * @author 20101
 */
public class OTD_Status extends OTD {
    private boolean status;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public OTD_Status(int id,boolean status,String message) {
        super(id);
        this.status=status;
        this.message=message;
    }
    
}
