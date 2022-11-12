package MOBLIMA.Interface;

/**
 * interface implemented for add/update/remove methods for concrete classes
 */
public interface ConfigurationInt {
    /**
     * add method
     * @return int value
     */
    public abstract int add();

    /**
     * update method
     * @return int value
     */
    public abstract int update();

    /**
     * remove method
     * @return int value
     */
    public abstract int remove();
}
