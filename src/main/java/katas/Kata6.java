package katas;

import model.BoxArt;
import model.Movie;
import util.DataUtil;

import java.util.Comparator;
import java.util.List;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute() {
        List<Movie> movies = DataUtil.getMovies();

        return movies.stream()
                .map(movie -> movie.getBoxarts())
                .flatMap(boxArts -> boxArts.stream())
                .reduce((FirstBox, SecondBox) ->
                    FirstBox.getWidth() > SecondBox.getWidth() && FirstBox.getHeight() > SecondBox.getHeight() ? FirstBox: SecondBox
                ).orElseThrow()
                .getUrl();
        //        return "someUrl";
    }
}
