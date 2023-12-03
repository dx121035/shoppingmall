package com.example.shoppingmall.auth;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = {UnauthorizedAccessException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleUnauthorizedAccess(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error/error";

}
}
