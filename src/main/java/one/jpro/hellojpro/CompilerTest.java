package one.jpro.hellojpro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompilerTest {
    private final FRQCodeBase codeBase;

    public CompilerTest(FRQCodeBase codeBase) {
        this.codeBase = codeBase;
    }

    public String CompileAndRunTest(String userCode) {
        String className = "UserClass";
        try {
            Pattern classNamePattern = Pattern.compile("public\\s+class\\s+(\\w+)");
            Matcher matcher = classNamePattern.matcher(userCode);
            if (matcher.find()) {
                className = matcher.group(1);
            }
            String filename = className + ".java";

            File file = new File(filename);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(userCode);
            }

            Process compileProcess = Runtime.getRuntime().exec("javac " + filename);
            compileProcess.waitFor();

            String compileErrors = readProcessStream(compileProcess.getErrorStream());
            if (!compileErrors.isBlank()) {
                return "Compilation Error:\n" + compileErrors;
            }

            Process runProcess = Runtime.getRuntime().exec("java " + className);
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<String> runTask = executor.submit(() -> readProcessStream(runProcess.getInputStream()));
            Future<String> errorTask = executor.submit(() -> readProcessStream(runProcess.getErrorStream()));

            String result;
            try {
                result = runTask.get(3, TimeUnit.SECONDS);
                String runtimeErrors = errorTask.get(3, TimeUnit.SECONDS);
                if (!runtimeErrors.isBlank()) {
                    return "Runtime Error:\n" + runtimeErrors;
                }
            } catch (TimeoutException e) {
                runProcess.destroy();
                return "Runtime Error: Execution timed out.";
            } finally {
                executor.shutdownNow();
            }

            return result;
        } catch (Exception e) {
            return "Fatal Error: " + e.getMessage();
        } finally {
            new File(className + ".java").delete();
            new File(className + ".class").delete();
        }
    }

    private String readProcessStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }
        return output.toString();
    }

}
