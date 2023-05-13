package filehandler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * The FileService class provides methods for reading from and writing to files.
 */
public class FileService {

    public FileService() {
    }

    public String readFile(Path path) throws IOException {

        try (var fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {

            var buffer = ByteBuffer.allocate((int) fileChannel.size());
            var sb = new StringBuilder();
            var charset = StandardCharsets.UTF_8;

            while (fileChannel.read(buffer) > 0) {
                buffer.flip();
                sb.append(charset.decode(buffer));
                buffer.clear();
            }


            return sb.toString().replaceAll("\t", "    ")
                    .replaceAll("\r", "");
        }
    }

    public void writeToFile(Path path, String content) throws IOException {

        try (FileChannel fileChannel = FileChannel.open(path,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING)
        ) {

            var buffer = StandardCharsets.UTF_8.encode(content);

            while (buffer.hasRemaining()) {
                fileChannel.write(buffer);
            }
        }

    }
}
