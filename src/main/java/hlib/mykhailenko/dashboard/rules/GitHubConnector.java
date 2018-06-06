package hlib.mykhailenko.dashboard.rules;

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

public class GitHubConnector {

    private List<GHRepository> repos = new LinkedList<>();

    public GitHubConnector() throws URISyntaxException, IOException {
        GitHub github = GitHub.connectAnonymously();

        for (String repoUrl : Files.lines(Paths.get(ClassLoader.getSystemResource("github/repositories.list").toURI()))
                .collect(Collectors.toList())) {
            repos.add(github.getRepository(repoUrl));
        }
    }

    @Rule
    public int hoursPROpen() throws IOException {
        int result = 0;
        for (GHRepository repo : repos) {
            for (GHPullRequest pullRequest : repo.getPullRequests(GHIssueState.OPEN)) {
                long h = hoursSinceCreation(pullRequest);
                System.out.println(pullRequest + " " + h);
                result += h;

            }
        }
        return result;

    }

    private long hoursSinceCreation(GHPullRequest pullRequest) throws IOException {
        return Duration.between(
                Instant.from(pullRequest.getCreatedAt().toInstant()),
                Instant.now())
                .toHours();
    }


}
