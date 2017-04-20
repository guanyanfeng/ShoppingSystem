package test;

public class TryTest {
	public static void main(String[] args) {
		int a=t();
		System.out.println(a);
		System.out.println(i);
	}
static int i=10;
	private static int t() {
		
		try {
			return i++;
			
		} catch (Exception e) {
			
		}finally{
			return i++;
		}
		
	}

}
