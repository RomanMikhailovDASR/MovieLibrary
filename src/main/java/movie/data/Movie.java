package movie.data;

import java.util.List;
import java.util.Objects;

public class Movie {
    private String imdbID;
    private String title;
    private int year;
    private String plot;
    private String posterUrl;
    private List<Actor> actors;
    private int awardWins;
    private int awardNominations;
    private double tomatoMeter;


    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public int getAwardWins() {
        return awardWins;
    }

    public void setAwardWins(int awardWins) {
        this.awardWins = awardWins;
    }

    public int getAwardNominations() {
        return awardNominations;
    }

    public void setAwardNominations(int awardNominations) {
        this.awardNominations = awardNominations;
    }

    public double getTomatoMeter() {
        return tomatoMeter;
    }

    public void setTomatoMeter(double tomatoMeter) {
        this.tomatoMeter = tomatoMeter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return year == movie.year && awardWins == movie.awardWins && awardNominations == movie.awardNominations && Double.compare(movie.tomatoMeter, tomatoMeter) == 0 && Objects.equals(imdbID, movie.imdbID) && Objects.equals(title, movie.title) && Objects.equals(plot, movie.plot) && Objects.equals(posterUrl, movie.posterUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imdbID, title, year, plot, posterUrl, awardWins, awardNominations, tomatoMeter);
    }
}
