package com.project.Springframework;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("Start session checking");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        if(email!=null && !email.isBlank() && password!=null && !password.isBlank()) {
        	return true; 
        }
        

        HttpSession session = request.getSession(false); // Use false to avoid creating a new session
        
        if (session == null || session.getAttribute("users") == null) {
            System.out.println("Session or user attribute not found!");
            
            // Redirect to login page
            response.sendRedirect(request.getContextPath() + "/loginForm");
            System.out.println("End session checking");
            return false; // Prevent further execution
        }

        // Debugging: Log session details
        System.out.println("Session exists. User: " + session.getAttribute("users"));
        System.out.println("End session checking");

        return true; // Allow request to proceed
    }

    // Uncomment and implement these methods if postHandle and afterCompletion logic is required
    /*
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Post-handle logic
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // After completion logic
    }
    */
}
