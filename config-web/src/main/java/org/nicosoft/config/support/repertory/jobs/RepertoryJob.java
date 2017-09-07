package org.nicosoft.config.support.repertory.jobs;

import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.transport.TrackingRefUpdate;
import org.nicosoft.config.support.repertory.RepertoryService;
import org.nicosoft.config.support.utils.Config;
import org.nicosoft.config.support.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Repertory job
 *
 * @author nico
 */
@Component
public class RepertoryJob {

    @Autowired
    Config config;
    @Autowired
    RepertoryService repertoryService;

    Resource buildRepoDir() {
        String pathName = config.getRepo("repoDir");
        return new FileSystemResource(pathName);
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void doJob() throws Exception {
        Resource repoDir = buildRepoDir();
        PullResult result = repertoryService.pullRepo(repoDir.getFile());
        Collection<TrackingRefUpdate> updates = result.getFetchResult().getTrackingRefUpdates();

        Logger.info("doJob::result repertory updates -> %s", updates);
    }

}
