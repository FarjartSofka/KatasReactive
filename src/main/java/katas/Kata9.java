package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream()
                .flatMap(movie -> movie.getVideos().stream())
                .map(info -> ImmutableMap.of(
                        "id", info.getId(), "title", info.getTitle(),
                        "time", info.getInterestingMoments()
                                .stream()
                                .filter(moment -> moment.getType().equals("Middle"))
                                .findFirst()
                                .orElseThrow()
                                .getTime(),
                        "url", info.getBoxarts()
                                .stream()
                                .reduce((FirstBox, SecondBox) -> FirstBox.getHeight() < SecondBox.getHeight() && FirstBox.getWidth() <SecondBox.getWidth() ? FirstBox : SecondBox
                                ).get()
                                .getUrl()))
                .collect(Collectors.toList());

        // return ImmutableList.of(ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl"));
    }
}
