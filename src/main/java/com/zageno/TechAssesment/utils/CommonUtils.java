package com.zageno.TechAssesment.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class CommonUtils
{
	public static int randomStringLength=7;

	public static String generateRandomString(){
		return RandomStringUtils.random(randomStringLength, true, true);
	}
}
