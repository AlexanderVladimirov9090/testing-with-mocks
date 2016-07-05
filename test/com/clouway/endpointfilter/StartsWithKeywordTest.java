package com.clouway.endpointfilter;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


/**
 * Created by clouway on 05.07.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
public class StartsWithKeywordTest extends EndpointFilterTest {

  @Test
  public void happyPath() throws EmptyURLExceptions, EmptyKeywordException {
    StartsWithKeyword startsWithKeyword = new StartsWithKeyword("Start");

    EndpointFilter endpointFilter = new EndpointFilter(startsWithKeyword);
    assertTrue(endpointFilter.shouldFilter("StartString"));
  }

  @Test(expected = EmptyURLExceptions.class)
  public void emptyUrl() throws EmptyURLExceptions, EmptyKeywordException {
    StartsWithKeyword startsWithKeyword = new StartsWithKeyword("Start");

    EndpointFilter endpointFilter = new EndpointFilter(startsWithKeyword);
    endpointFilter.shouldFilter("");

  }

  @Test
  public void urlDoesNotStartsWithKeyWord() throws EmptyURLExceptions, EmptyKeywordException {
    StartsWithKeyword startsWithKeyword = new StartsWithKeyword("StartKeyWord");

    EndpointFilter endpointFilter = new EndpointFilter(startsWithKeyword);
    assertFalse(endpointFilter.shouldFilter("StringNoKeyWord"));
  }

  @Test(expected = EmptyKeywordException.class)
  public void emptyStartKeyWord() throws EmptyURLExceptions, EmptyKeywordException {
    StartsWithKeyword startsWithKeyword = new StartsWithKeyword("");

    EndpointFilter endpointFilter = new EndpointFilter(startsWithKeyword);
    endpointFilter.shouldFilter("SomeUrl");
  }
}
