import java.io.*;
import java.util.*;

public class FrequentWords {
    private static final Integer ONE_BILLION = 1000000000;

    public static void main (String [] args) {
        try {
            System.out.println(mostFrequentWords(15, populateWordMap("C:\\Users\\abadrinath\\IdeaProjects\\Training\\src\\warandpeace.txt")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Integer> populateWordMap(String path) throws FileNotFoundException, IOException {
        Map<String,Integer> wordMap = new HashMap<>();
        List<String> words = getWordsFromLines(readLines(path));
        for (String word : words) {
            if (wordMap.containsKey(word)) {
                wordMap.put(word, wordMap.get(word) + 1);
            }
            else {
                wordMap.put(word, 1);
            }
        }
        if (wordMap.containsKey("")) {
            System.out.println("wordmap Contains nothings");
        }
        return wordMap;
    }

    private static List<String> getWordsFromLines(List<String> lines) {
        List<String> words = new ArrayList<> ();
        for (String line : lines) {
            List<String> tokens = tokenize(line);
            for (String token : tokens) {
                if (!(getWordFromToken(token).equals(""))) {
                    words.add(getWordFromToken(token));
                }
            }
        }
        return words;
    }

    private static String getWordFromToken (String token) {
        token = token.replace(",", "");
        token = token.replace(":", "");
        token = token.replace("-", "");
        token = token.replace("\\s+", "");
        token = token.replace("!", "");
        token = token.replace("?", "");
        token = token.replace("\n", "");
        token = token.replace("\n\\s*", "");
        token = token.replace(".", "");
        token = token.replace("'s", "");
        return token.toLowerCase();
    }

    private static List<String> tokenize (String line) {
        final String REGEX = "\\s+";
        List<String> tokens = Arrays.asList(line.split(REGEX));
        return tokens;
    }

    private static List<String> readLines (String path) throws FileNotFoundException, IOException {
        FileReader file = new FileReader(path);
        BufferedReader reader = new BufferedReader(file);
        List<String> lines = new ArrayList<>();
        String singleLine = "";
        while ((singleLine = reader.readLine()) != null) {
            lines.add(singleLine);
        }
        return lines;
    }

    private static List<String> mostFrequentWords (int n, Map<String, Integer> wordMap) {
        List<String> mostFrequentWords = sortFrequentWords(wordMap);
        return mostFrequentWords.subList(0, n);
    }

    private static List<String> sortFrequentWords (Map<String, Integer> wordMap) {
        List<String> frequenciesWithWords = concatenateFrequenciesWithWords (wordMap);
        Collections.sort(frequenciesWithWords, Collections.reverseOrder());
        List<String> frequentWords = removeFrequencies(frequenciesWithWords);
        return frequentWords;
    }

    private static List<String> concatenateFrequenciesWithWords (Map<String, Integer> wordMap) {
        List<String> frequenciesWithWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordMap.entrySet() ) {
            String frequencyWithWord = "";
            frequencyWithWord += Integer.toString(ONE_BILLION + entry.getValue());
            frequencyWithWord += entry.getKey();
            frequenciesWithWords.add(frequencyWithWord);
        }
        return frequenciesWithWords;
    }

    private static List<String> removeFrequencies (List<String> frequenciesWithWords) {
        List<String> wordsWithoutFrequencies = new ArrayList<> ();
        for (String word : frequenciesWithWords) {
            wordsWithoutFrequencies.add(word.substring(10));
        }
        return wordsWithoutFrequencies;
    }
}
