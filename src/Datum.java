public class Datum {

	// Attribute
    private int  tag;
	private int monat;
	private int jahr;
	
	// Konstruktor
	public Datum(int tag, int monat, int jahr) {
		if (istDatumGueltig(jahr, tag, monat)){
            this.monat = 0;
            this.jahr = 0;
            this.tag = 0;
        } else {
            this.jahr = jahr;
            this.tag = tag;
            this.monat = monat;
        }
	}
	
	// Getter
	public int getTag() {
		return tag;	
	}
	
	public int getMonat() {
		return monat;	
	}
	
	public int getJahr() {
		return jahr;	
	}
	
	
	public String toString() {
		return "Datum: "+tag+"."+monat+"."+jahr;
	}
	
	public boolean equals(Datum d) {
		return this.tag == d.tag && this.monat == d.monat && this.jahr == d.jahr;
    }	
        
    public boolean vor(Datum d){
        switch ((int)Math.signum(this.jahr - d.jahr)){
            case 0:  
            switch ((int)Math.signum(this.monat - d.monat)){
                case 0:  
                    switch ((int)Math.signum(this.tag - d.tag)){
	                    case -1:return true;
	                    default: return false;
                    }
                case -1:return true;
                default: return false;
            }
            case -1:return true;
            default: return false;
        }
    }

     public boolean vor(Datum d1, Datum d2){
        return d1.vor(d2);
    }
            
    public static boolean istschaltjahr(int jahr){
        return (jahr%400 == 0) || (jahr%4 == 0 && jahr%100!=0);
    }

    public static int anzahlTageImMonat(int monat, int jahr){
        if (monat == 2 ) {if (istschaltjahr(jahr)){return 29;} else {return 28;}}
        else {
            if ((monat > 7 && monat%2 == 0) || ((monat <= 7 && monat%2 == 1))){return 31;} else{return 30;} }      
    }
    
    public static boolean istDatumGueltig(int jahr, int tag, int monat){
        return (monat > 12 || monat < 1) || tag > anzahlTageImMonat(monat, jahr) || tag < 1;
    }
    
    // Zusatzaufgaben
    public int tagnummerImJahr() {
    	int nummer = this.tag; // Aktueller Monat abgearbeitet
    	
    	// Gehe Monate -1 durch und addiere Tage
    	for(int i = this.monat-1; i>=1; i--) {
    		nummer += anzahlTageImMonat(i, this.jahr);
    	}
    	return nummer;
    }
    
    /*
     * Endtag wird hier NICHT mitaddiert! -> einfach bei return nummer +1 machen
     */
    public int anzahlTageBis(Datum bis) {
    	// ist jahr gleich?
    	if(this.jahr == bis.jahr) {
    		// returne tagnummer
    		return bis.tagnummerImJahr() - tagnummerImJahr();
    	}
    	
    	
        int tage = 0;

        // bis liegt in der Zukunft
        if (bis.jahr > this.jahr) {
            // Resttage im aktuellen Jahr
            int tageImJahr = istschaltjahr(this.jahr) ? 366 : 365;
            tage += tageImJahr - this.tagnummerImJahr();

            // volle Jahre dazwischen
            for (int j = this.jahr + 1; j < bis.jahr; j++) {
                tage += istschaltjahr(j) ? 366 : 365;
            }

            // Tage im Zieljahr
            tage += bis.tagnummerImJahr();
            return tage;
        }

        // bis liegt in der Vergangenheit
        else {
            // Resttage im Zieljahr (vom Jahresende zurück)
            int tageImJahr = istschaltjahr(bis.jahr) ? 366 : 365;
            tage -= tageImJahr - bis.tagnummerImJahr();

            // volle Jahre dazwischen
            for (int j = bis.jahr + 1; j < this.jahr; j++) {
                tage -= istschaltjahr(j) ? 366 : 365;
            }

            // Tage im aktuellen Jahr
            tage -= this.tagnummerImJahr();
            return tage;
        }
    }

}