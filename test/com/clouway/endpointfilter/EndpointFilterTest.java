package com.clouway.endpointfilter;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by clouway on 04.07.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
public class EndpointFilterTest {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();


  @Test
  public void happyPath() throws EmptyURLExceptions, EmptyKeywordException {
    Endpoint endpoint = context.mock(Endpoint.class);
    context.checking(new Expectations() {{
      oneOf(endpoint).matches("someUrlThatWillPass");
      will(returnValue(true));
    }});

    EndpointFilter endpointFilter = new EndpointFilter(endpoint);
    assertTrue(endpointFilter.shouldFilter("someUrlThatWillPass"));
  }

  @Test(expected = EmptyURLExceptions.class)
  public void urlIsEmpty() throws EmptyURLExceptions, EmptyKeywordException {
    Endpoint endpoint = context.mock(Endpoint.class);

    context.checking(new Expectations() {
      {
        oneOf(endpoint).matches("");
        will(throwException(new EmptyURLExceptions()));
      }
    });
    EndpointFilter endpointFilter = new EndpointFilter(endpoint);
    endpointFilter.shouldFilter("");
  }

  @Test
  public void urlDoesNotMatch() throws EmptyURLExceptions, EmptyKeywordException {
    Endpoint endpoint = context.mock(Endpoint.class);

    context.checking(new Expectations() {{
      oneOf(endpoint).matches("ThisUrlDoesNotMatch");
      will(returnValue(false));
    }});

    EndpointFilter endpointFilter = new EndpointFilter(endpoint);
    assertFalse(endpointFilter.shouldFilter("ThisUrlDoesNotMatch"));
  }
}
