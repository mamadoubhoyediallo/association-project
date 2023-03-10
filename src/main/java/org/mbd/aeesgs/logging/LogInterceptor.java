package org.mbd.aeesgs.logging;

import lombok.AllArgsConstructor;
import org.mbd.aeesgs.services.ILoggingService;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
public class LogInterceptor implements HandlerInterceptor {

    private ILoggingService ILoggingService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
                && request.getMethod().equals(HttpMethod.GET.name())) {
            ILoggingService.logRequest(request, null);
        }

        return true;
    }
}
