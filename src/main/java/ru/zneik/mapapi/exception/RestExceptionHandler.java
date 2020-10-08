package ru.zneik.mapapi.exception;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.zneik.mapapi.to.ErrorInfo;
import ru.zneik.mapapi.util.ValidationUtil;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorInfo handleNotFound(HttpServletRequest request, Exception exception) {
        return new ErrorInfo(HttpStatus.NOT_FOUND.value(), "Not found");
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorInfo handleDateIntegrityViolation(HttpServletRequest request, Exception exception) {
        Throwable rootCause = ValidationUtil.getRootCause(exception);
        return new ErrorInfo(HttpStatus.UNPROCESSABLE_ENTITY.value(), rootCause.getMessage());
    }

    @ExceptionHandler(value = {AuthenticationException.class, BadCredentionalException.class})
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ErrorInfo handleAuthentication(HttpServletRequest request, Exception exception) {
        return new ErrorInfo(HttpStatus.UNAUTHORIZED.value(), "unauthorized");
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorInfo handleOther(HttpServletRequest request, Exception exception) {
        Throwable rootCause = ValidationUtil.getRootCause(exception);
        return new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), rootCause.getMessage());
    }
}
