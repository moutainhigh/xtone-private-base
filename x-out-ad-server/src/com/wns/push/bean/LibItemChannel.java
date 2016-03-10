package com.wns.push.bean;

import java.io.Serializable;

public class LibItemChannel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2918618989828715850L;

	public String getChannel()
    {
        return this._channel;
    }

    public void setChannel(String channel)
    {
        this._channel = channel;
    }
    
    private String _channel;
}
