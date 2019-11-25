package com.karthick.practice;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * @author karthick.r1
 */
public class Demo {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int songsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> songs = IntStream.range(0, songsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }).collect(toList());

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        String q = bufferedReader.readLine();

        int result = Result.playlist(songs, k, q);

        System.out.println(result);
        bufferedReader.close();
    }
}

class Result {

    /*
     * Complete the 'playlist' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING_ARRAY songs
     *  2. INTEGER k
     *  3. STRING q
     */

    public static int playlist(List<String> songs, int k, String q) {
        // Write your code here
        int[] possibleCombinations = new int[2];
        int i = k;
        int combinations = 0;
        if (songs.contains(q)) {
            /**
             * Forward Search for the selected song in the MP3 Playlist
             */
            for (; i < songs.size(); ) {
                if ((i == k) && (songs.get(i).equals(q))) {
                    return 0;
                } else if ((i != k) && (songs.get(i).equals(q))) {
                    combinations++;
                    break;
                } else if (!(songs.get(i).equals(q)) && (i != songs.size() - 1)) {
                    i++;
                    combinations++;
                } else if (!(songs.get(i).equals(q)) && (i == songs.size() - 1)) {
                    i = 0;
                    combinations++;
                }
            }

            possibleCombinations[0] = combinations - 1;
            i = k;
            combinations = 0;

            /**
             * Backward Search for the selected song in the MP3 Playlist
             */
            for (; i >= 0; ) {
                if ((i == k) && (songs.get(i).equals(q))) {
                    return 0;
                } else if ((i != k) && (songs.get(i).equals(q))) {
                    combinations++;
                    break;
                } else if ((i != 0) && !(songs.get(i).equals(q))) {
                    combinations++;
                    i--;
                } else if ((i == 0) && !(songs.get(i).equals(q))) {
                    i = songs.size() - 1;
                    combinations++;
                }
            }
            possibleCombinations[1] = combinations - 1;
            Arrays.sort(possibleCombinations);
            return possibleCombinations[0];
        } else {
            System.err.println("There is no such song present in the MP3 Playlist");
            return 0;
        }
    }
}
