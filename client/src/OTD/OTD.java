/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTD;

import java.io.Serializable;

/**
 *
 * @author 20101
 */
public  class OTD  implements Serializable  {
    private int id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public OTD(int id) {
        this.id = id;
    }
}

