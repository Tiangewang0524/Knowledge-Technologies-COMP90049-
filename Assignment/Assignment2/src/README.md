The attribute generator 

The program has two methods to generate new attributes for Weka to use and analyse and predict whether each tweet contains ADR. 

The first one (New_attribute_length) is to determine whether a tweet has the word which contains more than 11 letters since as I knew, some medical terms and symptom words are long words (beyond 11 letters). Then store the result in the result_long_words.txt.

The second one (New_attribute_nega) is to determine whether a tweet has negative words, such as not, "'t". Then store the result in the result_nega_words.txt. 

Besides, I used Excel to update the test.arff & train.arff respectively. I added length attribute to the test.arff and named the test_longwords.arff, added nega attribute to the test.arff and named the test_nega.arff, and added both length and nega attributes to the test.arff and named the test_combination.arff. 

Lastly, after Weka's analysis, it generated three txt files, which are longwords-output.txt, nega-output.txt, combination-output.txt. The original analysis result is stored in the orginal-output.txt. 