package com.rays.auth.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.auth.common.ORSResponse;
import com.rays.auth.dto.UserDTO;
import com.rays.auth.form.LoginForm;
import com.rays.auth.service.UserServiceInt;
import com.rays.auth.utility.JwtUtil;

@RestController
@RequestMapping(value = "Auth")
public class LoginCtl {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	public UserServiceInt service;

	@PostMapping("login")
	public ORSResponse login(@RequestBody LoginForm form) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = service.authenticate(form.getLoginId(), form.getPassword());

		if (dto != null) {
			String token = jwtUtil.generateToken(form.getLoginId());
			res.addData(dto);
			res.addResult("token", token);
		} else {
			res.addMessage("Login ID & Password is invalid..!!");
		}
		return res;
	}

}
