package com.scottmangiapane.courseevaluation.util;

import java.util.List;
import java.util.Map;

public class CheckUtils
{
	public static boolean isEmpty( Object obj )
	{
		boolean bl = false;
		String temString = "";
		if (obj == null) {
			bl = true;
		} else if (obj instanceof String) {
			
			String objString = (String)obj;
			if(objString.length() < 10 && objString.length() > 0 ){
				for ( int i = 0; i < objString.length(); i++ ) 
				{
					char c = objString.charAt( i );
					if ( c != ' ' && c != '\t' && c != '\r' && c != '\n' && c != '[' && c != ']' && c != '{' && c != '}')
					{
						temString = temString + String.valueOf(c);
					}
				}
				if(temString.trim().length() == 0) {
					bl = true;
				}
			}
			if(objString.trim().length() == 0){
				bl = true;
			}
	
		} else if (obj instanceof List) {
			if(((List)obj).isEmpty()){
				bl = true;
			}
		} else if (obj instanceof Map) {
			if(((Map)obj).isEmpty()){
				bl = true;
			}
		} else {
			
		}
		return bl;
	}
}