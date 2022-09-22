package com.vidscape.pojo.genre;

import com.vidscape.pojo.common.Translation;

public class GenreRoot {
	private String id;
	private String genrePath;

	private Translation title;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public Translation getTitle() {
		return title;
	}

	public void setTitle(Translation title) {
		this.title = title;
	}

	public String getGenrePath() {
		return genrePath;
	}

	public void setGenrePath(String genrePath) {
		this.genrePath = genrePath;
	}

	@Override
	public String toString() {
		return "GenreRoot [id=" + id + ", genrePath=" + genrePath + ", title=" + title + "]";
	}
	

	
	

}
