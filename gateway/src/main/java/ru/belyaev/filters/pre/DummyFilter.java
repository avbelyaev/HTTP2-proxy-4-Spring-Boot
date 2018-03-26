package ru.belyaev.filters.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author anton.belyaev@bostongene.com
 */
public class DummyFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(DummyFilter.class);

    /**
     * pre - filters are executed before the request is routed,
     * route - filters can handle the actual routing of the request,
     * post - filters are executed after the request has been routed, and
     * error - filters execute if an error occurs in the course of handling the request.
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * order in which this filter will be executed, relative to other filters
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * contains the logic that determines when to execute this filter (this particular filter will always be executed)
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        return null;
    }
}
