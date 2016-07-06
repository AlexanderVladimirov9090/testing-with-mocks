package com.clouway.shortmessagesystem;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by clouway on 06.07.16.
 *
 * @author alexandervladimirov1902@gmail.com
 *         (Alexander Vladimirov)
 */
public class GatewaySendsShortMessageTest {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  SMSValidator smsValidator = context.mock(SMSValidator.class);

  Sender sender = context.mock(Sender.class);
  Getaway getaway = new Getaway(sender, smsValidator);

  @Test
  public void validShortMessageAndSend() throws GatewayNotReachableException {
    ShortMessage shortMessage = new ShortMessage("Title", "Content", "0897000000");
    context.checking(new Expectations() {{
      oneOf(smsValidator).checkIsValidMessage(shortMessage);
      will(returnValue(true));
      oneOf(sender).send(shortMessage);
      will(returnValue(true));
    }});

    assertTrue(getaway.send(shortMessage));

  }

  @Test
  public void invalidShortMessageNotSend() throws GatewayNotReachableException {
    ShortMessage shortMessage = new ShortMessage("Title", "NotValidContent", "0889121212");
    context.checking(new Expectations() {{
      oneOf(smsValidator).checkIsValidMessage(shortMessage);
      will(returnValue(false));
    }});
    assertFalse(getaway.send(shortMessage));
  }

  @Test(expected = GatewayNotReachableException.class)
  public void unableToDeliverShortMessage() throws GatewayNotReachableException {
    ShortMessage shortMessage = new ShortMessage("Hello", "Some Contetn", "012321314");
    context.checking(new Expectations() {{
      oneOf(smsValidator).checkIsValidMessage(shortMessage);
      will(returnValue(true));
      oneOf(sender).send(shortMessage);
      will(throwException(new GatewayNotReachableException()));
    }});

    getaway.send(shortMessage);
  }
}