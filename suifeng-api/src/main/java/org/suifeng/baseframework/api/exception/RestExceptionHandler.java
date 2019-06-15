package org.suifeng.baseframework.api.exception;

import org.suifeng.baseframework.api.common.domain.ErrorType;
import org.suifeng.baseframework.api.common.domain.ResMessage;
import org.suifeng.baseframework.api.common.domain.ResultCode;
import org.suifeng.baseframework.api.common.domain.ValidationErrorDTO;
import org.suifeng.baseframework.api.common.exception.RestException;
import org.suifeng.baseframework.api.constant.MediaTypes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Restful接口异常统一处理
 * @createTime 2019/6/1 8:00
 * @author luoxc
 */
@Slf4j
@ControllerAdvice
@ConditionalOnProperty(prefix = "api.exception", name = "type", havingValue = "rest")
public class RestExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ResMessage> processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ValidationErrorDTO errorDTO = processFieldErrors(fieldErrors);
        ResMessage resMessage = new ResMessage<>(ErrorType.PARAM_ERROR, errorDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
        log.info("RestException:{}",resMessage);
        return new ResponseEntity<>(resMessage,headers,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    public final ResponseEntity<?> handleException(HttpMessageNotReadableException ex, WebRequest request) {
        //Map<String, String> errors = BeanValidators.extractPropertyAndMessage(ex.getConstraintViolations());
        String body = "请求参数错误";
        log.warn(body,ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
        ResMessage resMessage = new ResMessage<>(ErrorType.PARAM_ERROR);
        log.info("RestException:{}",resMessage);
        return new ResponseEntity<>(resMessage,headers,HttpStatus.BAD_REQUEST);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
//        return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { HttpMessageNotWritableException.class })
    public final ResponseEntity<?> handleException(HttpMessageNotWritableException ex, WebRequest request) {
        //Map<String, String> errors = BeanValidators.extractPropertyAndMessage(ex.getConstraintViolations());
        String body = "返回结果错误";
        log.warn(body,ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
        ResMessage resMessage = new ResMessage<>(ErrorType.PARAM_ERROR);
        log.info("RestException:{}",resMessage);
        return new ResponseEntity<>(resMessage,headers,HttpStatus.BAD_REQUEST);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
//        return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
    public final ResponseEntity<?> handleException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        //Map<String, String> errors = BeanValidators.extractPropertyAndMessage(ex.getConstraintViolations());
        String body = "接口不支持方法："+request.getMethod();
        log.warn(body,ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
        ResMessage resMessage = new ResMessage<>(ErrorType.PARAM_ERROR);
        log.info("RestException:{}",resMessage);
        return new ResponseEntity<>(resMessage,headers,HttpStatus.BAD_REQUEST);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
//        return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
    }


    /**
     * 处理RestException.
     */
    @ExceptionHandler(value = { RestException.class })
    public final ResponseEntity<?> handleException(RestException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
        // TODO 继续优化
        ResultCode re = new ResultCode(ex.getCode(), ex.getMessage());
        return handleExceptionInternal(ex, re, headers, ex.status, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", ex, WebRequest.SCOPE_REQUEST);
        }
        headers.add("ex", body.toString());
        log.info("RestException:{}",body);
        return new ResponseEntity<Object>(body, headers, status);
    }

	@ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ResMessage> processOtherException(Exception ex) {
        ResMessage resMessage = new ResMessage<>(ErrorType.SERVER_ERROR);
        log.error(ErrorType.SERVER_ERROR.getName(),ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaTypes.TEXT_PLAIN_UTF_8));
        return new ResponseEntity<>(resMessage,headers,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDTO dto = new ValidationErrorDTO();

        for (FieldError fieldError: fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }

        return dto;
    }

	private String resolveLocalizedErrorMessage(FieldError fieldError) {
		return fieldError.getDefaultMessage();
	}


}
