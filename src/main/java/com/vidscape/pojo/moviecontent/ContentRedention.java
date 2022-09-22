package com.vidscape.pojo.moviecontent;

public class ContentRedention {
	
	 private ContentURLs MOBILE_HD;

	    private ContentURLs PC_SS_HD;

	    private ContentURLs PC_DASH_HD;

		public ContentURLs getMOBILE_HD() {
			return MOBILE_HD;
		}

		public void setMOBILE_HD(ContentURLs mOBILE_HD) {
			MOBILE_HD = mOBILE_HD;
		}

		public ContentURLs getPC_SS_HD() {
			return PC_SS_HD;
		}

		public void setPC_SS_HD(ContentURLs pC_SS_HD) {
			PC_SS_HD = pC_SS_HD;
		}

		public ContentURLs getPC_DASH_HD() {
			return PC_DASH_HD;
		}

		public void setPC_DASH_HD(ContentURLs pC_DASH_HD) {
			PC_DASH_HD = pC_DASH_HD;
		}

		@Override
		public String toString() {
			return "ContentRedention [MOBILE_HD=" + MOBILE_HD + ", PC_SS_HD=" + PC_SS_HD + ", PC_DASH_HD=" + PC_DASH_HD
					+ "]";
		}
	    	

}
