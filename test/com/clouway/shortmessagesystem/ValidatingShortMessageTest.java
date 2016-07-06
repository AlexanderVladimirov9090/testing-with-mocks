package com.clouway.shortmessagesystem;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by clouway on 05.07.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
public class ValidatingShortMessageTest {

  @Test
  public void happyPath() {
    ShortMessageValidator shortMessageValidator = new ShortMessageValidator();
    ShortMessage shortMessage = new ShortMessage("This is title", "This is content", "0878121212");
    assertTrue(shortMessageValidator.checkIsValidMessage(shortMessage));
  }

  @Test
  public void validateShortMessageNoTitle() {
    ShortMessageValidator shortMessageValidator = new ShortMessageValidator();
    ShortMessage shortMessage = new ShortMessage("", "Some text", "0999121212");
    assertFalse(shortMessageValidator.checkIsValidMessage(shortMessage));
  }

  @Test
  public void validateShortMessageNoContent() {
    ShortMessageValidator shortMessageValidator = new ShortMessageValidator();
    ShortMessage shortMessage = new ShortMessage("SomeTitle", "", "0878333333");
    assertFalse(shortMessageValidator.checkIsValidMessage(shortMessage));
  }

  @Test
  public void validateShortMessageNoRecipient() {
    ShortMessageValidator shortMessageValidator = new ShortMessageValidator();
    ShortMessage shortMessage = new ShortMessage("Title", "Content", "");
    assertFalse(shortMessageValidator.checkIsValidMessage(shortMessage));
  }

  @Test
  public void validateShotMessageTitleExceeded() {
    ShortMessageValidator shortMessageValidator = new ShortMessageValidator();
    ShortMessage shortMessage = new ShortMessage("123456789012345678901234567890123456789012345678901234567890", "Content", "0812345678");
    assertFalse(shortMessageValidator.checkIsValidMessage(shortMessage));
  }

  @Test
  public void validateShotMessageContentExceeded() {
    ShortMessageValidator shortMessageValidator = new ShortMessageValidator();
    ShortMessage shortMessage = new ShortMessage("Title", "qwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwerqwer", "0833333333");
    assertFalse(shortMessageValidator.checkIsValidMessage(shortMessage));
  }

  @Test
  public void validateShortMessageRecipientNotValid() {
    ShortMessageValidator shortMessageValidator = new ShortMessageValidator();
    ShortMessage shortMessage = new ShortMessage("Title", "Hello John Sup", "00000000000");
    assertFalse(shortMessageValidator.checkIsValidMessage(shortMessage));
  }
}


