/**
 * Created by wtg on 29/8/17.
 */

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    //Read dict file
    public static HashSet<String> readDict(String file){
        HashSet<String> words = new HashSet<String>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(file)));
            while(true){
                String line = in.readLine();
                if (line == null){
                    break;
                }
                words.add(line.trim().toLowerCase());
            }
            System.out.println("Read dict successfully.");
            return words;
        } catch (Exception e) {
            e.printStackTrace();  //Catch exceptions.
            return words;
        }
    }

    //Calculate the canonical form
    public static String calcCanonicalFormED(HashSet<String> dict, String word){
        int bestVal = 100000000;
        String bestMatch = "";
        for (String w : dict) {
            int val = EditDistance.calc(word, w);
            if(val < bestVal){
                bestMatch = w;
                bestVal = val;
            }
        }
        return bestMatch;
    }

    //Judge whether the token has other characters.
    public static boolean judgeLetter(String str){
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); ++i){
            if (!(str.charAt(i) >= 'a' && str.charAt(i) <= 'z')){
                return false;
            }
        }
        return true;
    }

    //Calculate the record of word.
    public static Record calcRecordED(HashSet<String> dict, String word){
        Record record = new Record();
        if (judgeLetter(word) == false){
            record.setToken(word);
            record.setCanonicalForm(word);
            record.setType("NO");
            return record;
        }
        record.setToken(word);
        record.setCanonicalForm(calcCanonicalFormED(dict, word));
        if (dict.contains(word)){
            record.setType("IV");
        }
        else {
            record.setType("OOV");
        }
        return record;
    }

    //Evaluate the precision by using labelled tokens.
    public static void evaluateED(HashSet<String> dict) {
        System.out.println("Using labelled-tokens.txt to evaluate the precision of EditDistance algorithm.");
        int correctNum = 0;
        int wrongNum = 0;
        HashMap<String, Record> hash = new HashMap<String, Record>();

        try {
            StringBuilder bestMatch = new StringBuilder();
            BufferedReader in = new BufferedReader(new FileReader(new File("labelled-tokens.txt")));
            while(true){
                String line = in.readLine();
                if (line == null){
                    break;
                }
                String[] strs = line.split("\t");
                Record r = null;
                if(hash.containsKey(strs[0])){
                    r = hash.get(strs[0]);
                }
                else {
                    r = calcRecordED(dict, strs[0]);
                    hash.put(strs[0], r);
                }

                bestMatch.append(r.getToken());
                bestMatch.append("\t");
                bestMatch.append(r.getType());
                bestMatch.append("\t");
                bestMatch.append(r.getCanonicalForm());
                bestMatch.append("\n");
                if (r.equals(new Record(strs[0], strs[1], strs[2]))){
                    correctNum ++;
                }
                else {
                    wrongNum++;
                }
                //System.out.println(strs[0] + strs[1] + strs[2]);
            }
            PrintWriter printWriter = new PrintWriter(new File("labelled-tokens-ED.txt"));
            printWriter.write(bestMatch.toString());
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Correct number : " + correctNum);
        System.out.println("Wrong number : " + wrongNum);
        System.out.println("Precision : " + 1.0 * correctNum / (correctNum + wrongNum));
        System.out.println("Predict result saved to labelled-tokens-ED.txt");
    }

    public static void main(String[] args) {
        HashSet<String> dict = readDict("dict.txt");
        evaluateED(dict);
        NGramDistance.evaluateNG(dict);
    }
}
