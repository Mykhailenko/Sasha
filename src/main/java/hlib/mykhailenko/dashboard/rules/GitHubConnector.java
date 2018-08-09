package hlib.mykhailenko.dashboard.rules;

import hlib.mykhailenko.dashboard.model.EvaluatedRule;
import hlib.mykhailenko.dashboard.model.Rule;
import org.kohsuke.github.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static hlib.mykhailenko.dashboard.util.L.silent;

public class GitHubConnector {

    @Rule(id = "github.absense.old.prs",
            failedMessage = "There is at least one PR that is older than 2 weeks.",
            okMessage = "There are no old (2 weeks old) PRs at GitHub.")
    public EvaluatedRule twoWeekOldPRs() throws Exception {
        final GitHub gitHub = GitHub.connect("mykhailenko", " fe7c0764cb8ddca92ef6a5e8a1f762ea7529af65");
        List<GHRepository> repos = Files.lines(Paths.get(ClassLoader.getSystemResource("github/repositories.list").toURI()))
                .map(silent(gitHub::getRepository))
                .collect(Collectors.toList());
        List<GHPullRequest> oldRRs = new LinkedList<>();
        for (GHRepository repo : repos) {
            for (GHPullRequest pullRequest : repo.getPullRequests(GHIssueState.OPEN)) {
                if (hoursSinceCreation(pullRequest) > 2 * 7 * 24) {
                    oldRRs.add(pullRequest);
                }
            }
        }

        if (oldRRs.isEmpty()) {
            return EvaluatedRule.makeOk();
        } else {
            final String urls = oldRRs.stream()
                    .map(GHIssue::getHtmlUrl)
                    .map(Object::toString)
                    .map(s -> "<li>" + s + "</li>")
                    .collect(Collectors.joining("\n"));
            return EvaluatedRule.makeFailed("There is list of old PRs:\n" + urls);
        }
    }

    private long hoursSinceCreation(GHPullRequest pullRequest) throws IOException {
        return Duration.between(
                Instant.from(pullRequest.getCreatedAt().toInstant()),
                Instant.now())
                .toHours();
    }

}
