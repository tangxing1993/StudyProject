package com.tang.design.test.struct.adapter;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * 
 * <p>2019年11月20日</p>
 * @author tangxing
 * <p> 测试适配器模式 </p>
 */
public class AdapterPatternTest extends TestCase{

	@Test
	public void testAdapter() {
		AuditPlayer playper = new AuditPlayer();
		playper.play("mp3", "1.mp3");
		playper.play("mp4", "2.mp4");
		playper.play("vlc", "3.vlc");
		playper.play("avi", "4.avi");
	}
	
}
