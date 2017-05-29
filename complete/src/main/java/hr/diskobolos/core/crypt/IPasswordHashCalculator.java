/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.core.crypt;

/**
 * @author Tomislav Čavka
 *
 * Interface for the the password hash calculator. It calculates the hash based
 * upon random chosen salt. It can also verify the password against a given hash
 * and salt
 *
 *
 */
public interface IPasswordHashCalculator {

    String hashPassword(String password_plaintext);

    boolean checkPassword(String password_plaintext, String stored_hash);

}
