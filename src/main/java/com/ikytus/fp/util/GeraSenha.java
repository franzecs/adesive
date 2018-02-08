package com.ikytus.fp.util;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeraSenha {
			
	public static void main(String[] args) throws UnknownHostException, MalformedURLException{
		Tools tools = new Tools();
		System.out.println(tools.getIp("http://ik-gip.ddns.net/"));
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}

}
