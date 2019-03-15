/**
 * Created by wtg on 29/8/17.
 */

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

//Actually, here I used 2-Gram distance algorithm
public class NGramDistance {

    public static int Ngram(String src, String dst)  {
        src ="#" + src + "#";
        int lengthsrc = src.length();
        dst ="#" + dst + "#";
        int lengthdst = dst.length();
        int intersectNum = 0;
        int distance;
        for (int j = 1; j < lengthsrc; j++) {
            for (int k = 1; k < lengthdst; k++) {
                if (src.charAt(j - 1) == dst.charAt(k - 1) && src.charAt(j) == dst.charAt(k))
                    intersectNum++;
            }
        }
        distance = lengthsrc + 1 + lengthdst + 1 - 2 * intersectNum;

        return distance;

    }

    //Calculate the canonical form by 2-Gram distance algorithm
    public static String calcCanonicalFormNG(HashSet<String> dict, String word){
        int bestVal = 100000000;
        String bestMatch = "";
        for (String w : dict) {
            int val = NGramDistance.Ngram(word, w);
            if(val < bestVal){
                bestMatch = w;
                bestVal = val;
            }
        }
        return bestMatch;
    }

    //Calculate the record of word.
    public static Record calcRecordNG(HashSet<String> dict, String word){
        Record record = new Record();
        if (Main.judgeLetter(word) == false){
            record.setToken(word);
            record.setCanonicalForm(word);
            record.setType("NO");
            return record;
        }
        record.setToken(word);
        record.setCanonicalForm(calcCanonicalFormNG(dict, word));
        if (dict.contains(word)){
            record.setType("IV");
        }
        else {
            record.setType("OOV");
        }
        return record;
    }

    //Evaluate the precision by using labelled tokens.
    public static void evaluateNG(HashSet<String> dict) {
        System.out.println("Using labelled-tokens.txt to evaluate the precision of 2-Gram algorithm.");
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
                    r = calcRecordNG(dict, strs[0]);
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
            PrintWriter printWriter = new PrintWriter(new File("labelled-tokens-NG.txt"));
            printWriter.write(bestMatch.toString());
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Correct number : " + correctNum);
        System.out.println("Wrong number : " + wrongNum);
        System.out.println("Precision : " + 1.0 * correctNum / (correctNum + wrongNum));
        System.out.println("Predict result saved to labelled-tokens-NG.txt");
    }

}
