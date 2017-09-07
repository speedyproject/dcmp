package org.nicosoft.config.support.repertory;

import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.Status;
import org.nicosoft.config.support.exception.SysException;

import java.io.File;
import java.util.Set;

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
     * Get git repertory status.
     *
     * @param repoDir
     * @return
     * @throws SysException
     */
    PullResult pullRepo(File repoDir) throws SysException;

}
