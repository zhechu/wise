package com.wise.common.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json 工具类
 * @author lingyuwang
 *
 */
public final class JsonUtils {

	private JsonUtils() {}

	/**
	 * 将对象序列化成json字符串
	 * @param object javaBean
	 * @return jsonString json字符串
	 */
	public static String toJson(Object object) {
		try {
			return getInstance().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将json反序列化成对象
	 * @param jsonString jsonString
	 * @param valueType class
	 * @param <T> T 泛型标记
	 * @return Bean
	 */
	public static <T> T parse(String jsonString, Class<T> valueType) {
		try {
			return getInstance().readValue(jsonString, valueType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static ObjectMapper getInstance() {
		return JacksonHolder.INSTANCE;
	}

	private static class JacksonHolder {
		private static ObjectMapper INSTANCE = new JacksonObjectMapper();
	}

	static class JacksonObjectMapper extends ObjectMapper {

		private static final long serialVersionUID = 4288193147502386170L;

		public JacksonObjectMapper() {
			this.setLocale(Locale.CHINA);
			this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA));
			this.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		}

	}

}
