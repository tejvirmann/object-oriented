
public interface Dictionary {
	
	/**
	   * checks whether the dictionary is empty or not
	   * @return true if this dictionary is empty, false otherwise
	   */
	  public boolean isEmpty();
	  
	  /**
	   * adds a word definition (word and meaning provided) to this dictionary
	   * 
	   * @param word of the word definition to be added to this dictionary
	   * @param meaning of the word definition to be added to this dictionary
	   * @return true if the word was successfully added to the dictionary and false if the word was
	   *         already in this dictionary
	   * @throws IllegalArgumentException if either word or meaning is null or an empty String
	   */
	  public boolean addWord(String word, String meaning);
	  
	  /**
	   * Looks up for a meaning of a given word s if present in this dictionary
	   * @param s a word
	   * @return meaning of the provided string s if present in this dictionary
	   * @throws NoSuchElementException if the word s was not found in this dictionary
	   */  
	  public String lookup(String s);
	  
	  /**
	   * Gets the size of this dictionary in terms of words
	   * @return the number of word defintions stored in the dictionary
	   */
	  public int size();

}
