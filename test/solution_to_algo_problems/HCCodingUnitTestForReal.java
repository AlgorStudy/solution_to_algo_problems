package solution_to_algo_problems;

import static org.junit.Assert.*;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.concurrent.Callable;

import org.junit.Test;

public class HCCodingUnitTestForReal {
	private int permStringToInt(String permString) {
		char[] charArray = permString.toCharArray();
		char[] userPermission = Arrays.copyOfRange(charArray, 0, 3);
		char[] groupPermission = Arrays.copyOfRange(charArray, 3, 6);
		char[] otherPermission = Arrays.copyOfRange(charArray, 6, charArray.length);		
		return (getIntValueFromPermCharArray(userPermission) * 100 ) + (getIntValueFromPermCharArray(groupPermission) * 10 ) + (getIntValueFromPermCharArray(otherPermission));
    }
	private static int getIntValueFromPermCharArray(char[] permCharArray){
		int intValue = 0;
		for(char c : permCharArray){
			if(c == 'r'){
				intValue += 4;
			}else if(c == 'w'){
				intValue += 2;
			}else if(c == 'x'){
				intValue += 1;
			}else{
				continue;
			}
		}
		return intValue;
	}
	@Test
	public void test() {
		assertEquals(752, permStringToInt("rwxr-x-w-"));
	}
	
	
	class Movie {
	    private Date start, end;
	    
	    public Movie(Date start, Date end) {
	        this.start = start;
	        this.end = end;
	    }
	    
	    public Date getStart() {
	        return this.start;
	    }
	    
	    public Date getEnd() {
	        return this.end;
	    }
	    @Override
	    public String toString(){
	    	return "start : "+this.start+", end: "+this.end;
	    }
	}
	
	static Comparator<Movie> MOVIE_COMP = new Comparator<Movie>() {
		@Override
		public int compare(Movie o1, Movie o2) {
			// TODO Auto-generated method stub
			return o1.start.compareTo(o2.start);
		}
	};
	public static Boolean canViewAll(Collection<Movie> movies) {
		List<Movie> moviesInArray = new ArrayList<Movie>(movies);
		Collections.sort(moviesInArray, MOVIE_COMP);
		Movie previousMovie = null;
		Boolean result = Boolean.TRUE;
		for(Movie eachMovie : moviesInArray){
			if(previousMovie != null){
				result &= (previousMovie.end.compareTo(eachMovie.start) <= 0);
			}
			previousMovie = eachMovie;
		}
		return result;
        
    }
	@Test
	public void test2() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m");

        ArrayList<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie(sdf.parse("2015-01-01 20:00"), sdf.parse("2015-01-01 21:30")));
        movies.add(new Movie(sdf.parse("2015-01-01 21:30"), sdf.parse("2015-01-01 23:00")));
        movies.add(new Movie(sdf.parse("2015-01-01 23:10"), sdf.parse("2015-01-01 23:30")));

        System.out.println(canViewAll(movies));
	}
	
	
	interface IBird {
	    Egg Lay();
	}   

	class Chicken implements IBird{
	    public Chicken() {
	    }

		@Override
		public Egg Lay() {
			// TODO Auto-generated method stub
			return new Egg(new Callable<IBird>() {
				@Override
				public IBird call() throws Exception {
					// TODO Auto-generated method stub
					return new Chicken();
				}
			});
		}
	}

	class Egg{
		private boolean isFirstHatch = true;
	    private Callable<IBird> createBird;
		public Egg(Callable<IBird> createBird) {
	        this.createBird = createBird;
	    }
	    public IBird Hatch() throws Exception {
	        if(this.isFirstHatch){
	        	this.isFirstHatch = false;
	        	return createBird.call(); 
	        }else{
	        	throw new IllegalStateException("Egg has been  hatched");
	        }
	    }
	}
	@Test
	public void test3(){
        Chicken chicken = new Chicken();
        System.out.println(chicken instanceof IBird);
	}
	
}
