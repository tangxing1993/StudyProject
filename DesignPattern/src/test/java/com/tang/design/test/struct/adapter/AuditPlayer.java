package com.tang.design.test.struct.adapter;

import com.tang.design.test.util.PrintUtils;

public class AuditPlayer implements MediaPlayer {
	
	private MediaAdapter mediaAdapter;

	@Override
	public void play(String auditType, String fileName) {
		if("mp3".equalsIgnoreCase(auditType)) {
			  PrintUtils.print("播放Mp3:" + fileName);
		}else if(MediaAdapter.VLC.equalsIgnoreCase(auditType)) {
			mediaAdapter = new MediaAdapter(MediaAdapter.VLC);
			mediaAdapter.play(auditType, fileName);
		}else if(MediaAdapter.MP4.equalsIgnoreCase(auditType)) {
			mediaAdapter = new MediaAdapter(MediaAdapter.MP4);
			mediaAdapter.play(auditType, fileName);
		}else {
			PrintUtils.print("未找到适配的媒体类型:" + auditType);
		}
	}

}
