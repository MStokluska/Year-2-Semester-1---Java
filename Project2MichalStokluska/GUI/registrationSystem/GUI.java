package registrationSystem;

// Michael Stokluska IT2 20079174
/* First user has to create a software user in order to use software, user can specify his name, surname, club name, club type (1 of 3)
 * and 4 different random attributes that he/she would like his/hers users to have. Name, surname and age are build in attributes and can not be changed
 * Once created user can now log in and background of programm, buttons in main menu, change based on the type of the club
 * User can now add, remove, find and edit, view age statistics, or view all users registered on his system.
 * There's some optization missing, ceratin codes could be written in a shorter, more efficient way.
 * There's also try catch blocks in use to manage exception and lists files are saved relatively along with background pictures. 
 */


public class GUI	
{
    public static void main(String [] args)
    {
        new LogInWindow();
    }
}