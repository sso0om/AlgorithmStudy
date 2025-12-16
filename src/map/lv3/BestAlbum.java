package map.lv3;

import java.util.*;

/** 베스트 앨범 - 프로그래머스 42579
 *
 * 문제 유형 : 해시(Map), Comparator, Stream, 객체, Sort
 */
public class BestAlbum {

    // ### 1. Map 1 + Class 2
    //- 객체로 묶여 있어서 더 깔끔함
    //- 장르 단위로 사고하기 쉬움
    //- 유지보수성도 더 좋음

    class Genre {
        String name;
        int totalPlay = 0;
        List<Song> songs= new ArrayList<>();

        public Genre(String name) {
            this.name = name;
        }

        public void addSong(int no, int play) {
            songs.add(new Song(no, play));
            totalPlay += play;
        }

        // 장르의 노래들 정렬 - 1. 재생 수 많음, 2. 순번 앞
        public void sortSongs() {
            songs.sort(
                Comparator.comparingInt((Song s) -> s.play).reversed()
                    .thenComparingInt(s -> s.no));
        }
    }

    class Song{
        int no;
        int play;

        public Song(int no, int play) {
            this.no= no;
            this.play = play;
        }
    }

    public int[] solution1(String[] genres, int[] plays) {
        // 1. 장르별 총 재생 수
        Map<String, Genre> map = new HashMap<>();

        for(int i = 0; i < genres.length; i++) {
            String name = genres[i];
            map.computeIfAbsent(name, Genre::new)
                .addSong(i, plays[i]);
        }

        // 2. 장르를 총 재생 수 기준으로 정렬
        List<Genre> list = map.values().stream()
            .sorted(Comparator.comparingInt((Genre g) -> g.totalPlay).reversed())
            .toList();

        List<Integer> answer = new ArrayList<>();

        // 3. 장르별 재생수 최상위 2개
        for(Genre genre : list) {
            genre.sortSongs();

            for(int i = 0; i < Math.min(2, genre.songs.size()); i++) {
                answer.add(genre.songs.get(i).no);
            }
        }

        return answer.stream()
            .mapToInt(i -> i)
            .toArray();
    }


    // 2. Map 2 + Class 1
    // 구현이 단순

    static class Song2 {
        int index;
        int play;

        public Song2(int index, int play) {
            this.index = index;
            this.play = play;
        }
    }

    public int[] solution2(String[] genres, int[] plays) {
        // 1. 장르별 총 재생 수
        Map<String, Integer> genreTotalMap = new HashMap<>();

        // 2. 장르별 노래 목록
        Map<String, List<Song2>> genreSongMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            genreTotalMap.put(genre, genreTotalMap.getOrDefault(genre, 0) + play);

            genreSongMap
                .computeIfAbsent(genre, k -> new ArrayList<>())
                .add(new Song2(i, play));
        }

        // 3. 장르 정렬: 총 재생 수 내림차순
        List<String> genreOrder = new ArrayList<>(genreTotalMap.keySet());
        genreOrder.sort((a, b) -> Integer.compare(genreTotalMap.get(b), genreTotalMap.get(a)));

        List<Integer> result = new ArrayList<>();

        // 4. 장르별 노래 정렬 후 2개씩 선택
        for (String genre : genreOrder) {
            List<Song2> songs = genreSongMap.get(genre);

            songs.sort((a, b) -> {
                if (a.play == b.play) {
                    return Integer.compare(a.index, b.index); // 고유번호 오름차순
                }
                return Integer.compare(b.play, a.play); // 재생 수 내림차순
            });

            result.add(songs.get(0).index);
            if (songs.size() > 1) {
                result.add(songs.get(1).index);
            }
        }

        // 5. List<Integer> -> int[]
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}
