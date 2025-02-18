# Threading and File Handling in Java

## Threading in Java

### 1. Single Threading
- Involves executing a single task at a time.
- The program runs sequentially without parallel execution.
- Suitable for simple tasks without performance concerns.

### 2. Multithreading
- Multiple threads execute concurrently, improving efficiency and responsiveness.
- Threads share resources and memory space.
- Achieved using `Thread` class or `Runnable` interface.

### 3. Thread Pooling
- Uses a pool of worker threads to manage and execute tasks efficiently.
- Reduces overhead of thread creation and destruction.
- Implemented using `ExecutorService`.

## Differences Between Runnable, Consumer, and Executors

| Feature          | Runnable | Consumer | ExecutorService |
|-----------------|----------|----------|-----------------|
| Purpose         | Represents a task to be executed by a thread | Represents an operation that accepts a single argument and returns no result | Manages and executes threads efficiently |
| Method          | `run()`  | `accept(T t)` | `execute(Runnable command)` or `submit(Callable task)` |
| Return Value    | None (void) | None (void) | None or Future (depending on method used) |
| Usage          | Used when implementing a task for a thread | Used in functional programming and lambda expressions | Used for managing thread pools |
| Example Usage   | `new Thread(new MyRunnable()).start();` | `Consumer<String> printer = s -> System.out.println(s);` | `executor.execute(new MyRunnable());` |

## Streams and Readers in Java

### Byte Streams
- Used for reading and writing binary data (e.g., images, audio, and video files).
- Classes: `InputStream`, `OutputStream`, `FileInputStream`, `FileOutputStream`.
- Example:
  ```java
  FileInputStream fis = new FileInputStream("file.txt");
  int data;
  while ((data = fis.read()) != -1) {
      System.out.print((char) data);
  }
  fis.close();
  ```

### Character Streams
- Used for reading and writing text data.
- Classes: `Reader`, `Writer`, `FileReader`, `FileWriter`.
- Example:
  ```java
  FileReader fr = new FileReader("file.txt");
  int data;
  while ((data = fr.read()) != -1) {
      System.out.print((char) data);
  }
  fr.close();
  ```

### Converting Byte to Character Streams
- `InputStreamReader` converts `InputStream` to `Reader`.
- Example:
  ```java
  InputStreamReader isr = new InputStreamReader(new FileInputStream("file.txt"));
  BufferedReader br = new BufferedReader(isr);
  String line;
  while ((line = br.readLine()) != null) {
      System.out.println(line);
  }
  br.close();
  ```

## File Reading and Writing in Java

### Reading a File
```java
import java.io.*;

public class FileReadExample {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("sample.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Writing to a File
```java
import java.io.*;

public class FileWriteExample {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sample.txt", true))) {
            writer.write("Hello, this is a test message!\n");
            System.out.println("Message written to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```



