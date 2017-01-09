package dao.model;

public class DAOException extends Exception {

    /**
     * Creates a new instance of <code>EmpresaDAOException</code> without detail
     * message.
     */
    public DAOException() {
    }

    public DAOException(String e) {
        super(e);
    }

    public DAOException(Throwable e) {
        super(e);
    }

    public DAOException(String e, Throwable c) {
        super(e, c);
    }

    /**
     * Constructs an instance of <code>EmpresaDAOException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
}
