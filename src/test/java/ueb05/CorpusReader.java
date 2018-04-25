package ueb05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.Iterator;

class CorpusReader {
	static Logger logger = LogManager.getLogger(CorpusReader.class);

	static Iterator<String> thesisTitleIterator() throws IOException {
		ClassLoader classLoader = CorpusReader.class.getClassLoader();
		InputStream is = classLoader.getResourceAsStream("theses.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		return new Iterator<String>() {
			// keep first line ready
			String next = br.readLine();

			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public String next() {
				String h = next;
				try {
					next = br.readLine();

					// close reader to release file
					if (next == null)
						br.close();
				} catch (IOException e) {
					next = null;
				}

				return h;
			}
		};
	}
}
