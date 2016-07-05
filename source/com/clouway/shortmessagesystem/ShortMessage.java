package com.clouway.shortmessagesystem;

/**
 * Created by clouway on 06.07.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
class ShortMessage {
  private final String title;
  private final String content;
  private final String recipient;

  public ShortMessage(String title, String content, String recipient) {

    this.title = title;
    this.content = content;
    this.recipient = recipient;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public String getRecipient() {
    return recipient;
  }
}
