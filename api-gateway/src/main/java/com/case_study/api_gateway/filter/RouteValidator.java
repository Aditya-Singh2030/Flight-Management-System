package com.case_study.api_gateway.filter;

import java.util.List;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class RouteValidator {
	
	public static final List<String> openApiEndpoints = List.of(
            "/user/signUp",
            "/user/login"
    );

	public boolean isSecured(HttpServletRequest request) {
        return openApiEndpoints.stream()
                .noneMatch(uri -> request.getRequestURI().contains(uri));
    }
	
}
