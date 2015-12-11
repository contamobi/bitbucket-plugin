package com.cloudbees.jenkins.plugins;

import hudson.Extension;
import hudson.security.csrf.CrumbExclusion;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Extension
public class BitbucketCrumbExclusion extends CrumbExclusion {
    private static final String EXCLUSION_PATH = "/" + BitbucketHookReceiver.BITBUCKET_HOOK_URL;

    @Override
    public boolean process(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && (pathInfo.equals(EXCLUSION_PATH) || pathInfo.equals(EXCLUSION_PATH + "/"))) {
            chain.doFilter(req, resp);
            return true;
        }
        return false;
    }
}
