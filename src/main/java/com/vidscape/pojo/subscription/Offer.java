package com.vidscape.pojo.subscription;

public class Offer {
	
	
	private String id;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
	@Override
	public String toString() {
		return "Offer [id=" + id + "]";
	}
}
