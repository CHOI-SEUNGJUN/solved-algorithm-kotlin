package programmers

/**
 * 프로그래머스 - 베스트앨범
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42579
 */

fun main() {
    val answer = BestAlbumSolution().solution(
        arrayOf("classic", "pop", "classic", "classic", "pop"),
        intArrayOf(500, 600, 150, 800, 2500)
    )
    println(answer.joinToString(" "))
}

class BestAlbumSolution {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        val songList = mutableListOf<Song>()
        val genreMap = hashMapOf<String, Int>()

        for (i in genres.indices) {
            songList.add(
                Song(i, plays[i], genres[i])
            )

            genreMap[genres[i]] = genreMap.getOrDefault(genres[i], 0) + plays[i]
        }

        songList.sortWith { o1, o2 ->
            if (o1.genre == o2.genre) {
                if (o1.play == o2.play) {
                    o1.idx - o2.idx
                } else {
                    -(o1.play - o2.play)
                }
            } else {
                -(genreMap[o1.genre]!! - genreMap[o2.genre]!!)
            }
        }

        val list = mutableListOf<Int>()

        songList
            .groupBy { it.genre }
            .map { it.value.take(2) }
            .forEach { sub ->
                sub.forEach { song ->
                    list.add(song.idx)
                }
        }

        return list.toIntArray()
    }

    data class Song(
        val idx: Int,
        val play: Int,
        val genre: String
    )
}