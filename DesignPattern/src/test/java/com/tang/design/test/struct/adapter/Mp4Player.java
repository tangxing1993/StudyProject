package com.tang.design.test.struct.adapter;

import com.tang.design.test.util.PrintUtils;

/**
 * 
 * <p>2019年11月20日</p>
 * @author tangxing
 * <p> MP4播放器 </p>
 */
public class Mp4Player implements AdvanceMediaPlayer {

	@Override
	public void playVlc(String fileName) {
		// 什么也不做
	}

	@Override
	public void playMp4(String fileName) {
		PrintUtils.print("播放Mp4:" + fileName);
	}

}
