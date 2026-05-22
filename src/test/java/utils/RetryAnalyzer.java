package utils;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RetryAnalyzer implements TestRule {

    private int retryCount = 0;
    private static final int maxRetryCount = 2;

    @Override
    public Statement apply(Statement base, Description description) {

        return new Statement() {

            @Override
            public void evaluate() throws Throwable {

                while (true) {

                    try {

                        base.evaluate();
                        return;

                    } catch (Throwable t) {

                        retryCount++;

                        if (retryCount > maxRetryCount) {

                            throw t;
                        }

                        System.out.println("Retrying test: " + description.getDisplayName()
                                + " | Attempt: " + retryCount);
                    }
                }
            }
        };
    }
}