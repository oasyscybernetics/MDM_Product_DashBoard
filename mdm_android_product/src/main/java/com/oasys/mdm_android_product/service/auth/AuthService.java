package com.oasys.mdm_android_product.service.auth;

import com.oasys.mdm_android_product.response.RegisterDTO;
import com.oasys.mdm_android_product.response.UserDTO;

public interface AuthService {

	UserDTO createUser(RegisterDTO registerDTO);

}

