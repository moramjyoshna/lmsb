package com.hcl.lms.service;

import com.hcl.lms.dto.LoginRequestDto;
import com.hcl.lms.dto.LoginResponseDto;

public interface LoginService {

	LoginResponseDto login(LoginRequestDto loginRequestDto);
}
