package org.nicosoft.config.support.repertory;

import org.nicosoft.config.support.exception.SysException;

import java.io.File;

/**
 * Git repertory service
 *
 * @author nico
 * @since 2017.8.28
 */
public interface RepertoryService {


    /**
     * Clone git repertory.
     *
     * @param repoDir
     * @throws SysException
     */
    void cloneRepo(File repoDir) throws SysException;


    /**
     * Check git repertory status.
     *
     * @param repoDir
     * @throws SysException
     */
    void checkRepo(File repoDir) throws SysException;

}
