package com.clouway.endpointfilter;

/**
 * Created by clouway on 05.07.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
public class EndpointFilter {
  private final Endpoint[] endpoints;


  public EndpointFilter(Endpoint... endpoints) {
    this.endpoints = endpoints;
  }

  public boolean shouldFilter(String url) throws EmptyURLExceptions, EmptyKeywordException {

    return endpoints[0].matches(url);
  }
}
