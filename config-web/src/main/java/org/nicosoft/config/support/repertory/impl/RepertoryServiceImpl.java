package org.nicosoft.config.support.repertory.impl;

//import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.nicosoft.config.support.exception.SysException;
import org.nicosoft.config.support.repertory.RepertoryService;
import org.nicosoft.config.support.utils.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Set;

/**
 * Git repertory service
 *
 * @author nico
 * @since 2017.8.28
 */
@Component
public class RepertoryServiceImpl implements RepertoryService {

    @Autowired
    Config config;

    @Override
    public void cloneRepo(File repoDir) throws SysException {

        try {

            CloneCommand command = Git.cloneRepository();
            command.setURI(config.getRepo("host"));
            command.setDirectory(repoDir);

            //String user = config.getRepo("username");
            //String passwd = config.getRepo("password");

            //if(StringUtils.isNotBlank(user) && StringUtils.isNotBlank(passwd)) {
                //UsernamePasswordCredentialsProvider upcp = new UsernamePasswordCredentialsProvider(user, passwd);
                //command.setCredentialsProvider(upcp);
            //}

            command.call();

        } catch (Exception e) {
            throw new SysException(e);
        }
    }

    @Override
    public void checkRepo(File repoDir) throws SysException {

        try {

            File gitDir = new File(repoDir.getAbsolutePath() + "/.git");

            if (!gitDir.exists()) {
                throw new SysException(gitDir.getAbsolutePath() + " not exists.");
            }

            Repository repository = new FileRepository(repoDir.getAbsolutePath());
            Git git = new Git(repository);
            Status status = git.status().call();
            Set<String> statusSet = status.getChanged();

            if (statusSet != null && statusSet.size() > 0) {
                git = Git.open(repoDir);
                PullCommand pull = git.pull();
                pull.call();
            }

        } catch (Exception e) {
            throw new SysException(e);
        }
    }

}
