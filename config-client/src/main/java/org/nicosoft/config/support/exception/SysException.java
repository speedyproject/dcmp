package org.nicosoft.config.support.exception;

/**
 * @author nico
 * @since 2017.8.28
 */
public class SysException extends RuntimeException {

    private static final long serialVersionUID = -1972925542520532318L;

    public SysException(String e) {
        super(e);
    }

    public SysException(Exception e) {
        super(e);
    }

}
