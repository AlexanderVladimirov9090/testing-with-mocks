package com.clouway.endpointfilter;

import org.jmock.Expectations;
import org.jmock.Sequence;
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
  public void endPointIsMatching() throws EmptyURLExceptions, EmptyKeywordException {
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
  public void endPointIsNotMatching() throws EmptyURLExceptions, EmptyKeywordException {
    Endpoint endpoint = context.mock(Endpoint.class);

    context.checking(new Expectations() {{
      oneOf(endpoint).matches("ThisUrlDoesNotMatch");
      will(returnValue(false));
    }});

    EndpointFilter endpointFilter = new EndpointFilter(endpoint);
    assertFalse(endpointFilter.shouldFilter("ThisUrlDoesNotMatch"));
  }

  @Test
  public void endPointMany() throws EmptyKeywordException, EmptyURLExceptions {
    final Sequence match = context.sequence("sequence-name");
    Endpoint endpoint = context.mock(Endpoint.class);
    context.checking(new Expectations(){{
      oneOf(endpoint).matches("ThisWillNotMatch");
      inSequence(match);
      will(returnValue(false));
      oneOf(endpoint).matches("ThisWillNotMatchAgain");
      inSequence(match);
      will(returnValue(false));
      oneOf(endpoint).matches("ThisWillMatch");
      inSequence(match);
      will(returnValue(true));
    }});

    EndpointFilter endpointFilter = new EndpointFilter(endpoint);

    endpointFilter.shouldFilter("ThisWillNotMatch");
    endpointFilter.shouldFilter("ThisWillNotMatchAgain");
    endpointFilter.shouldFilter("ThisWillMatch");

  }
}
