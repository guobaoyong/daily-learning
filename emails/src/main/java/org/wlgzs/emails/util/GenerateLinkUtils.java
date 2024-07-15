package org.wlgzs.emails.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wlgzs.emails.model.User;
import org.wlgzs.emails.service.UserService;

import javax.annotation.PostConstruct;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

/**
 * 生成帐户激活、重新设置密码的链接
 */
@Component
public class GenerateLinkUtils {
	
	private static final String CHECK_CODE = "checkCode";

	@Autowired
	private UserService userService;
	private static GenerateLinkUtils generateLinkUtils;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@PostConstruct
	public void init(){
		generateLinkUtils = this;
		generateLinkUtils.userService = this.userService;
	}

	/**
	 * 生成帐户激活链接
	 */
	public static String generateActivateLink(User user) {
		User user1 = generateLinkUtils.userService.selectByUserName(user.getUsername());
		user1.setRandomcode(generateCheckcode(user,0));
		generateLinkUtils.userService.updateByPrimaryKey(user1);
		return "http://localhost:8081/activate?id="
				+ user1.getId() + "&" + CHECK_CODE + "=" + generateCheckcode(user,0);
	}
	
	/**
	 * 生成重设密码的链接
	 */
	public static String generateResetPwdLink(User user) {
		user.setRandomcode(generateCheckcode(user,1));
		generateLinkUtils.userService.updateByPrimaryKey(user);
		return "http://localhost:8081/resetPasswordUI?userName="
				+ user.getUsername() + "&" + CHECK_CODE + "=" + generateCheckcode(user,1);
	}
	
	/**
	 * 生成验证帐户的MD5校验码
	 * @param user  要激活的帐户,n=0表示注册，n=1表示重置密码
	 * @return 将用户名和密码组合后，通过md5加密后的16进制格式的字符串
	 */
	public static String generateCheckcode(User user,int n) {
		//获取系统当前时间的小时数
		if (n == 0){
			return md5(user.getUsername() + ":" + user.getPassword()+':'+Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		}else if (n == 1){
			return md5(user.getUsername() + "+" + user.getPassword()+'+'+Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		}
		return null;
	}
	
	/**
	 * 验证校验码是否和注册时发送的验证码一致
	 * @param user 要激活的帐户
	 * @param
	 * checkcode 注册时发送的校验码
	 * @return 如果一致返回true，否则返回false
	 */
	public static boolean verifyCheckcode(User user, String checkCode,int i) {
		boolean a=false,b=false;
		try {
			a = user.getRandomcode().equals(checkCode);
			b = generateCheckcode(user,i).equals(checkCode);
			return a == b;
		}catch (Exception e){
			return false;
		}
	}

	private static String md5(String string) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("md5");
			md.update(string.getBytes());
			byte[] md5Bytes = md.digest();
			return bytes2Hex(md5Bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static String bytes2Hex(byte[] byteArray)
	{
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++)
		{
			if(byteArray[i] >= 0 && byteArray[i] < 16)
			{
				strBuf.append("0");
			}
			strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
		}
		return strBuf.toString();
	}

}
