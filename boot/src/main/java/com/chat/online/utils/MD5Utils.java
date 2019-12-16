package com.chat.online.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Utils {
	private static final String ENCRYPTION_SOURCE = "365KKKKK^&*$$#@FFF996";
	public static String buildMD5Code(String sourceMessage) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] tmpsource = sourceMessage.getBytes();
			md.update(tmpsource);
			tmpsource = md.digest();
			for (int i = 0; i < tmpsource.length; ++i) {
				String tmp = Integer.toHexString(tmpsource[i] & 0xFF);
				if (tmp.length() == 1)
					sb = sb.append("0").append(tmp) ;
				else
					sb = sb.append(tmp);
			}
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}


}
