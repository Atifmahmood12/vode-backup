package com.vidscape.pojo.common;

public class Translation {
	private String vi;

    private String eng;

    public void setVi(String vi){
        this.vi = vi;
    }
    public String getVi(){
        return this.vi;
    }
    public void setEng(String eng){
        this.eng = eng;
    }
    public String getEng(){
        return this.eng;
    }
	@Override
	public String toString() {
		return "Title [vi=" + vi + ", eng=" + eng + "]";
	}


}
