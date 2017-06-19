package com.digrabok.crx.rainyhills.web.servlets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class ValidationFilterTest {
    @Test
    public void shouldForwardRequestToTheNextItemOfFIlteringChain() throws IOException, ServletException {
        ValidationFilter filter = new ValidationFilter();

        FilterChain chain = Mockito.mock(FilterChain.class);

        filter.init(null);
        filter.doFilter(
                Mockito.mock(HttpServletRequest.class),
                Mockito.mock(HttpServletResponse.class),
                chain
        );
        verify(chain).doFilter(any(), any());
        filter.destroy();
    }
}
