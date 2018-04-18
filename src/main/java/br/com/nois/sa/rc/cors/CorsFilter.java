package br.com.nois.sa.rc.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Configuration
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Authorization, Content-Type, Accept, X-CSRF-TOKEN");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		response.setHeader("Access-Control-Request-Method", "POST, GET, OPTIONS, DELETE, PUT, PATCH");

		response.setHeader("Accept-Encoding", "gzip, deflate, br");
		response.setHeader("Accept-Language", "en-US,en;q=0.9,pt;q=0.8");
		response.setHeader("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
		response.setHeader("Accept", "/");
		response.setHeader("Connection", "keep-alive");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("x-frame-options", "ALLOW");

		if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void destroy() {
	}
}