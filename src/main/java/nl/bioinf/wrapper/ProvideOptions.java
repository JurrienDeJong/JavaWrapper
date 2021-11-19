package nl.bioinf.wrapper;

/**
 * Interface which provides getters which are used in other classes.
 * @author Jurrien de Jong
 * @version 1.0
 */

public interface ProvideOptions
{
    String getFilePath();
    int getAge();
    double getSerCreatinin();
    double getSerSodium();
    boolean getInputType();
}
