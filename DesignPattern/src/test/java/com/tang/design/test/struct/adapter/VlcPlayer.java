package com.tang.design.test.struct.adapter;

import com.tang.design.test.util.PrintUtils;

public class VlcPlayer implements AdvanceMediaPlayer {

	@Override
	public void playVlc(String fileName) {
		PrintUtils.print("播放Vlc:" + fileName);
	}

	@Override
	public void playMp4(String fileName) {
		// 什么也不做
	}


}
