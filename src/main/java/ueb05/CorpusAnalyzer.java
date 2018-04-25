package ueb05;

import sun.awt.image.ImageWatched;

import java.util.*;

class CorpusAnalyzer {
	private List<String> theses;

	CorpusAnalyzer(Iterator<String> thesesIterator) {
		// TODO Alle Titel in die this.theses Liste übernehmen
		theses = new LinkedList<>();
		while(thesesIterator.hasNext()) {
			theses.add(thesesIterator.next());
		}
	}
	/**
	 * Gibt die Anzahl der angefertigten Theses zurück
	 */
	int countTheses() {
		return theses.size();
	}

	/**
	 * Gibt die durchschnittliche Länge von Titeln in Worten zurück
	 */
	int averageThesisTitleLength() {
	    int average = 0;
	    int summe = 0;
	    int counter = 0;
        for (String e: theses) {
            int anzahlwoerter = 0;
            for (int i = 0; i < e.length(); i++) {
                if (e.charAt(i)== ' ') {
                    anzahlwoerter++;
                }
            }
            summe = summe + anzahlwoerter + 1;
            counter ++;
        }
        return average = summe / counter;
	}

	/**
	 * Gibt eine alphabetisch absteigend sortierte und duplikatfreie
	 * Liste der ersten Wörter der Titel zurück.
	 */
	List<String> uniqueFirstWords() {
	    Set<String> wordsSet = new TreeSet<>();
        for (String e: theses){
            for (int i = 0; i < e.length(); i++){
                if (e.charAt(i)== ' '){
                    wordsSet.add(e.substring(0, i));
                    break;
                }
            }
        }
        List<String> sortiert = new LinkedList<>();
        for (String e : wordsSet){
            sortiert.add(e);
        }

        //Sortieren
        Collections.sort(sortiert, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.toString().compareTo(o1.toString());
            }
        });
	    return sortiert;
	}

	/**
	 * Gibt einen Iterator auf Titel zurück; dabei werden alle Woerter, welche
	 * in `blackList` vorkommen durch Sternchen ersetzt (so viele * wie Buchstaben).
	 */
	Iterator<String> censoredIterator(Set<String> blackList) {
	    Iterator<String> itTheses = theses.iterator();
	    return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return itTheses.hasNext();
            }

            @Override
            public String next() {
                String censored = itTheses.next();

                for (String t : censored.split(" ")){
                    if (blackList.contains(t)) {
                        String asterisks = "";
                        for (int i = 0; i < t.length(); i++) {
                            asterisks = asterisks + "*";
                        }
                        censored = censored.replaceAll(t, asterisks);
                    }
                }
                return censored;
            }
        };
	}

	/**
	 * Gibt eine Liste von allen Titeln zurueck, wobei Woerter so ersetzt werden,
	 * wie sie in der Map abgebildet werden.
	 */
	List<String> normalizedTheses(Map<String, String> replace) {
        List<String> normal = new LinkedList<>();
        Set<String> keys = replace.keySet();
        for (String e : theses) {
            for (String k : keys) {
                if (e.contains(k)) {
                    e = e.replaceAll(k, replace.get(k));
                }

            }
            normal.add(e);
        }
        return normal;
    }
}
