package service.demo.store.shoppingservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

@ControllerAdvice
public class ResponseHttpExceptionHandler extends ResponseEntityExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    private final ExceptionHelper exceptionHelper;

    public ResponseHttpExceptionHandler(ExceptionHelper exceptionHelper) {
        this.exceptionHelper = exceptionHelper;
    }


    /**
     *  Obtiene todos los errores de entrada del usuario
     *  y los retorna en el header bajo la etiqueta: Error
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if(error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                String field = fieldError.getField();
                if(field.contains(".")) field = field.split("\\.")[1];
                StringBuilder builder = new StringBuilder();
                builder.append("variable: ");
                builder.append(field);
                builder.append(" ");
                builder.append(fieldError.getDefaultMessage());
                headers.add("ERROR", builder.toString());
            }
        }

        headers = exceptionHelper.customServiceCode(headers, 00);
        return new ResponseEntity<>(null,headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ServiceException.class})
    public final ResponseEntity<Object> handleServiceException(ServiceException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        headers.set("Error", ex.getMessage());
        headers = exceptionHelper.customServiceCode(headers, ex.getServiceCode(), ExceptionHelper.getFinalCause(ex));
        return new ResponseEntity<>(null,headers, ex.getStatus());
    }


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        response.setContentType("application/json");
        response.setHeader("timestamp", new Date().toString());
        response.setHeader("message", "Authentication Failed");
        response.setHeader("path", request.getRequestURL().toString());
        response.setHeader("pd", "Have a good day :)");

        OutputStream out = response.getOutputStream();
        out.flush();

    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        response.setHeader("timestamp", new Date().toString());
        response.setHeader("message", "Valueasdasd");
        response.setHeader("path", request.getRequestURL().toString());
        response.setHeader("pd", "Have a good day :)");

        OutputStream out = response.getOutputStream();
        out.flush();

    }
}
