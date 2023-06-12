import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StressTest {
    private static final String URL_TO_TEST = "https://example.com"; // URL to test
    private static final int NUM_REQUESTS = 100; // Number of requests to send
    private static final int THREAD_POOL_SIZE = 10; // Number of concurrent threads

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        CompletableFuture<Void>[] futures = new CompletableFuture[NUM_REQUESTS];

        try {
            for (int i = 0; i < NUM_REQUESTS; i++) {
                CompletableFuture<Void> future = sendRequestAsync(executor);
                futures[i] = future;
            }

            CompletableFuture.allOf(futures).join();
            System.out.println("Stress test completed.");
        } catch (Exception e) {
            System.err.println("Error occurred during stress test: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }

    private static CompletableFuture<Void> sendRequestAsync(ExecutorService executor) {
        return CompletableFuture.runAsync(() -> {
            try {
                URL url = new URL(URL_TO_TEST);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                System.out.println("Response code: " + responseCode);
                connection.disconnect();
            } catch (IOException e) {
                System.err.println("Error occurred during request: " + e.getMessage());
            }
        }, executor);
    }
}
