package com.stock.gateway.zuul.api.filter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PostFilter extends ZuulFilter {
	private static org.slf4j.Logger log = LoggerFactory.getLogger(PostFilter.class);
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
		log.info("PostFilter:"+String.format("response's content type is %s", response.getStatus()));
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
