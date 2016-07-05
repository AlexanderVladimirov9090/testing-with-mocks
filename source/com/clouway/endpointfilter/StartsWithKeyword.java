package com.clouway.endpointfilter;

/**
 * Created by clouway on 05.07.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
class StartsWithKeyword implements Endpoint {
  private final String keyWord;

  public StartsWithKeyword(String keyWord) {
    this.keyWord = keyWord;
  }

  @Override
  public boolean matches(String url) throws EmptyURLExceptions {
    if (url.startsWith(keyWord)) {
      return true;
    }
    return false;
  }
}
