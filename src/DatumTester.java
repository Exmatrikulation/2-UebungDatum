public class DatumTester {
    @SuppressWarnings("static-access")
	public static void main(String[] args) {
       Datum d1 = new Datum(30, 11 ,2025);
       Datum d2 = new Datum(6,12,2024); 
       Datum d3 = new Datum(5, 12 ,2024);
       
        System.out.println(d1.toString());
        System.out.println(d1.equals(d2));
        System.out.println(d1.vor(d3));
        System.out.println(d1.istschaltjahr(100));
            
        System.out.println(d1.tagnummerImJahr());
        System.out.println(d1.anzahlTageBis(d3));
    }
}
