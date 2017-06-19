package com.digrabok.crx.rainyhills.commons;

import com.digrabok.crx.rainyhills.commons.exceptions.CopyPropertiesException;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.InvocationTargetException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(BeanUtils.class)
public class UtilsTest {
    @Rule
    private ExpectedException expectedException = ExpectedException.none();

    @Before
    public void beforeEach() {
        PowerMockito.mockStatic(BeanUtils.class);
    }

    @Test
    public void shouldDelegateInvocationToBeanUtils() throws InvocationTargetException, IllegalAccessException {
        Utils.copyProperties(new Object(), new Object());

        verifyStatic(times(1));
        BeanUtils.copyProperties(any(), any());
    }

    @Test
    public void shouldWrapBeanUtilsIllegalAccessExceptionExceptionsToCustom() throws Exception {
        PowerMockito.doThrow(new IllegalAccessException()).when(BeanUtils.class, "copyProperties", any(), any());

        expectedException.expect(CopyPropertiesException.class);
        Utils.copyProperties(new Object(), new Object());
    }

    @Test
    public void shouldWrapBeanUtilsInvocationTargetExceptionExceptionsToCustom() throws Exception {
        PowerMockito.doThrow(new InvocationTargetException(null)).when(BeanUtils.class, "copyProperties", any(), any());

        expectedException.expect(CopyPropertiesException.class);
        Utils.copyProperties(new Object(), new Object());
    }
}
