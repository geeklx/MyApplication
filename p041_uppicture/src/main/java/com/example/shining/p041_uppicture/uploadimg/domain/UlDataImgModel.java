package com.example.shining.p041_uppicture.uploadimg.domain;

import java.io.Serializable;

public class UlDataImgModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Url;
	private String Extension;
	private String Bytes;

	public UlDataImgModel() {
	}

	public UlDataImgModel(String url, String extension, String bytes) {
		Url = url;
		Extension = extension;
		Bytes = bytes;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getExtension() {
		return Extension;
	}

	public void setExtension(String extension) {
		Extension = extension;
	}

	public String getBytes() {
		return Bytes;
	}

	public void setBytes(String bytes) {
		Bytes = bytes;
	}

}
