; 27/4 clojure.lang.Ratio
(+ (* 2 3) (/ 3 4))

; fehler: + funktion erwartet eine zahl oder variable, - ist eine weitere funktion
(+ - * /)

; (+ - * /) liste die symbole werden in eine liste gepackt und nicht ausgeführt
'(+ - * /)

; "1.02true" setzt die werte als strings zusammen
(str 1.0 2 true)

; 123.45 double, parst string 123 in eine long und string 0.45 und addiert diese
(+ (parse-long "123") (parse-double "0.45"))

; "this" string, gibt den ersten wert zurück der nicht false oder nil ist
(or "this" "is" "crazy")

; "crazy" string, gibt den letzten wert zurück falls alle werte true sind
(and "this" "is" "crazy")

; fehler: funktion erwartet nicht 3 argumente
(not "this" "is" "crazy")

; 2/5 ratio, die anonyme funktion wird mit 5/2 aufgerufen
((fn [x] (/ 1 x)) (/ 5 2))

; true boolean, prüft ob die zahl 3000000000 eine ganzzahl ist, true for int and long??
(int? 3000000000)