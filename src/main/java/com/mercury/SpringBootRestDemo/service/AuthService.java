//package com.mercury.SpringBootRestDemo.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//
////import com.mercury.SpringBootRestDemo.dao.UserDao;
//import com.mercury.SpringBootRestDemo.http.AuthenticationSuccessResponse;
//import com.mercury.SpringBootRestDemo.http.Response;
//
//@Service
//public class AuthService {
//
//	@Autowired
//	private DelUserDao delUserDao;
//
//	public Response checklogin(Authentication authentication) {
//		if (authentication != null) {
//			Response response = new AuthenticationSuccessResponse(true, 200, "Logged In!",
//					delUserDao.findByUsername(authentication.getName()));
//			return response;
//		} else {
//			return new Response(false);
//		}
//	}
//
//}
