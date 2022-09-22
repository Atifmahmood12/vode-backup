package com.vidscape.pojo.moviecontent;

public class VideoContents {

	 private String contentType;

	    private String duration;

	    private String entitlementContentId;

	    private ContentRedention renditions;

		public String getContentType() {
			return contentType;
		}

		public void setContentType(String contentType) {
			this.contentType = contentType;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public String getEntitlementContentId() {
			return entitlementContentId;
		}

		public void setEntitlementContentId(String entitlementContentId) {
			this.entitlementContentId = entitlementContentId;
		}

		public ContentRedention getRenditions() {
			return renditions;
		}

		public void setRenditions(ContentRedention renditions) {
			this.renditions = renditions;
		}

		@Override
		public String toString() {
			return "VideoContents [contentType=" + contentType + ", duration=" + duration + ", entitlementContentId="
					+ entitlementContentId + ", renditions=" + renditions + "]";
		}
	    	
}
