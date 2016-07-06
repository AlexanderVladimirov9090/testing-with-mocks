package com.clouway.shortmessagesystem;

/**
 * Created by clouway on 06.07.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
class ShortMessage {
  final String title;
  final String content;
  final String recipient;

  ShortMessage(String title, String content, String recipient) {

    this.title = title;
    this.content = content;
    this.recipient = recipient;
  }
}