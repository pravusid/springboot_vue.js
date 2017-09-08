package com.talsist.util;

import com.talsist.domain.User;
import com.talsist.exception.NotLoggedInException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpSessionUtils {

    private static boolean isLogin(HttpSession session) {
        Object user = session.getAttribute("user");
        return user != null;
    }

    public static void loginCheck(HttpSession session) throws NotLoggedInException {
        if (!HttpSessionUtils.isLogin(session)) {
            throw new NotLoggedInException();
        }
    }

    public static User getSessionUser(HttpSession session) throws NotLoggedInException {
        if (isLogin(session)) {
            return (User) session.getAttribute("user");
        }
        throw new NotLoggedInException();
    }

    public static String redirctToLoginPage(HttpServletRequest request, HttpSession session) {
        String query = (request.getQueryString() != null) ? "?" + request.getQueryString() : "";
        session.setAttribute("prevPage", request.getRequestURI() + query);
        return "redirect:/login";
    }

}
