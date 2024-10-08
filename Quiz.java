import java.util.*;
import java.util.concurrent.*;

public class Quiz {

    private static final int TIME_LIMIT = 30; // 30 seconds for the quiz
    private static final List<Question> QUESTIONS = Arrays.asList(
        new Question("What is the capital of France?", "Paris"),
        new Question("What is 2 + 2?", "4"),
        new Question("What is the largest ocean on Earth?", "Pacific"),
        new Question("What year did the Titanic sink?", "1912")
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Quiz!");
        System.out.println("You have " + TIME_LIMIT + " seconds to answer all questions.");
        
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(() -> {
            int score = 0;

            for (Question question : QUESTIONS) {
                System.out.println(question.getQuestion());
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase(question.getAnswer())) {
                    score++;
                }
            }

            System.out.println("Your score: " + score + "/" + QUESTIONS.size());
            return true;
        });

        try {
            future.get(TIME_LIMIT, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("Time is up!");
            future.cancel(true);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            executor.shutdown();
            scanner.close();
        }
    }

    static class Question {
        private final String question;
        private final String answer;

        public Question(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }
    }
}