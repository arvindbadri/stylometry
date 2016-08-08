import edu.emory.mathcs.nlp.tokenization.EnglishTokenizer;
import edu.emory.mathcs.nlp.tokenization.Token;
import edu.emory.mathcs.nlp.tokenization.Tokenizer;
import edu.emory.mathcs.nlp.tokenization.dictionary.Unit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TestMain {
    private static final Tokenizer TOKENIZER = new EnglishTokenizer();
    private static final String IS_WORD = "[a-zA-Z]+";
    public static void main(String[] args) {
        String line = "something is amiss! what is it?";
        System.out.println(getSentencesFromLine(line));
        System.out.println(getNumberOfSentencesInParagraph(line));
        System.out.println(getNumberOfWordsInParagraph(line));
    }

    private static int getNumberOfLettersInWord (Token token) {
        int length = 0;
        if (isWord(token)) {
            length+= token.toString().length();
        }
        return length;
    }

    private static int getNumberOfLettersInSentence(List<Token> sentence) {
        int length = 0;
        for (Token token: sentence) {
            length += getNumberOfLettersInWord(token);
        }
        return length;
    }

    private static int getNumberOfLettersInParagraph(String line) {
        List<List<Token>> sentences = getSentencesFromLine(line);
        int length = 0;
        for (List<Token> sentence : sentences) {
            length += getNumberOfLettersInSentence(sentence);
        }
        return length;
    }

    private static int getNumberOfWordsInParagraph(String paragraph) {
        List<List<Token>> sentences = getSentencesFromLine(paragraph);
        int numberOfWords = 0;
        for (List<Token> tokens : sentences) {
            for (Token token : tokens) {
                if (isWord(token)) {
                    numberOfWords ++;
                }
            }
        }
        return numberOfWords;
    }

    private static Boolean isWord(Token token) {
            return token.toString().matches(IS_WORD);
    }

    private static int getNumberOfSentencesInParagraph(String paragraph) {
        return getSentencesFromLine(paragraph).size();
    }

    private static List<List<Token>> getSentencesFromLine(String line) {
        return TOKENIZER.segmentize(line);
    }


    private static List<String> readLines (String path) throws FileNotFoundException, IOException {
        FileReader file = new FileReader(path);
        BufferedReader reader = new BufferedReader(file);
        List<String> lines = new ArrayList<String>();
        String singleLine = "";
        while ((singleLine = reader.readLine()) != null) {
            lines.add(singleLine);
        }
        return lines;
    }
}
