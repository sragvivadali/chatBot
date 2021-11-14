import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

/**
 * Class that contains helper methods for the Review Lab
 **/
public class Review {
  
  private static HashMap<String, Double> sentiment = new HashMap<String, Double>();
  private static ArrayList<String> posAdjectives = new ArrayList<String>();
  private static ArrayList<String> negAdjectives = new ArrayList<String>();
 
  
  private static final String SPACE = " ";
  
  static{
    try {
      Scanner input = new Scanner(new File("cleanSentiment.csv"));
      while(input.hasNextLine()){
        String[] temp = input.nextLine().split(",");
        sentiment.put(temp[0],Double.parseDouble(temp[1]));
        //System.out.println("added "+ temp[0]+", "+temp[1]);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing cleanSentiment.csv");
    }
  }
  
  public static String textToString( String fileName )
  {  
    try{
        File file = new File("response.txt"); 
        PrintWriter writer = new PrintWriter(file);
        String []strArray=fileName.split(" ");
        for(int i=0; i<strArray.length;i++) {
            //System.out.println(strArray[i]);
            writer.write(strArray[i] + "\n");
        }   
        writer.close();
        } catch(IOException e){
            e.printStackTrace();

    }
    String temp = "";
    try {
      Scanner input = new Scanner(new File("response.txt"));
      
      //add 'words' in the file to the string, separated by a single space
      while(input.hasNext()){
        temp = temp + input.next() + " ";
      }
      input.close();
      
      //add 'words' in the file to the string, separated by a single space
      while(input.hasNext()){
        temp = temp + input.next() + " ";
      }
      
    }
    catch(Exception e){
    }
    //make sure to remove any additional space that may have been added at the end of the string.
    return temp.trim();
  }
  
  /**
   * @returns the sentiment value of word as a number between -1 (very negative) to 1 (very positive sentiment) 
   */
  public static double sentimentVal( String word )
  {
    try
    {
      return sentiment.get(word.toLowerCase());
    }
    catch(Exception e)
    {
      return 0;
    }
  }
  
  /**
   * Returns the ending punctuation of a string, or the empty string if there is none 
   */
  public static String getPunctuation( String word )
  { 
    String punc = "";
    for(int i=word.length()-1; i >= 0; i--){
      if(!Character.isLetterOrDigit(word.charAt(i))){
        punc = punc + word.charAt(i);
      } else {
        return punc;
      }
    }
    return punc;
  }
  
  public static double totalSentiment(String fileName)
  {
    String placeholder = "";
    double totalSentiment = 0.0;
    String review = textToString(fileName); 

    for (int i = 0; i < review.length(); i++)
    {
      if (review.substring(i, i + 1).equals(" "))
      {
        totalSentiment += sentimentVal(placeholder);
        placeholder = "";
      }else{
        placeholder += review.substring(i, i + 1);
      }
    }
    return totalSentiment;
  }
  public static int starRating(String fileName)
  {
    double totalSentiment = totalSentiment(fileName);
    if(totalSentiment > 9)
    {
      return 4;
    }
      else if(totalSentiment > 6)
    {
    return 3;
    }
      else if(totalSentiment > 2)
    {
    return 2;
    }
      else if(totalSentiment > 0)
    {
      return 1;
    }
    else
    {
      return 0;
    }
  }

  /*public static String fakeReview(String fileName)
  {
    String review = textToString(fileName);
    String fake = "";
    
    for(int i = 0; i < review.length()-1; i++)
    {
      if(review.substring(i, i+1).equals("*"))
      {
        i++;
        String replace = "";
        boolean isWord = true;
        while(isWord)
        {
          replace += review.substring(i, i+1);
          i++;
          if(review.substring(i, i+1).equals(" "))
          {
            isWord = false;
          }
        }
        replace = replace.replaceAll("\\p{Punct}", "");
        replace = randomAdjective() + " ";
        fake += replace;
      }else
      {
        fake += review.substring(i, i+1);
      }
    }
    return fake; 
  }

  public static String fakeReviewStronger(String fileName)
  {
    String review = textToString(fileName);
    String fake = "";
    
    for(int i = 0; i < review.length()-1; i++)
    {
      if(review.substring(i, i+1).equals("*"))
      {
        i++;
        String replace = "";
        boolean isWord = true;
        while(isWord)
        {
          replace += review.substring(i, i+1);
          i++;
          if(review.substring(i, i+1).equals(" "))
          {
            isWord = false;
          }
        }
        replace = replace.replaceAll("\\p{Punct}", "");
        if(sentimentVal(replace) > 0)
        {
          String word = replace;
          replace = randomPositiveAdj();
          while(sentimentVal(replace) < sentimentVal(word))
          {
            replace = randomPositiveAdj();
          }
          replace += " ";
        }else if (sentimentVal(replace) < 0)
        {
          String word = replace;
          replace = randomNegativeAdj();
          while(sentimentVal(replace) > sentimentVal(word))
          {
            replace = randomNegativeAdj();
          }
          replace += " ";
        }else 
        {
          replace = randomAdjective() + " ";
        }
        fake += replace;
      }else
      {
        fake += review.substring(i, i+1);
      }
    }
    return fake; 
  }

*/
}