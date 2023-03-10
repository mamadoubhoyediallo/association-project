package org.mbd.aeesgs.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ILoggingService {
    void logRequest(HttpServletRequest httpServletRequest, Object body);
    void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body);
}
