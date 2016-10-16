package com.bolly.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

	private static Gson gson;

	private static GsonUtil INSTANCE = new GsonUtil();

	private GsonUtil(){
		gson = new GsonBuilder().create();
	}

	public static GsonUtil getInstance(){
		return INSTANCE;
	}

	public Gson getGson(){
		return gson;
	}
}
