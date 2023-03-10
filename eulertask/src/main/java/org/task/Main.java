package org.task;

import org.task.providers.DeciderProvider;
import org.task.solution.Problem;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        var problem = new Problem(DeciderProvider.provideInstance());
        problem.solve();
    }
}