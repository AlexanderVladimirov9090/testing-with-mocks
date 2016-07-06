package com.clouway.userrepository;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static java.lang.Integer.parseInt;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by clouway on 06.07.16.
 *
 * @author Alexander Vladimirov
 *        (alexandervladimirov1902@gmail.com)
 */
public class RegistersUserTest {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private UserDataBase dataBase;
  @Mock
  private UserValidator validator;

  @Test
  public void registerUserSucceed(){
    User user = new User("Ivan", "38");
    UserRepository userRepository = new UserRepository(dataBase, validator);
    context.checking(new Expectations() {{
      oneOf(validator).validate(user);
      will(returnValue(true));
      oneOf(dataBase).exists("Ivan");
      will(returnValue(false));
      oneOf(dataBase).add(user);
      will(returnValue(true));
    }});

     assertTrue(userRepository.registerUser(user));
  }

  @Test(expected = UserExistsException.class)
  public void registerExistingUserFailed(){
    User user = new User("RegisteredUser", "13");
    UserRepository userRepository = new UserRepository(dataBase, validator);
    context.checking(new Expectations() {{
      oneOf(validator).validate(user);
      will(returnValue(true));
      oneOf(dataBase).exists("RegisteredUser");
      will(returnValue(true));
      never(dataBase).add(user);
    }});

    userRepository.registerUser(user);
  }

  @Test
  public void emptyUserFailedToRegister() {
    User user = new User("", "");
    UserRepository userRepository = new UserRepository(dataBase, validator);
    context.checking(new Expectations() {{
      oneOf(validator).validate(user);
      will(returnValue(false));
      never(dataBase).exists("");
    }});

    assertFalse(userRepository.registerUser(user));
  }

  @Test
  public void underageUserToFailedToRegister(){
    User user = new User("UnderageUser", "3");
    UserRepository userRepository = new UserRepository(dataBase, validator);
    context.checking(new Expectations() {{
      oneOf(validator).validate(user);
      will(returnValue(false));
      never(dataBase).exists("UnderageUser");
    }});
    assertFalse(userRepository.registerUser(user));
  }

  @Test
  public void userOverageToFailedRegister(){
    User user = new User("OldBag", "133");
    UserRepository userRepository = new UserRepository(dataBase, validator);
    context.checking(new Expectations() {{
      oneOf(validator).validate(user);
      will(returnValue(false));
      never(dataBase).exists("OldBag");
    }});

    assertFalse(userRepository.registerUser(user));
  }

  @Test(expected = DataBaseUnreachableException.class)
  public void  dataBaseIsUnreachable(){
    User user = new User("NoDataBase", "55");
    UserRepository userRepository = new UserRepository(dataBase, validator);
    context.checking(new Expectations() {{
      oneOf(validator).validate(user);
      will(returnValue(true));
      oneOf(dataBase).exists("NoDataBase");
      will(returnValue(false));
      oneOf(dataBase).add(user);
      will(throwException(new DataBaseUnreachableException()));
    }});
    userRepository.registerUser(user);
  }

  @Test
  public void registerNegativeAgeUserFailed(){
    User user = new User("TheNegativeDude", "-87");
    UserRepository userRepository = new UserRepository(dataBase, validator);
    context.checking(new Expectations() {{
      oneOf(validator).validate(user);
      will(returnValue(false));
      never(dataBase).exists("TheNegativeDude");
    }});
    assertFalse(userRepository.registerUser(user));
  }
}