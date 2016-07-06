package com.clouway.shortmessagesystem;

/**
 * Created by clouway on 06.07.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
class ShortMessageValidator implements SMSValidator  {

  @Override
  public boolean checkIsValidMessage(ShortMessage shortMessage) {
    if (isEmpty(shortMessage)||checkConsistencyOfRecipient(shortMessage)||titleAndContentExceeded(shortMessage)){
      return false;
    }
    return true;
  }

  private boolean titleAndContentExceeded(ShortMessage shortMessage) {
    if (shortMessage.title.length() > 50) {
      return true;
    }
    if (shortMessage.content.length() > 120) {
      return true;
    }
    return false;
  }

  private boolean checkConsistencyOfRecipient(ShortMessage shortMessage) {
    if (!shortMessage.recipient.startsWith("08")) {
      return true;
    }
    if (!shortMessage.recipient.matches("[0-9]+")) {
      return true;
    }
    if (shortMessage.recipient.length() < 10 || shortMessage.recipient.length() > 10) {
      return true;
    }
    return false;

  }

  private boolean isEmpty(ShortMessage shortMessage) {
    return shortMessage.title.isEmpty() || shortMessage.content.isEmpty() || shortMessage.recipient.isEmpty();
  }
}