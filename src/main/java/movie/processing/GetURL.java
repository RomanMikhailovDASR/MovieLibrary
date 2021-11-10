package movie.processing;

import movie.exception.MovieNotFoundException;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class GetURL {
        public String getURL(String newMovieName, String imdbId, String apikey, String type, int page) throws MovieNotFoundException {

        final Logger log = Logger.getLogger(GetURL.class);
        String URL;
        if(imdbId == null) {
            try {
                URL movieURL = new URL("http://www.omdbapi.com/?s=" + newMovieName + "&" + "apikey=" + apikey + "&type=" + type + "&page=" + page);
                BufferedReader in = new BufferedReader(new InputStreamReader(movieURL.openStream()));
                URL = in.readLine();
                log.info("Movie is found");
            } catch (IOException e1) {
                log.error("Movie not found. Incorrect URL");
                throw new MovieNotFoundException();
            }
        }
        else {
            try {
                URL movieURL = new URL("http://www.omdbapi.com/?i=" + imdbId + "&" + "apikey=" + apikey + "&type=" + type);
                BufferedReader in = new BufferedReader(new InputStreamReader(movieURL.openStream()));
                URL = in.readLine();
                log.info("Movie is found");
            } catch (IOException e1) {
                log.error("Movie not found. Incorrect URL");
                throw new MovieNotFoundException();
            }
        }
        return URL;
    }
}
