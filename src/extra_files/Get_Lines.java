import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public interface Get_Lines {
	public static long getLineCount(File file) throws IOException {
		Stream<String> lines = Files.lines(file.toPath());
		return lines.count();
	}
}
