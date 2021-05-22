package com.demo.inventory.management.utils;

public class Constants {
	
	private Constants() {}
	
	public static final String API_V1_URL = "/api/v1";
	public static final String AUTH_URL = "/auth";
	
	public static final String PRODUCT_FORM_SAVED = "Product Form Saved";
	public static final String PRODUCT_FORM_UPDATED = "Product Form Updated";
	
	public static final String METHOD_NOT_ALLOWED = "Method Not Allowed";
	public static final String NOT_FOUND = "Not Found";
	
    //CORS Related
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    public static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    public static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    public static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
    public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    public static final String OPTIONS = "OPTIONS";
    public static final String ACCESS_CONTROL_ALLOW_METHODS_LIST = "ACL, CANCELUPLOAD, CHECKIN, CHECKOUT, COPY, DELETE, GET, HEAD, LOCK, MKCALENDAR, MKCOL, MOVE, OPTIONS, POST, PROPFIND, PROPPATCH, PUT, REPORT, SEARCH, UNCHECKOUT, UNLOCK, UPDATE, VERSION-CONTROL";
    public static final String ACCESS_CONTROL_ALLOW_HEADERS_LIST = "X-Token,timezone,device-name,device-os-version,device-os,Content-Type,app-version,Origin, X-Requested-With, Content-Type, Accept, Key, Authorization";
    public static final String MAX_AGE_VALUE = "3600";
    public static final String TRUE = "true";
	
	

}
