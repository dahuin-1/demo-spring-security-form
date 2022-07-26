package me.whiteship.demospringsecurityform.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoggingFilter extends GenericFilterBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        StopWatch stopWatch = new StopWatch();
        
//      이렇게 하먼 uri에러는 task이름이된다.
        stopWatch.start(((HttpServletRequest)request).getRequestURI());
        
//      이걸해야 다음필터로 넘어간다 아니면 멈춘다.
        chain.doFilter(request, response);

        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
    }
}
