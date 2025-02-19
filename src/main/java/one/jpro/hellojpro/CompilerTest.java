package one.jpro.hellojpro;

import java.io.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CompilerTest {
    private final FRQCodeBase codeBase;

    public CompilerTest(FRQCodeBase codeBase) {
        this.codeBase = codeBase;
    }

    public String CompileAndRunTest(String userCode) {
        String className = "UserClass";
        try {
            // Extract the class name from user code
            Pattern classNamePattern = Pattern.compile("public\\s+class\\s+(\\w+)");
            Matcher matcher = classNamePattern.matcher(userCode);
            if (matcher.find()) {
                className = matcher.group(1);
            }
            String filename = className + ".java";

            // Write user code to file
            File file = new File(filename);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(userCode);
            }

            // Compile the code
            Process compileProcess = Runtime.getRuntime().exec("javac " + filename);
            compileProcess.waitFor();

            // Capture compilation errors
            String compileErrors = readProcessStream(compileProcess.getErrorStream());
            if (!compileErrors.isBlank()) {
                return "Compilation Error:\n" + compileErrors;
            }

            // Run compiled code
            Process runProcess = Runtime.getRuntime().exec("java " + className);
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<String> runTask = executor.submit(() -> readProcessStream(runProcess.getInputStream()));
            Future<String> errorTask = executor.submit(() -> readProcessStream(runProcess.getErrorStream()));

            // Handle timeouts
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

    // Helper method to read the process stream
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
