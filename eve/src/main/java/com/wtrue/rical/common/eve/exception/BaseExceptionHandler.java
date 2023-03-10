package com.wtrue.rical.common.eve.exception;

import com.wtrue.rical.common.adam.domain.BaseResponse;
import com.wtrue.rical.common.adam.domain.BaseException;
import com.wtrue.rical.common.adam.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description:
 * @author: meidanlong
 * @date: 2022/9/5 11:58
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    /**
     * 拦截业务类异常
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public BaseResponse businessExceptionHandle(BaseException e) {
        log.error("捕捉到业务类异常：", e);
        return BaseResponse.failure(e);
    }


    /**
     * 拦截运行时异常
     * @param e
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionHandle(RuntimeException e) {
        log.error("捕捉到运行时异常：", e);
        return BaseResponse.failure(
                ErrorEnum.UNKNOWN_ERROR.getCode(),
                e.getMessage());
    }

    /**
     * 捕捉系统级异常
     * @param th
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public BaseResponse throwableHandle(Throwable th) {
        log.error("捕捉Throwable异常：", th);
        return BaseResponse.failure(
                ErrorEnum.SYSTEM_ERROR.getCode(),
                th.getMessage());
    }
}
