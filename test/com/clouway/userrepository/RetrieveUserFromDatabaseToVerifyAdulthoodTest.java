package com.clouway.userrepository;


import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by clouway on 06.07.16.
 *
 * @author Alexander Vladimirov
 *         (alexandervladimirov1902@gmail.com)
 */
public class RetrieveUserFromDatabaseToVerifyAdulthoodTest {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  @Mock
  private UserDataBase userDataBase;
  private UserValidator validator;


  @Test
  public void userIsAdultTrue() {
    User user = new User("AdultUser", "18");
    UserRepository userRepository = new UserRepository(userDataBase, validator);
    context.checking(new Expectations() {{
      oneOf(userDataBase).findByName("AdultUser");
      will(returnValue(user));
    }});
    assertTrue(userRepository.isUserAdult("AdultUser"));
  }

  @Test
  public void userExistsIsNotAdult() {
    User user = new User("Underage", "10");
    UserRepository userRepository = new UserRepository(userDataBase, validator);
    context.checking(new Expectations() {{
      oneOf(userDataBase).findByName("Underage");
      will(returnValue(user));
    }});
    assertFalse(userRepository.isUserAdult("Underage"));
  }

  @Test(expected = UserDoesNotExistException.class)
  public void userDoesNotExistInDataBase() {
    UserRepository userRepository = new UserRepository(userDataBase, validator);
    context.checking(new Expectations() {{
      oneOf(userDataBase).findByName("NotInBase");
      will(throwException(new UserDoesNotExistException()));
      never(userDataBase).findByName("NotInBase");
    }});
    userRepository.isUserAdult("NotInBase");
  }
}
