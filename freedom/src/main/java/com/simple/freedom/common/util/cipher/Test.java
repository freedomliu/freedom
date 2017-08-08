package com.simple.freedom.common.util.cipher;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;


public class Test {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		// sha1 加密
		System.out.println(DigestUtils.sha1Hex("abc".getBytes()));

	}
}
