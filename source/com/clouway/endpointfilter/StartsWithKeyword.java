package com.clouway.endpointfilter;

/**
 * Created by clouway on 05.07.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
class StartsWithKeyword implements Endpoint {
  private final String keyWord;

  StartsWithKeyword(String keyWord) {
    this.keyWord = keyWord;
  }

  @Override
  public boolean matches(String url) throws EmptyURLExceptions, EmptyKeywordException {

    if (url.isEmpty()) {
      throw new EmptyURLExceptions();
    }

    if (keyWord.isEmpty()) {
      throw new EmptyKeywordException();
    }

    if (url.startsWith(keyWord)) {
      return true;
    }
    return false;
  }
}
