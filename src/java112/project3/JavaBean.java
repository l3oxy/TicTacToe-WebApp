package java112.project3;

/**
 * This is a JavaBean to
 */
public class JavaBean {

    // We need at least 3 attributes
    private String data;

    /**
     * Constructor for the JavaBean object
     */
    public JavaBean() {
        data = "default value";
    }

    /**
     * Gets the __ attribute of the JavaBean object
     * @return The new data value
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the __ attribute of the JavaBean object
     * @param  data  The new Data value
     *
     */
    public void setData(String data) {
        this.data = data;
    }
}