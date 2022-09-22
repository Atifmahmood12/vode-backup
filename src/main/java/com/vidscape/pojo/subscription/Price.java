package com.vidscape.pojo.subscription;

public class Price {
	
	private int USD;

    public void setUSD(int USD){
        this.USD = USD;
    }
    public int getUSD(){
        return this.USD;
    }
	@Override
	public String toString() {
		return "Price [USD=" + USD + "]";
	}
    
    

}
