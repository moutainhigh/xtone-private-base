package org.demo.info;
/**
 * 用户管理界面GSON对象类
 * @author dev.jiabin
 *
 */
public class AdUser {
	/**
	 * 以下字段以user-update.jsp中json封装的字段相对应
	 */
	private long id;         //用户ID
	private String user;     //用户名称
	private int type;        //状态码，用于判断信息是增加界面传送的还是修改界面传送的
	private String pwd;      //密码
	private String email;    //邮箱
	private int admin;       //是否为管理员
	private int status;      //是否开启
	private long addTime;    //创建时间
	private long lastLogin;  //最近登录时间
	
	/*     get、set方法      */
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getAddTime() {
		return addTime;
	}
	public void setAddTime(long addTime) {
		this.addTime = addTime;
	}
	public long getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(long lastLogin) {
		this.lastLogin = lastLogin;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
