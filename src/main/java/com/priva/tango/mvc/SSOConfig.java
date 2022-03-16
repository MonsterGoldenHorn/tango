package com.priva.tango.mvc;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;



/**
 * 单点登录信息
 * 
 * @Copyright: 川大智胜系统集成公司 
 * @Time: 2020年8月22日 下午12:33:20
 * @author zhou.bo
 * @version 1.0
 */
@Component
@PropertySource(value={"classpath:config/jxj/sso-dev.properties"})
public class SSOConfig {
	/**
	 * sso配置文件
	 */
	public static final String SSO_PROPERTIES="sso.properties";
	/**
	 * authorize接口地址
	 */
	public static final String AUTHORIZE_URL="sso/auth2/authorize.dhtml";
	/**
	 * accessToken接口地址（获取token）
	 */
	public static final String ACCESSTOKEN_URL="sso/auth2/accessToken.dhtml";
	/**
	 * Logout 接口地址(退出)
	 */
	public static final String LOGOUT_URL="sso/auth2/logout.dhtml";
	/**
	 * myAccount 接口地址（获取用户信息）
	 */
	public static final String MYACCOUNT_URL="sso/auth2/myAccount.dhtml";
	/**
	 * myAccount 接口地址（获取用户信息）
	 */
	public static final String my_URL="sso/auth2/myAccount.dhtml";
	/**
	 * 接口地址（获取用户信息）
	 */
	public static final String FIND_USERINFO_URL="sys/api/findUserInfoByUserId.dhtml";
	/**
	 * 重定向地址
	 */
	public static final String REDIRECT_URI="redirect_uri";
	/**
	 * 登录地址
	 */
	public static final String SYSTEM_LOGIN_URI="system_login_url";
	/**
	 * 注销地址
	 */
	public static final String SYSTEM_LOGIN_OUT_URI="system_login_out_url";
	/**
	 * 客户端key
	 */
	public static final String LOGIN_UNIQUE_KEY  ="login_unique_key";
	/**
	 * 刷新用户登录信息
	 */
	public static final String REFRSH_LOGIN_USER_USER="/sso/auth2/refreshLoginUser.dhtml";
	/**
	 * 客户端id/code
	 */
	@Value("${client_id}")
	private String clientId;
	/**
	 * 请求地址
	 */
	@Value("${sso_url}")
	private String ssoUrl;
	/**
	 * 密钥
	 */
	@Value("${client_secret}")
	private String  clientSecret;
	/**
	 * 访问类型
	 */
	private String  grantType="authorization_code";
	/**
	 * 响应类型
	 */
	private String responseType="code";
	/**
	 * 重定向地址
	 */
	@Value("${redirect_uri}")
	private String redirectUri;
	/**
	 * 访问令牌token
	 */
	private String accessToken;
	/**
	 * 过期时间
	 */
	private int expiresIn;
	/**
	 * 授权码
	 */
	private String code;
	/**
	 * 登录地址
	 */
	public static String systemLoginUrl;
	/**
	 * 注销地址
	 */
	@Value("${system_login_out_url}")
	private String systemLoginOutUrl;

	/**
	 * @return 获取 登录地址
	 */
	public String getSystemLoginUrl() {
		return systemLoginUrl;
	}
	

	/**
	 * @param 设置 登录地址 
	 */
	@Value("${system_login_url}")
	public void setSystemLoginUrl(String systemLoginUrl) {
		SSOConfig.systemLoginUrl = systemLoginUrl;
	}
	

	/**
	 * @return 获取 注销地址
	 */
	public String getSystemLoginOutUrl() {
		return systemLoginOutUrl;
	}
	

	/**
	 * @param 设置 注销地址 
	 */
	public void setSystemLoginOutUrl(String systemLoginOutUrl) {
		this.systemLoginOutUrl = systemLoginOutUrl;
	}
	

//	public static SSOConfig getInstance() {
//		SSOConfig config=new SSOConfig();
////		Properties properties = PropertiesUtil.getProperties(ResourceUtil.getResourceAsStream(SSO_PROPERTIES));
////		config.setClientId(properties.getProperty("client_id"));
////		config.setClientSecret(properties.getProperty("client_secret"));
////		config.setSsoUrl(properties.getProperty("sso_url"));
////		config.setSystemLoginUrl(properties.getProperty(SYSTEM_LOGIN_URI));
////		config.setSystemLoginOutUrl(properties.getProperty(SYSTEM_LOGIN_OUT_URI));
////		config.setRedirectUri(properties.getProperty(REDIRECT_URI));
//		return config;
//	}

	/**
	 * @return 获取 客户端id/code
	 */
	public String getClientId() {
		return clientId;
	}
	

	/**
	 * @param 设置 客户端id/code
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	

	/**
	 * @return 获取 请求地址
	 */
	public String getSsoUrl() {
		return ssoUrl;
	}
	

	/**
	 * @param 设置 请求地址 
	 */
	public void setSsoUrl(String ssoUrl) {
		this.ssoUrl = ssoUrl;
	}
	

	/**
	 * @return 获取 密钥
	 */
	public String getClientSecret() {
		return clientSecret;
	}
	

	/**
	 * @param 设置 密钥 
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	

	/**
	 * @return 获取 访问类型
	 */
	public String getGrantType() {
		return grantType;
	}
	

	/**
	 * @param 设置 访问类型 
	 */
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	

	/**
	 * @return 获取 响应类型
	 */
	public String getResponseType() {
		return responseType;
	}
	

	/**
	 * @param 设置 响应类型 
	 */
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	

	/**
	 * @return 获取 重定向地址
	 */
	public String getRedirectUri() {
		return redirectUri;
	}
	

	/**
	 * @param 设置 重定向地址 
	 */
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	

	/**
	 * @return 获取 访问令牌token
	 */
	public String getAccessToken() {
		return accessToken;
	}
	

	/**
	 * @param 设置 访问令牌token 
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	

	/**
	 * @return 获取 过期时间
	 */
	public int getExpiresIn() {
		return expiresIn;
	}
	

	/**
	 * @param 设置 过期时间 
	 */
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	/**
	 * @return 获取 授权码
	 */
	public String getCode() {
		return code;
	}
	

	/**
	 * @param 设置 授权码 
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	

	
	
	
	
}
