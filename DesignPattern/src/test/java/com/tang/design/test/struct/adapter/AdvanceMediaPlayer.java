package com.tang.design.test.struct.adapter;
/**
 * 
 * <p>2019年11月20日</p>
 * @author tangxing
 * <p> 增强的媒体播放器 </p>
 */
public interface AdvanceMediaPlayer {

	/**
	 * 播放vlc
	 */
	void playVlc(String fileName);
	
	/**
	 * 播放MP4
	 */
	void playMp4(String fileName);
	
}
