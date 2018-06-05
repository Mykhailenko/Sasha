package hlib.mykhailenko.dashboard.github;

//import org.kohsuke.github.GHIssueState;
//import org.kohsuke.github.GHPullRequest;
//import org.kohsuke.github.GHRepository;
//import org.kohsuke.github.GitHub;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.GitHubRequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class GitHubConnector {

//    private List<GHRepository> repos;

    public GitHubClient() throws URISyntaxException, IOException {
//        GitHub github = GitHub.connectAnonymously();
//
//
//        for(String repoUrl : Files.lines(Paths.get(ClassLoader.getSystemResource("github/repositories.list").toURI())).collect(Collectors.toList())){
//            System.out.println(repoUrl);
//
//
//            repos.add(github.getRepository(repoUrl));
//
//        }

        GitHubClient client = new GitHubClient();
        GitHubRequest request = new GitHubRequest();
        request.
        client.get()
    }

    public int test() throws IOException {
//        int result = 0;
//        for (GHRepository repo : repos) {
//            final List<GHPullRequest> pullRequests = repo.getPullRequests(GHIssueState.OPEN);
//            result += pullRequests.size();
//        }
        return 0;

    }


}
