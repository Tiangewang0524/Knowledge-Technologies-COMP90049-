The spelling correction program 

The program has two methods to find the approximate spelling for each token.

The EditDistance method has these 5 main functions besides the main function in source code.

1. Hashset <string> readDict
Read the file (Dict.txt) and store in (add to) the hashset. 

2. calcCanonicalForm 
Calculate and find the best match of each token based on the editdistance algorithm.

3. judgeLetter
Distinguish and judge whether the token has any other characters besides letters. If it contains other characters, return false. Otherwise, return true.

4. calcRecord
Based on the definition of type ’NO’, ‘IV’ and ‘OOV’ and the Dict.txt, determine which type the token should belongs to.  

5. evaluate
The core function which use the labelled-tokens.txt to evaluate the precision. Based on the editdistance algorithm, call the ‘calcCanonicalForm’ function to find the best match word in the Dict.txt for each token through the Hashmap. Then output to a new txt file called ‘labelled-tokens-sol.txt’.

Next,compare each best match word in the ‘labelled-tokens-sol.txt’ and the correct word in the ‘labelled-tokens.txt’ and count the same words to assignment to the variable ‘correctNum’ and the different words to assignment to the variable ‘wrongNum’.

Lastly, calculate the precision by calculate ‘correctNum/(correctNum+wrongNum)’.



In the N-Gram class, it utilised the 2-Gram method to find the best match for each token. The code of 2-Gram is written by myself, referred to the slides. The other functions are similar to those in the Main.java. To apply the 2-Gram method, just call NGramDistance.NGram function in the main function.

In addition, apart from the main and N-Gram class, it has two extra classes named EditDistance and Record respectively. The EditDistance class includes the editdistance algorithm, referred to the EditDistance code from Prince of Songkla University(http://fivedots.coe.psu.ac.th/Software.coe/ContestAlgs/Code/11.%20Dynamic%20Prog/EditDistance.java). The Record class has two functions: one is to record the token, the corresponding type and the best match; the other one is to compare with the correct word in the ‘labelled-token.txt’ 

And finally, I learned the hash function (hash table, hashset, hashmap) temporarily and preliminarily from the websites (https://www.tutorialspoint.com/java/util/java_util_hashmap.htm and https://www.tutorialspoint.com/java/util/java_util_hashset.htm) to accelerate the speed of the searching. 