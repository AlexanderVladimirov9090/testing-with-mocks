package com.clouway.shortmessagesystem;

/**
 * Created by clouway on 06.07.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
class Getaway {
  private final Sender sender;
  private final SMSValidator validator;

  public Getaway(Sender sender, SMSValidator validator) {
    this.sender = sender;
    this.validator = validator;
  }

  public boolean send(ShortMessage shortMessage) throws GatewayNotReachableException {
    if (validator.checkIsValidMessage(shortMessage)) {

      sender.send(shortMessage);
      return true;
    }
    return false;
  }
}
