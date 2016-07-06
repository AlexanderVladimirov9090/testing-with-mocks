package com.clouway.shortmessagesystem;

/**
 * Created by clouway on 06.07.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
public interface SMSValidator {
  boolean checkIsValidMessage(ShortMessage shortMessage);
}
