package com.vidscape.constants;

import com.vidscape.configs.ProjectConfigs;

public interface APIEndPoints {

	public static String AUTH_API = "/admin/authenticate";
	public static String AUTH_API_USERNAME = "x-Auth-Username";
	public static String AUTH_API_PASSWORD = "X-Auth-Password";
	public static String AUTH_TOKEN_HEADER_FIELD = "X-Auth-Token";
	public static String GENRE_URI = "/genre/getGenres?language=";

	public static String CONTENT_TYPE_URI = "/admin/content/types";
	public static String ALL_CONTENT_URI = "/admin/content/all";
	public static String ALL_CONTENT_URI_QUERYSTRING_STATUS = "?status=";
	public static String ALL_CONTENT_URI_QUERYSTRING_CONTENT_TYPE = "&contentTypeIds=";
	public static String ALL_CONTENT_URI_QUERYSTRING_CURRENT = "&current=";
	public static String ALL_CONTENT_URI_QUERYSTRING_SIZE = "&size=";
	public static String ALL_CONTENT_URI_QUERYSTRING_IS_AVAILABLE = "&isAvailable=";
	public static String ALL_CONTENT_URI_QUERYSTRING_IS_LANGUAGE = "&language=";

	public static String PRICING_MODEL_URI = "/admin/content/getPricingModels?language=";
	public static String SERVER_API_AUTH_URL = "/admin/authenticate";

	public static String GET_HOME_GROUP = "https://kplus-stage-admin.vidscapeapp.com/admin/authenticate";
	public static String AUTH_TOKEN = "X-Auth-Token";

	public static String BANNER_API_BASE_PATH = ProjectConfigs.getBANNER_API_BASE_PATH();
	public static String ENG_LANGUAGE = "en-GB";
	public static String VN_LANGUAGE = "vi-vn";
	public static String LANGUAGE_PARAM = "language=";
	public static String SEARCH_VAR_PARAM = "searchText==";
	public static String SEARCH_FROM_PARAM = "&from=";
	public static String SEARCH_SIZE_PARAM = "&size=";
	public static String SEARCH_TYPE_PARAM = "&type=";

	public static String CONTENT_TYPE_VALUE = "application/json;charset=UTF-8";
	public static int API_SUCCESS_STATUS_CODE = 200;

}
