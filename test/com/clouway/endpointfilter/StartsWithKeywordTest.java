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
public class StartsWithKeywordTest {

  @Test
  public void happyPath() throws EmptyURLExceptions {
    StartsWithKeyword startsWithKeyword = new StartsWithKeyword("Start");

    EndpointFilter endpointFilter = new EndpointFilter(startsWithKeyword);
    assertTrue(endpointFilter.shouldFilter("StartString"));
  }

  @Test
  public void urlDoesNotStartsWithKeyWord() throws EmptyURLExceptions {
    StartsWithKeyword startsWithKeyword = new StartsWithKeyword("Start");

    EndpointFilter endpointFilter = new EndpointFilter(startsWithKeyword);
  assertFalse(endpointFilter.shouldFilter("String"));
  }


}
