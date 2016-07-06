package com.clouway.userrepository;

import java.util.Optional;

/**
 * Created by clouway on 06.07.16.
 *
 * @author Alexander Vladimirov
 *         (alexandervladimirov1902@gmail.com)
 */
class UserRepository {

  private final UserDataBase dataBase;
  private final UserValidator validator;


  UserRepository(UserDataBase dataBase, UserValidator validator) {
    this.dataBase = dataBase;
    this.validator = validator;
  }

  /**
   * Registers user to Data Base.
   *
   * @param user that is give for registering.
   * @return true if registered and false if not.
   * @throws DataBaseUnreachableException when can't reach Data Base for some reason.
   * @throws UserExistsException          when user is in Data Base.
   */
  boolean registerUser(User user) {
    if (validator.validate(user)) {
      if (dataBase.exists(user.name)) {
        throw new UserExistsException();
      }
      dataBase.add(user);
      return true;
    }
    return false;
  }

  /**
   * Validates if user is adult.
   *
   * @param name is used to validate if is adult or not.
   * @return true if user is adult and false if not.
   * @throws UserDoesNotExistException when given name is not user in Data Base.
   */
  boolean isUserAdult(String name) {
    Optional<User> possibleUser = Optional.of(dataBase.findByName(name));
    if (!possibleUser.isPresent()) {
      throw new UserDoesNotExistException();
    }
    User user = possibleUser.get();
    return Integer.valueOf(user.age) >= 18;
  }
}