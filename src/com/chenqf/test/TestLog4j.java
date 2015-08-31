package com.chenqf.test;

import org.apache.log4j.Logger;


public class TestLog4j {
	
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(TestLog4j.class);
		logger.debug("调试的");
		logger.info("普通的");
		logger.warn("警告的");
		logger.error("错误的");
		logger.fatal("致命的");
	}
}
