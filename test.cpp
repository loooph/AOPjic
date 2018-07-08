/*Sehr gute Implementierung!
 */

// clang++ -Weverything -std=c++14 pi1.cpp -o pia1
#include <iomanip>
#include <iostream>
#include <cmath>

/**@brief Funktion zur Berechnung der Kreiszahl Pi
 *	Berechnung über die Produktformel von Vieta
 *	@return gibt die berechnete Kreiszahl Pi zurück
 */
double pi();
double pi()
{
/**	Startwerte der Berechnungsvariablen, die für den ersten Durchlauf nötig sind.
 *	Diese werden in der for-Schleife überschrieben
 */
 /*Solche kommentare verwenden "/**" ist für Doxygen komentare
  */
	double b=sqrt(2);
	double c=1;

/**
 * Folgend wird die Produktformel von Vieta benutzt um die Kreiszahl Pi annähernd zu berechnen
 * Für eine Genauigkeit von 15. Nachkommastellen sind lediglich 25 Durchläufe nötig
 */
	for (int i=1; i<=25; ++i)
	/*Grenzen sollten bei einer Konvergenz evtl. erhöht werden bei ähnlichen aufgaben um die 
	 * genauigkeit zu erhöhen!
	 * Konvergenz liegt im Unendlichen vor; 25 << inf
	 */
	{
	 	c=c*b/2;	
		b=sqrt(2+b);
	}
	return 2/c;
}

int main()
{
//Ausgabe der Funktion auf 15 Nachkommastellen genau durch setprecision
	std::cout << "Meine eigene Funktion für Pi: " << std::setprecision(15) << pi() << std::endl;
//Ausgabe von PI der CMath - Datenbank für den Direktvergleich
	std::cout << "Pi aus der cmath-Datenbank:   " << M_PI << std::endl;
	/*Okay gut zum vergleich jedoch solte NICHTS außer sqrt aus cmath benutzt werden ;)*/
}
