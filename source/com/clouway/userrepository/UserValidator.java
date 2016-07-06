package com.clouway.userrepository;

/**
 * Created by clouway on 06.07.16.
 *
 * @author Alexander Vladimirov
 *         (alexandervladimirov1902@gmail.com)
 *         This user validator interface is for basic validation of user before added to Data Base.
 */
interface UserValidator {

  /**
   * Checks if age and name  is valid.
   *
   * @param user that is checked.
   * @return true if name and age are valid and false if not.
   */
  boolean validate(User user);
}