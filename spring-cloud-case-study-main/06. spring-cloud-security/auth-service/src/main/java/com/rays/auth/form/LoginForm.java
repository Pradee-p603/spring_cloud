package com.rays.auth.form;

import com.rays.auth.common.BaseForm;

public class LoginForm extends BaseForm {

	private String loginId;

	private String password;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
