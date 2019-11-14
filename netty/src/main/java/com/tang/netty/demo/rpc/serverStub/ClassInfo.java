package com.tang.netty.demo.rpc.serverStub;

import java.io.Serializable;

import lombok.Data;

/**
   *   类的信息
 * @author tangxing
 *
 */
@Data
public class ClassInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String className;
	
	private String methodName;
	
	private Class<?>[] types;
	
	private Object[] objects;
}
