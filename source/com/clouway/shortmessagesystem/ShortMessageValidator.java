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
    if (isEmpty(shortMessage)) {
      return false;
    }
    if (checkConsistencyOfRecipient(shortMessage)) {
      return false;
    }
    if (titleAndContentExceeded(shortMessage)) {
      return false;
    }
    return true;
  }

  private boolean titleAndContentExceeded(ShortMessage shortMessage) {
    if (shortMessage.getTitle().length() > 50) {
      return true;
    }
    if (shortMessage.getContent().length() > 120) {
      return true;
    }
    return false;
  }

  private boolean checkConsistencyOfRecipient(ShortMessage shortMessage) {
    if (!shortMessage.getRecipient().startsWith("08")) {
      return true;
    }
    if (!shortMessage.getRecipient().matches("[0-9]+")) {
      return true;
    }
    if (shortMessage.getRecipient().length() < 10 || shortMessage.getRecipient().length() > 10) {
      return true;
    }
    return false;

  }



  private boolean isEmpty(ShortMessage shortMessage) {
    if (shortMessage.getTitle().isEmpty() || shortMessage.getContent().isEmpty() || shortMessage.getRecipient().isEmpty()) {
      return true;
    }
    return false;
  }
}
