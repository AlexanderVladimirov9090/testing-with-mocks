package com.clouway.endpointfilter;

/**
 * Created by clouway on 05.07.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
class EndpointFilter {
  private final Endpoint[] endpoints;


  EndpointFilter(Endpoint... endpoints) {
    this.endpoints = endpoints;
  }

  boolean shouldFilter(String url) throws EmptyURLExceptions, EmptyKeywordException {
    for (Endpoint each : endpoints) {
      if (each.matches(url)) {
        return true;
      }
    }
    return false;
  }
}
