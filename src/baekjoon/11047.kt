package baekjoon

// 동전 0
// https://www.acmicpc.net/problem/11047

fun main() = with(System.`in`.bufferedReader()) {
    var (N, K) = readLine().split(" ").map { it.toInt() }

    val coins = IntArray(N) { readLine().toInt() }

    var requiredCoinCnt = 0


    for (coin in coins.reversed()) {
        if (K > 0) {
            requiredCoinCnt += K / coin
            K %= coin
        } else break
    }

    print(requiredCoinCnt)
}