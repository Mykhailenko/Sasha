package hlib.mykhailenko.dashboard.rules;

import hlib.mykhailenko.dashboard.model.EvaluatedRule;
import hlib.mykhailenko.dashboard.model.Rule;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static hlib.mykhailenko.dashboard.util.L.silent;

public class GitHubConnector {

    private List<GHRepository> repos = new LinkedList<>();

    public GitHubConnector() throws URISyntaxException, IOException {
        GitHub github = GitHub.connectAnonymously();

        repos = Files.lines(Paths.get(ClassLoader.getSystemResource("github/repositories.list").toURI()))
                .map(silent(github::getRepository))
                .collect(Collectors.toList());
    }

    @Rule(okMessage = "There are no old (2 weeks old) PRs at GitHub.")
    public EvaluatedRule twoWeekOldPRs() throws IOException {
        List<GHPullRequest> oldRRs = new LinkedList<>();
        for (GHRepository repo : repos) {
            for (GHPullRequest pullRequest : repo.getPullRequests(GHIssueState.OPEN)) {
                if (hoursSinceCreation(pullRequest) > 2 * 7 * 24) {
                    oldRRs.add(pullRequest);
                }
            }
        }


        if (oldRRs.isEmpty()) {
            return new EvaluatedRule(EvaluatedRule.STATUS.OK);
        } else {
            final String urls = oldRRs.stream()
                    .map(x -> x.getHtmlUrl().toString())
                    .collect(Collectors.joining("\n"));
            return new EvaluatedRule(EvaluatedRule.STATUS.FAILED, "Some PRs are way too old", "Here is list of old PRs:\n" + urls);
        }
    }

    private long hoursSinceCreation(GHPullRequest pullRequest) throws IOException {
        return Duration.between(
                Instant.from(pullRequest.getCreatedAt().toInstant()),
                Instant.now())
                .toHours();
    }


}
