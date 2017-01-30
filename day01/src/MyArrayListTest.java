import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyArrayListTest {
	MyArrayList cows = new MyArrayList();

	private static final Cow DELILAH = new Cow("Delilah", 10, "blue");
	private static final Cow GEORGY = new Cow("Georgy", 8, "green");
	private static final Cow LILY = new Cow("Lily", 4, "yellow");
	private static final Cow JIMY = new Cow("Jimi", 13, "black");
	private static final Cow JAQUAN = new Cow("Jaquan", 10, "transparent");
	private static final Cow LEGOS = new Cow("Legos", 400, "rainbow");

	@Before
	public void setUp() throws Exception{
		cows = new MyArrayList(4);
		cows.add(DELILAH);
		cows.add(GEORGY);
		cows.add(LILY);
		cows.add(JIMY);
	}

	@Test
	public void testSize(){
		assertEquals(4,cows.size());
	}

	@Test
	public void testGet(){
		assertEquals(GEORGY,cows.get(1));
		assertEquals(LILY,cows.get(2));
	}

	@Test
	public void testRemove(){
		cows.remove(1);
		cows.remove(1);
		assertEquals(2,cows.size());
		assertEquals(JIMY,cows.get(1));
	}

	@Test
	public void testAddIndex(){
		cows.add(1,JAQUAN);
		cows.add(1,LEGOS);
		assertEquals(6,cows.size());
		assertEquals(JAQUAN,cows.get(2));
	}

	@Test
	public void testResize(){
		MyArrayList resizeCows = new MyArrayList();
		for (int i = 0; i < 1000; i++) {
			String name = "Cow" + i;
			int age = i;
			String color = "Color" + i;
			resizeCows.add(new Cow(name,age,color));
		}

		assertEquals(1000,resizeCows.size());
		assertEquals(new Cow("Cow996", 996, "Color996"),resizeCows.get(996));
	}

}
