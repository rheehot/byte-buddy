package net.bytebuddy.dynamic;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.test.utility.MockitoRule;
import net.bytebuddy.test.utility.ObjectPropertyAssertion;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class MethodTransformerCompoundTest {

    @Rule
    public TestRule mockitoRule = new MockitoRule(this);

    @Mock
    private MethodTransformer first, second;

    @Mock
    private MethodDescription firstMethod, secondMethod, finalMethod;

    @Before
    public void setUp() throws Exception {
        when(first.transform(firstMethod)).thenReturn(secondMethod);
        when(second.transform(secondMethod)).thenReturn(finalMethod);
    }

    @Test
    public void testTransformation() throws Exception {
        assertThat(new MethodTransformer.Compound(first, second).transform(firstMethod), is(finalMethod));
    }

    @Test
    public void testObjectProperties() throws Exception {
        ObjectPropertyAssertion.of(MethodTransformer.Compound.class).apply();
    }
}
