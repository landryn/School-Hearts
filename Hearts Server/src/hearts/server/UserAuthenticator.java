/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hearts.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orbit
 */
public class UserAuthenticator {
    private Properties userList = null;
    private String fileName = "userList";

    public UserAuthenticator() {
        loadProperties();
    }

    /**
     * Metoda ładująca userlistę z pliku.
     */
    private void loadProperties() {
        userList = new Properties();
        try {
            FileInputStream in = new FileInputStream(fileName);
            userList.load(in);
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserAuthenticator.class.getName()).log(Level.INFO, "Nie znaleziono pliku z użytkownikami.");
            saveProperties();
            loadProperties();
        } catch (IOException ex) {
            Logger.getLogger(UserAuthenticator.class.getName()).log(Level.SEVERE, "Błąd ładownia listy użytkowników.", ex);
        }
    }

    /**
     * Metoda zapisująca userlistę do pliku.
     */
    private void saveProperties() {
        try {
            FileOutputStream out = new FileOutputStream(fileName);
            if (userList != null) {
                userList.store(out, "---------------");
            }
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(UserAuthenticator.class.getName()).log(Level.SEVERE, "Błąd zapisywania listy użytkowników.", ex);
        }
    }

    /**
     * Metoda dodająca użytkownika do listy a następnie zapisująca listę i ładująca ją ponownie.
     * @param username nazwa użytkownika do dodania
     * @param password hasło użytkoniwka
     */
    public void addUser(String username, String password) {
        userList.setProperty(username, password);
        saveProperties();
        loadProperties();
    }

    /**
     * Sprawdza poprawność użytkownika i jego hasła.
     *
     * @param username nazwa użytkownika do sprawdzenia
     * @param password hasło użytkownika do sprawdzenia
     * @return false jeśli użytkownika nie ma w bazie lub jego hasło jest nieprawidłowe, true jeśli wszystko jest ok.
     */
    public boolean checkUser(String username, String password) {
        if(!userList.containsKey(username)) {
            return false;
        }

        String dbPass = userList.getProperty(username);
        if(dbPass != null && dbPass.equals(password)) {
            return true;
        }

        return false;
    }

    /**
     * Usuwa użytkownika z listy następnie ją zapisując.
     * @param username nazwa użytkownika do usunięcia.
     */
    public void delUser(String username) {
        userList.remove(username);
        saveProperties();
        loadProperties();
    }

    /**
     * Czyści listę użytkowników.
     */
    public void clear() {
        userList.clear();
        saveProperties();
        loadProperties();
    }


}
