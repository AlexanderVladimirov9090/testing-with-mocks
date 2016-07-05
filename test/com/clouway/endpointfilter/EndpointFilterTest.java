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
    final Sequence matchEndpoints = context.sequence("MatchEndpoints");
    Endpoint endpoint = context.mock(Endpoint.class);
    Endpoint endpoint1 = context.mock(Endpoint.class, "endPoint1");
    Endpoint endpoint2 = context.mock(Endpoint.class, "endPoint2");
    EndpointFilter endpointFilter = new EndpointFilter(endpoint, endpoint1, endpoint2);

    context.checking(new Expectations() {{
      oneOf(endpoint).matches("Url");
      inSequence(matchEndpoints);
      will(returnValue(false));
      oneOf(endpoint1).matches("Url");
      inSequence(matchEndpoints);
      will(returnValue(false));
      oneOf(endpoint2).matches("Url");
      inSequence(matchEndpoints);
      will(returnValue(true));
    }});

    endpointFilter.shouldFilter("Url");
  }

}
