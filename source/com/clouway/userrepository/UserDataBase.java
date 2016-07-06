package com.clouway.userrepository;

import java.util.Optional;

/**
 * Created by clouway on 06.07.16.
 *
 * @author Alexander Vladimirov
 *         (alexandervladimirov1902@gmail.com)
 *         <p>
 *         This interface provides some basic method, for specified type of Data Base used for storing users.
 */
interface UserDataBase {

  /**
   * Adds user in to Data Base.
   *
   * @param user that is going to be added in the Data Base.
   * @return true if added and false if not.
   * @throws DataBaseUnreachableException when Data Base is not reachable for some reason.
   */
  boolean add(User user) throws DataBaseUnreachableException;

  /**
   * Search the Data Base for user by name.
   *
   * @param name that is used to search for user.
   * @return true if user found and false if not.
   */
  boolean exists(String name);

  /**
   * Retrieves user from Data Base.
   *
   * @param name using name to search user.
   * @return user from Data Base.
   */
  User findByName(String name);
}