package com.tang.design.test.struct.adapter;
/**
 * 
 * <p>2019年11月20日</p>
 * @author tangxing
 * <p> 实现了media接口的适配器 </p>
 */
public class MediaAdapter implements MediaPlayer {

	private AdvanceMediaPlayer advanceMediaPlayer;
	
	public static final String VLC = "vlc";
	public static final String MP4 = "mp4";
	
	public MediaAdapter(String auditType) {
		if(VLC.equalsIgnoreCase(auditType)) {
			advanceMediaPlayer = new VlcPlayer();
		}else if(MP4.equalsIgnoreCase(auditType) ) {
			advanceMediaPlayer = new Mp4Player();
		}
	}
	
	@Override
	public void play(String auditType, String fileName) {
		if(VLC.equalsIgnoreCase(auditType)) {
			advanceMediaPlayer.playVlc(fileName);
		}else if(MP4.equalsIgnoreCase(auditType) ) {
			advanceMediaPlayer.playMp4(fileName);
		}
	}
}
