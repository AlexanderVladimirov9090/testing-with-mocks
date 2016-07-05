package com.clouway.endpointfilter;

/**
 * Created by clouway on 05.07.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
public interface Endpoint {
  boolean matches(String url) throws EmptyURLExceptions, EmptyKeywordException;
}
