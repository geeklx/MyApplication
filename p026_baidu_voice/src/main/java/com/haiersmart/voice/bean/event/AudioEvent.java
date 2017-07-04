package com.haiersmart.voice.bean.event;

/**
 * @作者:gaoruishan
 * @时间:2017/2/10/20:06
 * @邮箱:grs0515@163.com
 */

public class AudioEvent {


	public String cover_url;
	public String mp3_audio_url;
	public String title;

	public AudioEvent(String mp3_audio_url, String cover_url, String title) {
		this.mp3_audio_url = mp3_audio_url;
		this.cover_url = cover_url;
		this.title = title;
	}

}
