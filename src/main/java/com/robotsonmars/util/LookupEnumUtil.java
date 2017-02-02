package com.robotsonmars.util;

import com.robotsonmars.exceptions.UnSupportedCommandException;

public class LookupEnumUtil {
	
	public static <T extends Enum<T>> T lookupCommand(Class<T> t, String id, String message) throws UnSupportedCommandException{
		T key = null;
		try{
			key = Enum.valueOf(t, id);
		}catch(IllegalArgumentException u){
			throw new UnSupportedCommandException(message, u);
		}
		return key;
	}

}